package com.eduardoptorezan.exand_bibliotecamusical;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.os.Build;

public class ActivityCadastrarMusico extends ActionBarActivity {
	
	Notificacoes notific = new Notificacoes();
	TextView edtNome;	
	Button   btnConfirmarMusicos;
	long idNadaAPassar = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_cadastrar_musico);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		edtNome	 = (TextView) findViewById(R.id.edtNomeMusico);		
		btnConfirmarMusicos	 = (Button) findViewById(R.id.btnConfirmarMusico);
		
		Bundle extras = getIntent().getExtras(); // get Bundle of extras

		
        if (extras != null){     
        	
        	extras.putString("RETORNO", String.valueOf(false));
        	
        	if ((extras.getString("nadaAPassar") != null) &&
        	    (extras.getString("nadaAPassar") != "")) {
        		idNadaAPassar = extras.getLong("nadaAPassar");
        	}
        }
        
        
        btnConfirmarMusicos.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) {
    			try
    			{
	    			Musico novoMusico = new Musico();
	    			
	    			novoMusico.Nome	 = edtNome	.getText().toString();	    			
	    			
	    			String sMsg = "Nome: " + novoMusico.Nome;
	    			
	    			notific.NotificarPub(getApplicationContext(), enumTipoNotificacao.Toast, sMsg);
	    			
	    			//RETORNANDO DADOS
	    	    	Intent data = new Intent();        
	    	        Bundle extras = new Bundle();
	    	        
	    	        extras.putString("RETORNO", String.valueOf(true));
	    	        extras.putString("Nome"	  , novoMusico.Nome.toString());	    	        
	    	        data.putExtras(extras);        
	    	        
	    	        if (getParent() == null) {
	    	           setResult(Activity.RESULT_OK, data);          
	    	           
	    	        } else {
	    	           getParent().setResult(Activity.RESULT_OK, data);
	    	        }
	    	        //RETORNANDO DADOS
    			}
    			finally {
    				finish(); 
    			}
    		}
    	});
    	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_cadastrar_musico, menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.fragment_activity_cadastrar_musico, container,
					false);
			return rootView;
		}
	}

}
