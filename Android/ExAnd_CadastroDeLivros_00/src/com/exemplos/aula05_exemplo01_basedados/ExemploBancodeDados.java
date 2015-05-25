package com.exemplos.aula05_exemplo01_basedados;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

public class ExemploBancodeDados extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	long id;
    	Cursor cursor;
    	boolean alterou;
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        DBAdapter db = new DBAdapter(this); 
        
//********************************
//--- adiciona livros---
        db.open();        
        id = db.insereLivro("Livro 1","Autor 1", "111-85-666-0210-3");
        id = db.insereLivro("Livro 2", "Autor 2", "222-85-333-0183-0");
        id = db.insereLivro("Livro 3", "Autor 3", "85-9999-09-2");
        id = db.insereLivro("titulo", "editora", "isbn");
        db.close();
     
//********************************
//--- obtém todos os livros ---
        db.open();
        cursor = db.getTodosTitulos();
        if (cursor.moveToFirst() == true){
            do{          
                mostraLivro(cursor);
            }while (cursor.moveToNext());
        }
        db.close();
//*******************************************************************
//--- obtém um livro ---
        db.open();
        cursor = db.getLivro(2); //objeto c declarado 
        if (cursor.moveToFirst())        
           mostraLivro(cursor);
        else
            Toast.makeText(this, "Livro nao encontrado!", Toast.LENGTH_LONG).show();
        db.close();
//*******************************************************************
        //--- exclui um livro ---
     /*   db.open();
        if (db.excluiTitulo(1))
            Toast.makeText(this, "Exclusao OK!",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Nao foi possivel excluir!!",Toast.LENGTH_LONG).show();            
        db.close();
    */
//*******************************************************************
        //--- altera um livro ---
        db.open();
        alterou = db.alteraTitulo(4,"Livro 44444","Autor 4444","44-4444-4444-4");   
        if (alterou == true)
            Toast.makeText(this, "Alteracao OK!",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Nao foi possivel alterar!!",Toast.LENGTH_LONG).show();
        
        //--- Consulta o livro alterado ---
        cursor = db.getLivro(4);
        if (cursor.moveToFirst())        
            mostraLivro(cursor);
        else
            Toast.makeText(this, "Livro nao encontrado!!",Toast.LENGTH_LONG).show();        
        //-------------------        
        db.close();
      
    }
    
    public void mostraLivro(Cursor cursor){
    	String livro = "Id: " + cursor.getString(0) + "\n";
    	livro = livro + "Título: " + cursor.getString(1) + "\n";
    	livro = livro + "Editora: " +cursor.getString(2) + "\n";
    	livro = livro + "ISBN: " + cursor.getString(3) + "\n";
    	
        Toast.makeText(this,livro,Toast.LENGTH_LONG).show();        
    } 
}