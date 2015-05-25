package com.exemplos.aula06_exemplo03_contentprovider;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class LivrosProvider extends ContentProvider{
	public static final String PROVIDER_NAME = "com.exemplos.aula03_contentprovider";
	public static final Uri CONTENT_URI = Uri.parse("content://"+ PROVIDER_NAME + "/livros");
	      
	public static final String KEY_ROWID = "_id";
	public static final String KEY_TITULO = "titulo";
	public static final String KEY_EDITORA = "editora";
	public static final String KEY_ISBN = "isbn";
	      
	private static final int LIVROS = 1;
	private static final int LIVRO_ID = 2;   
	         
	private static final UriMatcher uriMatcher;
	static{
	   uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	   uriMatcher.addURI(PROVIDER_NAME, "livros", LIVROS);
	   uriMatcher.addURI(PROVIDER_NAME, "livros/#", LIVRO_ID);      
	}

	   //---for database use---
	   private SQLiteDatabase livrosDB;
	   private static final String DATABASE_NAME = "Exemplo01BD";
	   private static final String DATABASE_TABLE = "livros";
	   private static final int DATABASE_VERSION = 1;
	  
	   private static final String CRIA_DATABASE = "create table livros " +
               "(_id integer primary key autoincrement, " +
               " titulo text not null," +
               " editora text not null," +
               " isbn text not null);" ;
	   
	   private static class DatabaseHelper extends SQLiteOpenHelper{
	      DatabaseHelper(Context context) {
	         super(context, DATABASE_NAME, null, DATABASE_VERSION);
	      }

	      @Override
	      public void onCreate(SQLiteDatabase db){
	         db.execSQL(CRIA_DATABASE);
	      }

	      @Override
	      public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    	  db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
	          onCreate(db);
	      }
	   }
	
	
   @Override
   public int delete(Uri uri, String selecao, String[] valores) {
	   //exclui um registro no cp
	      // arg0 = uri 
	      // arg1 = selection
	      // arg2 = selectionArgs
	      int count=0;
	      switch (uriMatcher.match(uri)){
	         case LIVROS:
	            count = livrosDB.delete(DATABASE_TABLE,selecao,valores);
	            break;
	         case LIVRO_ID:
	            String id = uri.getPathSegments().get(1);
	            count = livrosDB.delete(DATABASE_TABLE, KEY_ROWID + " = " + id + (!TextUtils.isEmpty(selecao) ? " AND (" + selecao + ')' : ""),valores);
	            break;
	         default: throw new IllegalArgumentException("URI DESCONHECIDA " + uri);    
	      }       
	      getContext().getContentResolver().notifyChange(uri, null);
	      return count;      

   }

   @Override
   public String getType(Uri uri) {
	   
	   switch (uriMatcher.match(uri)){
       //---get all books---
       case LIVROS:
          return "vnd.android.cursor.dir/vnd." + PROVIDER_NAME + "/livros" + " ";
       //---get a particular book---
       case LIVRO_ID:                
          return "vnd.android.cursor.item/vnd." + PROVIDER_NAME + "/livros" + " ";
       default:
          throw new IllegalArgumentException("Unsupported URI: " + uri);        
       }  
   }

 //  @Override
   public Uri insert(Uri uri, ContentValues values) {
	   //insere um novo registro usando um ContentProvider
	   long idLinha = livrosDB.insert(DATABASE_TABLE, "", values);
	   
	   if (idLinha > 0){
	         Uri _uri = ContentUris.withAppendedId(CONTENT_URI, idLinha);
	         getContext().getContentResolver().notifyChange(_uri, null);    
	         return _uri;                
	      }        
	      throw new SQLException("Falha na inserção da linha na uri: " + uri);
   }

   @Override
   public boolean onCreate() {
	   //chamado quando o provider está sendo iniciado
	   Context context = getContext();
	   DatabaseHelper dbHelper = new DatabaseHelper(context);
	   livrosDB = dbHelper.getWritableDatabase();
	   return (livrosDB == null)? false:true;
   }

   @Override
   public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
	   //recebe uma consulta do cliente e retorna um cursor
	   
	   SQLiteQueryBuilder sqlBuilder = new SQLiteQueryBuilder();
	   sqlBuilder.setTables(DATABASE_TABLE);
	           
	   if (uriMatcher.match(uri) == LIVRO_ID)
	   //---if getting a particular book---
	      sqlBuilder.appendWhere(KEY_ROWID + " = " + uri.getPathSegments().get(1));                
	           
	      if (sortOrder==null || sortOrder=="")
	             sortOrder = KEY_TITULO;
	       
	      Cursor c = sqlBuilder.query(
	             livrosDB, 
	             projection, 
	             selection, 
	             selectionArgs, 
	             null, 
	             null, 
	             sortOrder);
	       
	      //---register to watch a content URI for changes---
	      c.setNotificationUri(getContext().getContentResolver(), uri);
	      return c;
   }

   @Override
   public int update(Uri uri, ContentValues values, String selection,String[] selectionArgs) {
	   //atualiza um registro no cp
	   int count = 0;
	      switch (uriMatcher.match(uri)){
	         case LIVROS:
	            count = livrosDB.update(
	               DATABASE_TABLE, 
	               values,
	               selection, 
	               selectionArgs);
	            break;
	         case LIVRO_ID:                
	            count = livrosDB.update(
	               DATABASE_TABLE, 
	               values,
	               KEY_ROWID + " = " + uri.getPathSegments().get(1) + 
	               (!TextUtils.isEmpty(selection) ? " AND (" + 
	                  selection + ')' : ""), 
	                selectionArgs);
	            break;
	         default: throw new IllegalArgumentException(
	            "Unknown URI " + uri);    
	      }       
	      getContext().getContentResolver().notifyChange(uri, null);
	      return count;

   }
}
 
