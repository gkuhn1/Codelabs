package com.example.exand_interfaces_dialogs_01;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	Button btnAlertBox, btnProgresso;
	final Context context = this;
	
	private String pi_string;
    private TextView tv;
    private ProgressDialog pd;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnAlertBox = (Button) findViewById(R.id.btnAlertBox);
		btnProgresso = (Button) findViewById(R.id.btnProgresso);
		 
		// add button listener
		btnAlertBox.setOnClickListener(new OnClickListener() {
 
		@Override
		public void onClick(View arg0) {
 
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);
 
			// define o título
			alertDialogBuilder.setTitle("Dialog de alerta");
 
			// define a dialog
			alertDialogBuilder
				.setMessage("Clique em SIM para sair")
				.setCancelable(false)
				.setPositiveButton("Sim",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						MainActivity.this.finish();
					}
				  })
				.setNegativeButton("Não",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						dialog.cancel();
					}
				});
 
				// Cria a janela
				AlertDialog alertDialog = alertDialogBuilder.create(); 
				// Exibe
				alertDialog.show();
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
