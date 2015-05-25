package com.eduardoptorezan.exand_bibliotecamusical;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class CadastrarCDs extends ActionBarActivity {
	
	Notificacoes notific = new Notificacoes();
	Spinner  spnGenero;
	TextView edtNomeDoAlbum;
	TextView edtAno;
	TextView edtCompositor;
	TextView edtGravadora;
	TextView edtObservacoes;
	Button   btnConfirmar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_cds);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		//Construindo o objeto spinner, populando os dados e atribuindo eventos.
		spnGenero		 = (Spinner)  findViewById(R.id.spnGenero);
		edtNomeDoAlbum	 = (TextView) findViewById(R.id.edtNomeDoAlbum);
		edtAno			 = (TextView) findViewById(R.id.edtAno);
		edtCompositor	 = (TextView) findViewById(R.id.edtCompositor);
		edtGravadora	 = (TextView) findViewById(R.id.edtGravadora);
		edtObservacoes	 = (TextView) findViewById(R.id.edtObservacoes);
		btnConfirmar	 = (Button)   findViewById(R.id.btnConfirmar);
		
		CarregarDadosInterface();
		
		
		spnGenero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
		        // your code here
		    	String strTextoSelecioado = spnGenero.getSelectedItem().toString();
				notific.NotificarPub(getApplicationContext(), enumTipoNotificacao.Toast, strTextoSelecioado);
		    }

		    @Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		        // your code here
		    }

		});
		
		btnConfirmar.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) {
    			CD novoCD = new CD();
    			
    			novoCD.NomeDoAlbum	 = edtNomeDoAlbum	.getText().toString();
    			novoCD.Ano			 = edtAno		 	.getText().toString();
    			novoCD.Compositor	 = edtCompositor	.getText().toString();
    			novoCD.Gravadora	 = edtGravadora		.getText().toString();
    			novoCD.Observacoes	 = edtObservacoes	.getText().toString();
    			
    			String sMsg = "Álbum: " + novoCD.NomeDoAlbum + " - Ano: " + novoCD.Ano;
    			
    			notific.NotificarPub(getApplicationContext(), enumTipoNotificacao.Toast, sMsg);
    					
    		}
    	});
		
	}
	
	private void CarregarDadosInterface() {
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
				R.array.lstGerenosMusicais, android.R.layout.simple_spinner_item);
		
		// Determinando o formado para usar quando a lista é exibida
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);		
		// Aplicando o adapter ao spinner
		spnGenero.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastrar_cds, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_cadastrar_cds,
					container, false);
			return rootView;
		}
	}

}
