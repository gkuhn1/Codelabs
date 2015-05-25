package com.exemplos.aula05_exemplo02_basedados;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import com.eduardoptorezan.ExAnd_CadastroDeLivros_01.R;

public class ViewLivros extends Activity 
{
   private long idLinha; 
   private TextView lblTitulo; 
   private TextView lblEditora;
   private TextView lblISBN;

   @Override
   public void onCreate(Bundle savedInstanceState){
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_livros);

      lblTitulo = (TextView) findViewById(R.id.lblTitulo);
      lblEditora = (TextView) findViewById(R.id.lblEditora);
      lblISBN = (TextView) findViewById(R.id.lblISBN);

      Bundle extras = getIntent().getExtras();
      idLinha = extras.getLong(MainActivity.LINHA_ID); 
   } 
   
   @Override
   protected void onResume(){
      super.onResume();
      new CarregaLivroTask().execute(idLinha);
   } 
   
   // Executa a consulta em uma thead separada
   private class CarregaLivroTask extends AsyncTask<Long, Object, Cursor>{
      DBAdapter databaseConnector = new DBAdapter(ViewLivros.this);

      @Override
      protected Cursor doInBackground(Long... params){
         databaseConnector.open();
         return databaseConnector.getLivro(params[0]);
      } 
      // Usa o Cursor retornado do método doInBackground
      @Override
      protected void onPostExecute(Cursor result) {
         super.onPostExecute(result);
   
         result.moveToFirst(); 
         
         int tituloIndex = result.getColumnIndex("titulo");
         int editoraIndex = result.getColumnIndex("editora");
         int isbnIndex = result.getColumnIndex("isbn");

         lblTitulo.setText(result.getString(tituloIndex));
         lblEditora.setText(result.getString(editoraIndex));
         lblISBN.setText(result.getString(isbnIndex));
         result.close(); 
         databaseConnector.close(); 
      } 
   } 
      
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
	   getMenuInflater().inflate(R.menu.activity_viewlivros, menu);
       return true; 
   } 
   
   @Override
   public boolean onOptionsItemSelected(MenuItem item){
      switch (item.getItemId()){
         case R.id.editItem:
            Intent addEditLivro = new Intent(this, AddNovoLivro.class);
            
            addEditLivro.putExtra(MainActivity.LINHA_ID, idLinha);
            addEditLivro.putExtra("titulo", lblTitulo.getText());
            addEditLivro.putExtra("editora", lblEditora.getText());
            addEditLivro.putExtra("isbn", lblISBN.getText());
 
            startActivity(addEditLivro); 
            return true;
         case R.id.deleteItem:
            deleteLivro(); 
            return true;
         default:
            return super.onOptionsItemSelected(item);
      } 
   } 
   
   private void deleteLivro(){
      
      AlertDialog.Builder builder = new AlertDialog.Builder(ViewLivros.this);

      builder.setTitle(R.string.confirmaTitulo); 
      builder.setMessage(R.string.confirmaMensagem); 

      // provide an OK button that simply dismisses the dialog
      builder.setPositiveButton(R.string.botao_delete,
         new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int button){
               final DBAdapter databaseConnector = new DBAdapter(ViewLivros.this);

               // create an AsyncTask that deletes the contact in another 
               // thread, then calls finish after the deletion
               AsyncTask<Long, Object, Object> deleteTask =
                  new AsyncTask<Long, Object, Object>(){
                     @Override
                     protected Object doInBackground(Long... params){
                    	 try{
                    		 databaseConnector.open();
                    		 databaseConnector.excluiTitulo(params[0]);
                    		 databaseConnector.close();
                    	 }
                         catch(SQLException e){
                        	 e.printStackTrace();
                         }
                        return null;
                     } // end method doInBackground

                     @Override
                     protected void onPostExecute(Object result){
                        finish(); 
                     } // end method onPostExecute
                  }; // end new AsyncTask

               // execute the AsyncTask to delete contact at rowID
               deleteTask.execute(new Long[] { idLinha });               
            } // end method onClick
         } // end anonymous inner class
      ); // end call to method setPositiveButton
      
      builder.setNegativeButton(R.string.botao_cancel, null);
      builder.show(); // display the Dialog
   } // end method deleteContact

} 