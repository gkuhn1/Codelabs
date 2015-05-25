package com.exemplos.aula06_exemplo02_contentprovider;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	 /** Called when the activity is first created. */
		Button btnContatos1;
		Button btnContatos2;
		Button btnContatos3;
		TextView txtConteudo;
		
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			txtConteudo = (TextView) findViewById(R.id.txtConteudo);
			
		    btnContatos1 = (Button) findViewById(R.id.btnContatos1);	
			btnContatos1.setOnClickListener(new OnClickListener(){
				public void onClick(View v) {
					Cursor cursor = getContatos();
					txtConteudo.setText("");
					while (cursor.moveToNext()) {
						String nome = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
						txtConteudo.append("Nome: ");
						txtConteudo.append(nome);
						txtConteudo.append("\n");
					}
				}	
			});
			
		    btnContatos2 = (Button) findViewById(R.id.btnContatos2);	
			btnContatos2.setOnClickListener(new OnClickListener(){
				public void onClick(View v) {
					mostraContatos();
				}	
			});	
			
			btnContatos3 = (Button) findViewById(R.id.btnContatos3);
			btnContatos3.setOnClickListener(new OnClickListener(){
					public void onClick(View v) {
						mostraContatos2();
					}	
			});	
		} //fim do onCreate
	   

		
		//Chamado através do botão btnContatos1
		private Cursor getContatos() {
			Uri contatos = ContactsContract.Contacts.CONTENT_URI;
			ContentResolver cr = getContentResolver();
			Cursor cur = cr.query(contatos, null, null, null, null);
			return cur;
		}

		//Chamado através do botão btnContatos2
		private void mostraContatos() {
			
			Uri contatos = ContactsContract.Contacts.CONTENT_URI;
			
			ContentResolver cr = getContentResolver();
			
	        Cursor cur = cr.query(contatos, null, null, null, null);
	        
	        txtConteudo.setText("");
	        
	        if (cur.getCount() > 0) {
	        	while (cur.moveToNext()) {
	      
	        		
	        		
	        		String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
	        		String nome = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
	        		String fone="";
	        		
	        		if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
	        			
	        			/* Se HAS_PHONE_NUMBER>0 significa que o contato possui telefones cadastrados
	        			 * Números de telefone são armazenados em sua própria tabela e para serem acessados
	        			 * deve ser feita uma consulta separada.
	        			 * Para consultar os telefones deve ser usada a URI armazenada na variável ContactsContract.CommonDataKinds.Phone.CONTENT_URI
	        			 * Usa-se uma cláusula WHERE para obter os números de telefone de um contato
	        			 */
	   
	                        Cursor pCur = cr.query( ContactsContract.CommonDataKinds.Phone.CONTENT_URI, 
	                        						null, 
	                        						ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?", 
	                        						new String[]{id}, null); //chave estrangeira
	         	        
	                        while (pCur.moveToNext()) {
	                        	fone = fone + pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)) +"\n";
	                        } 
	                        pCur.close();	
	        		}
	        		
	        		txtConteudo.append("id: ");
	        		txtConteudo.append(id);
	        		txtConteudo.append("\n");
	        		txtConteudo.append("Nome: ");
	        		txtConteudo.append(nome);
	        		txtConteudo.append("\n");
	        		txtConteudo.append("Fone: ");
	        		txtConteudo.append(fone);
	        		txtConteudo.append("\n\n");
	            }
	        }
		}// fim do mostraContatos
		
		private void mostraContatos2() {
			Uri contatos = ContactsContract.Contacts.CONTENT_URI;
			
			ContentResolver cr = getContentResolver();
	        Cursor cur = cr.query(contatos, null, null, null, null);
	        
	        txtConteudo.setText("");
	        
	        if (cur.getCount() > 0) {
	        	while (cur.moveToNext()) {
	        		String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
	        		String nome = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
	        		String fone="";
	        		String email="";
	        		String emailType="";
	        		
	        		if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
	                        Cursor pCur = cr.query( ContactsContract.CommonDataKinds.Phone.CONTENT_URI, 
	                        						null, 
	                        						ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?", 
	                        						new String[]{id}, null);
	         	        
	                        while (pCur.moveToNext()) {
	                        	fone = fone + pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))+"\n";
	                        } 
	                        pCur.close();	
	        		}
	        		
	        		Cursor emailCur = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, 
	        									null,
	        									ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?", 
	        									new String[]{id}, null); 
	        			while (emailCur.moveToNext()) { 

	        			    email = email + emailCur.getString(emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA)) + "\n";
	        		 	    emailType = emailType + emailCur.getString(emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.TYPE))+"\n"; 
	        		 	} 
	        		 	emailCur.close();

	        		 	txtConteudo.append("id: ");
	        		 	txtConteudo.append(id);
	        		 	txtConteudo.append("\n");
	        		
	        		 	txtConteudo.append("Nome: ");
	        		 	txtConteudo.append(nome);
	        		 	txtConteudo.append("\n");
					
	        		 	txtConteudo.append("Fone: ");
	        		 	txtConteudo.append(fone);
	        		 	txtConteudo.append("\n");
					
	        		 	txtConteudo.append("Email:");
	        		 	txtConteudo.append(email);
	        		 	txtConteudo.append("\n");
	        		 	txtConteudo.append("Email Type: ");
	        		 	txtConteudo.append(emailType);
					
	        		 	txtConteudo.append("\n\n");
					
	            }
	        }
		}

	}
