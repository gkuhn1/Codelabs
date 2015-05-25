package com.exemplos.aula06_exemplo03_contentprovider;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {
	    /** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        
	      //--- Adiciona um Livro ---
	        ContentValues dados = new ContentValues();
	        dados.put(LivrosProvider.KEY_TITULO, "O Homem que Mudou o Céu");
	        dados.put(LivrosProvider.KEY_EDITORA,"Planeta");
	        dados.put(LivrosProvider.KEY_ISBN, "987-85-7665-546-6");        
	        Uri uri = getContentResolver().insert(LivrosProvider.CONTENT_URI, dados);
	               
	        //--- Adiciona um Livro ---
	        dados.clear();
	        dados.put(LivrosProvider.KEY_TITULO, "O Mestre dos Quebra-Cabeças");
	        dados.put(LivrosProvider.KEY_EDITORA,"Planeta");
	        dados.put(LivrosProvider.KEY_ISBN, "978-85-7665-615-9");        
	        uri = getContentResolver().insert(LivrosProvider.CONTENT_URI, dados);
	        
	        //--- Adiciona um Livro ---
	        dados.clear();
	        dados.put(LivrosProvider.KEY_TITULO, "teste");
	        dados.put(LivrosProvider.KEY_EDITORA,"teste");
	        dados.put(LivrosProvider.KEY_ISBN, "123-456");        
	        uri = getContentResolver().insert(LivrosProvider.CONTENT_URI, dados);

	        //--- Consulta um Livro ---
	        Uri allTitles = Uri.parse("content://"+ LivrosProvider.PROVIDER_NAME + "/livros");
	        ContentResolver cr = getContentResolver();
	        Cursor c = cr.query(allTitles, null, null, null, "titulo desc");
	        if (c.moveToFirst()) {
	           do{
	        	  Toast.makeText(this, 
	        	            c.getString(c.getColumnIndex(LivrosProvider.KEY_ROWID)) + ", " +                     
	        	            c.getString(c.getColumnIndex(LivrosProvider.KEY_TITULO)) + ", " + 
	        	            c.getString(c.getColumnIndex(LivrosProvider.KEY_EDITORA)) + ", " + 
	        	            c.getString(c.getColumnIndex(LivrosProvider.KEY_ISBN)), 
	        	            Toast.LENGTH_LONG).show();               
	        	} while (c.moveToNext());
	        }

	        
	   //para EDITAR
	       // ContentValues dados = new ContentValues();
/*	        dados.clear();
	        dados.put(LivrosProvider.KEY_TITULO, "Android para Programadores");
	        dados.put(LivrosProvider.KEY_EDITORA,"Bookmann");
	        dados.put(LivrosProvider.KEY_ISBN, "978-85-407-0210-3");
	        cr.update(Uri.parse("content://"+ LivrosProvider.PROVIDER_NAME + "/livros/5"),  dados, null, null);
*/	 	  
 	  
	        //para deletar
	       /* getContentResolver().delete(
	        	      Uri.parse(
	        	         "content://"+ LivrosProvider.PROVIDER_NAME + "/livros" + "/2"), 
	        	         null, null);
	       */
	       //deletar todos
	     /*  getContentResolver().delete(
	        	      Uri.parse("content://"+ LivrosProvider.PROVIDER_NAME + "/livros"), 
	        	         null, null);
		*/	 
	    }
	}