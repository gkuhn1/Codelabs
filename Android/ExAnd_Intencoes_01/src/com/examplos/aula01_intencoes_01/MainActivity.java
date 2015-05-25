package com.examplos.aula01_intencoes_01;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button btnBrowser, btnFone, btnMaps, btnContatos;
	int codigoRequisicao = 1;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btnBrowser = (Button) findViewById(R.id.btnBrowser);
        btnFone = (Button) findViewById(R.id.btnFone);
        btnMaps = (Button) findViewById(R.id.btnMaps);
        btnContatos = (Button) findViewById(R.id.btnContatos);
        
        btnBrowser.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Intent i = new Intent(android.content.Intent.ACTION_VIEW,
						Uri.parse("http://www.google.com.br"));
				startActivity(i);
			}
		});
        btnFone.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
			//O número pode ser omitido
				Intent i = new Intent(android.content.Intent.ACTION_DIAL,
						Uri.parse("tel:+651234567"));
				startActivity(i);
				
			}
		}); 
        btnMaps.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Intent i = new Intent(android.content.Intent.ACTION_VIEW,
						Uri.parse("geo:40.747778, -73.985556"));
				startActivity(i);
			}
		});
        btnContatos.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Intent i = new Intent(android.content.Intent.ACTION_PICK);
				i.setType(ContactsContract.Contacts.CONTENT_TYPE);
				startActivityForResult(i, codigoRequisicao);
			}
		});
        
    }//fecha o onCreate
    
    public void onActivityResult(int codRequisicao, int codResultado, Intent dados){
    	if(codRequisicao == codigoRequisicao){
    		if(codResultado == RESULT_OK){
    					
    			Toast.makeText(this, dados.getData().toString(),Toast.LENGTH_LONG).show();
    			Intent i = new Intent(android.content.Intent.ACTION_VIEW,
    								  Uri.parse(dados.getData().toString()));
    			startActivity(i);
    		}
    	}
    }

   
}
