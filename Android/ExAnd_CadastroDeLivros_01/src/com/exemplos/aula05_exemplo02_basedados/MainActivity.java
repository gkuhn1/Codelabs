package com.exemplos.aula05_exemplo02_basedados;
import android.os.AsyncTask;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import com.eduardoptorezan.ExAnd_CadastroDeLivros_01.R;

public class MainActivity extends ListActivity{
	public static final String LINHA_ID = "idLinha"; 
	private ListView livrosListView; //
	private CursorAdapter livrosAdapter; // Adaptador para a ListView
	   
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState){
	   super.onCreate(savedInstanceState); 
	   livrosListView = getListView(); 
	   livrosListView.setOnItemClickListener(viewLivrosListener);      

	   // mapeia cada coluna da tabela com um componente da tela
	   String[] origem = new String[]{"titulo","editora","isbn"};
	   int[] destino = new int[] { R.id.txtTitulo, R.id.txtEditora, R.id.txtISBN };
	   int flags = 0;
	      
	   livrosAdapter = new SimpleCursorAdapter(MainActivity.this,R.layout.activity_viewlivros,null,origem,destino,flags);
	   setListAdapter(livrosAdapter); // set contactView's adapter
	} 
	@Override
	protected void onResume(){
	//sempre que executar onResume, irá fazer uma busca no banco de dados
	//e vai atualizar a tela de exibição dos livros cadastrados
	   super.onResume(); 
	   new ObtemLivros().execute((Object[]) null);
	} 
////////////////////////////////////////////////////////////
	// Quando precisamos dos resultados de uma operação do BD na thread da 
	// interface gráfica, vamos usar AsyncTask para efetuar a operação em
	// uma thread e receber os resultados na thread da interface gráfica
	private class ObtemLivros extends AsyncTask<Object, Object, Cursor>{
	      DBAdapter databaseConnector = new DBAdapter(MainActivity.this);
	      @Override
	      protected Cursor doInBackground(Object... params){
	         databaseConnector.open(); //abre a base de dados
	         return databaseConnector.getTodosTitulos(); //retorna todos os livros 
	      } // end method doInBackground

	      // use the Cursor returned from the doInBackground method
	      @Override
	      protected void onPostExecute(Cursor result){
	         livrosAdapter.changeCursor(result); //altera o cursor para um novo cursor
	         databaseConnector.close();
	      } // end method onPostExecute
	   } // end class GetContactsTask
///////////////////////////////////////////////////////////
    @Override
    //exibe o menu "Adicionar um novo livro"
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
       //Cria uma intenção para executar o cadastramento de um novo livro
       Intent addNovoLivro = new Intent(MainActivity.this, AddNovoLivro.class);
       startActivity(addNovoLivro);
       return super.onOptionsItemSelected(item); 
    }

    //Quando o usuário clica em uma linha da consulta, uma nova intenção
    //é exeutada, para exibir os dados do livro selecionado 
    OnItemClickListener viewLivrosListener = new OnItemClickListener(){
       public void onItemClick(AdapterView<?> parent, View view, int posicao,long id){
          Intent viewLivros = new Intent(MainActivity.this, ViewLivros.class);
          viewLivros.putExtra(LINHA_ID, id);
          startActivity(viewLivros); 
       } // end method onItemClick
    }; // end viewContactListener
}
