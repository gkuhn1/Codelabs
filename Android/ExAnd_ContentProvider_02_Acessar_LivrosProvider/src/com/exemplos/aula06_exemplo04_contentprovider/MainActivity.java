package com.exemplos.aula06_exemplo04_contentprovider;

import android.net.Uri;
import android.os.Bundle;
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
	TextView txtLivros;
	Button btnConsulta;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        txtLivros = (TextView) findViewById(R.id.txtLivros);
        btnConsulta = (Button) findViewById(R.id.btnConsulta);	
        btnConsulta.setOnClickListener(new OnClickListener(){
			public void onClick(View v)
			{
				
				Cursor cursor = getDados();
				
				txtLivros.setText("");
				
				while (cursor.moveToNext()) {
					String titulo = cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1)));
					txtLivros.append("Nome: ");
					txtLivros.append(titulo);
					txtLivros.append("\n");
				}
			}	
		});
        
        
    }
    private Cursor getDados() {
		ContentResolver cr = getContentResolver();
		Cursor cur = cr.query(Uri.parse("content://com.exemplos.aula03_contentprovider/livros"), null, null, null, null);
		return cur;
	}
}