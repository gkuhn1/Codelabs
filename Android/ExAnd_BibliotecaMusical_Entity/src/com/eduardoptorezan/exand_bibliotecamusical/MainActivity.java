package com.eduardoptorezan.exand_bibliotecamusical;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	Button btnCadastrarCd;
	Button btnListarCds;
	Button btnCadastrarMusico;
	Button btnListarMusicos;
	int codigoRequisicaoCds = 1;
	int codigoRequisicaoMusico = 2;
	Notificacoes notif = new Notificacoes();
	private ArrayList<CD> lstCds = new ArrayList<CD>();
	private ArrayList<Musico> lstMusicos = new ArrayList<Musico>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		
		btnCadastrarCd = (Button) findViewById(R.id.btnCadastrarCd);
		btnListarCds    = (Button) findViewById(R.id.btnListarCDs);
		btnCadastrarMusico = (Button) findViewById(R.id.btnCadastrarMusico);
		btnListarMusicos    = (Button) findViewById(R.id.btnListarMusicos);
		
		btnCadastrarCd.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
							
				Intent intencao = new Intent(getApplicationContext(), CadastrarCDs.class);
				Bundle extras = new Bundle();
				extras.putString("nadaAPassar", "0");
				intencao.putExtras(extras);
				startActivityForResult(intencao, codigoRequisicaoCds);
			}
		});
		
		btnCadastrarMusico.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
							
				Intent intencao = new Intent(getApplicationContext(), ActivityCadastrarMusico.class);
				Bundle extras = new Bundle();
				extras.putString("nadaAPassar", "0");
				intencao.putExtras(extras);
				startActivityForResult(intencao, codigoRequisicaoMusico);
			}
		});
		
		btnListarCds.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				Intent intencao = new Intent(getApplicationContext(), ListaCds.class);
				
				Bundle extras = new Bundle();
				extras.putSerializable("listaCds", lstCds);
				intencao.putExtras(extras);
				
				startActivity(intencao);
				
			}
		});
		
		btnListarMusicos.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				Intent intencao = new Intent(getApplicationContext(), ListarMusicos.class);
				
				Bundle extras = new Bundle();
				extras.putSerializable("listaMusicos", lstMusicos);
				intencao.putExtras(extras);
				
				startActivity(intencao);
				
			}
		});
	}
	
	public void onActivityResult(int codRequisicao, int codResultado, Intent dados){
    	if(codRequisicao == codigoRequisicaoCds){
    		if(codResultado == RESULT_OK){
    					
    			Bundle extras = dados.getExtras();
    			
    			if(extras!=null){
    				
		    		try {    				
	    				
		    			CD newCd		  = new CD();	    				
		    			newCd.Ano		  = extras.getString("Ano");
		    			newCd.Compositor  = extras.getString("Compositor");
		    			newCd.Gravadora   = extras.getString("Gravadora");
		    			newCd.NomeDoAlbum = extras.getString("NomeDoAlbum");
		    			newCd.Observacoes = extras.getString("Observacoes");
	    				
	    				lstCds.add(newCd);	    			
		    		}
    				catch (Exception ex) {
    					//e.printStackTrace();
		    		}
    			} //if(extras!=null){
    		}
    	}
    	else
    		if(codRequisicao == codigoRequisicaoMusico){
        		if(codResultado == RESULT_OK){
        					
        			Bundle extras = dados.getExtras();
        			
        			if(extras!=null){
        				
    		    		try {    				
    	    				
    		    			Musico newMusico		  = new Musico();	    				
    		    			newMusico.Nome		  = extras.getString("Nome");    		    			
    	    				
    	    				lstMusicos.add(newMusico);    	    			
    		    		}
        				catch (Exception ex) {
        					//e.printStackTrace();
    		    		}
        			} //if(extras!=null){
        		}
        	}
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
