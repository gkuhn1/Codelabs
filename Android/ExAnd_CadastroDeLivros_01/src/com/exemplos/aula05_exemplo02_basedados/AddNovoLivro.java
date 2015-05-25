package com.exemplos.aula05_exemplo02_basedados;

import android.app.Activity;
import android.app.AlertDialog;
import android.database.SQLException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import com.eduardoptorezan.ExAnd_CadastroDeLivros_01.R;

public class AddNovoLivro extends Activity{
   private long idLinha; 
   private EditText txtTitulo;
   private EditText txtEditora;
   private EditText txtISBN;
   private Button btnSalvar;

   @Override
   public void onCreate(Bundle savedInstanceState){
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_addlivros); 

      txtTitulo = (EditText) findViewById(R.id.txtTitulo);
      txtEditora = (EditText) findViewById(R.id.txtEditora);
      txtISBN = (EditText) findViewById(R.id.txtISBN);
      
      Bundle extras = getIntent().getExtras(); // get Bundle of extras

      // Se há extras, usa os valores para preencher a tela
      if (extras != null){
         idLinha = extras.getLong("idLinha");
         txtTitulo.setText(extras.getString("titulo"));  
         txtEditora.setText(extras.getString("editora"));  
         txtISBN.setText(extras.getString("isbn"));  
      } // end if
    
      btnSalvar = (Button) findViewById(R.id.btnSalvar);
      btnSalvar.setOnClickListener(salvarLivroButtonClicked);
   } 

   OnClickListener salvarLivroButtonClicked = new OnClickListener(){
      public void onClick(View v){
         if (txtTitulo.getText().length() != 0){
            AsyncTask<Object, Object, Object> salvaLivroTask = new AsyncTask<Object, Object, Object>(){
                  @Override
                  protected Object doInBackground(Object... params){
                     salvaLivro(); // Salva o livro na base de dados
                     return null;
                  } // end method doInBackground
      
                  @Override
                  protected void onPostExecute(Object result){
                     finish(); // Fecha a atividade
                  } 
               };
               
            // Salva o livro no BD usando uma thread separada
            salvaLivroTask.execute((Object[]) null); 
         } // end if
         else {
            // Cria uma caixa de diálogo 
            AlertDialog.Builder builder = new AlertDialog.Builder(AddNovoLivro.this);
            builder.setTitle(R.string.tituloErro); 
            builder.setMessage(R.string.mensagemErro);
            builder.setPositiveButton(R.string.botaoErro, null); 
            builder.show(); 
         } 
      } 
   }; 

   // Salva o livro na base de dados
   private void salvaLivro(){
      DBAdapter databaseConnector = new DBAdapter(this);
      try{
    	 databaseConnector.open();
    	 //Se nenhum parâmetro foi recebido, indica que o cadastro é novo
         if (getIntent().getExtras() == null){
            databaseConnector.insereLivro(
               txtTitulo.getText().toString(),
               txtEditora.getText().toString(), 
               txtISBN.getText().toString());
         } 
         else{
            databaseConnector.alteraTitulo(idLinha,
        	       txtTitulo.getText().toString(),
                   txtEditora.getText().toString(), 
                   txtISBN.getText().toString());
         }
         databaseConnector.close();
      }catch(SQLException e){
    	 e.printStackTrace();
      }
   } 
} 
