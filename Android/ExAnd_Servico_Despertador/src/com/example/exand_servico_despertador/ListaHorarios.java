package com.example.exand_servico_despertador;

import java.util.ArrayList;


import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.os.Build;

public class ListaHorarios extends ListActivity {
	
	ArrayList<Horario> lstHorarios = new ArrayList<Horario>();
	
	ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_lista_horarios);

		
		
		CarregarHorarios(true);

		setListAdapter(new ItemHorarioAdpter(this, lstHorarios)); 
	    
		lv = getListView(); //Obtem a partir do handle da lista da janela  
		
	    lv.setTextFilterEnabled(true); //mantem a lista habilitada
    	
    	//lv.setLongClickable(true); //permite o clique longo

    	/*
    	if (savedInstanceState == null)
    	{
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		*/
		    
	}
	
	@SuppressWarnings("unchecked")
	private void CarregarHorarios(Boolean abrir)
	{
		  //obtem o dado serializado do Bundle
		  //lstHorarios = (ArrayList<Horario>) getIntent().getSerializableExtra("listHorarios");
				   
		  
		  if ((lstHorarios != null) &&
			  ( lstHorarios.size() > 0)) {	

			lv.invalidateViews();
			lv.refreshDrawableState(); 
			  
			  Toast.makeText(getApplicationContext(), "Total de horário carregados: " + lstHorarios.size(), Toast.LENGTH_SHORT).show();	 
		  }
		  else
		  {
			  if (abrir)
				  lstHorarios =  new ArrayList<Horario>();
			  
			  
			  Toast.makeText(getApplicationContext(), "Nenhum horário carregado.", Toast.LENGTH_SHORT).show();
		  }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lista_horarios, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		if (id == R.id.menuAdicionarLista)
		{			
			Intent intencao = new Intent(getApplicationContext(), CadastroHorarios.class);
			Bundle extras = new Bundle();
			extras.putString("Id", "0");
			intencao.putExtras(extras);
			startActivityForResult(intencao, 1);          
		}
		else if (id == R.id.menuSairLista) {
			finish();
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
			View rootView = inflater.inflate(R.layout.fragment_lista_horarios,
					container, false);
			return rootView;
		}
	}
	
	@Override
	public void onActivityResult(int codRequisicao, int codResultado, Intent dados){
		Toast.makeText(getApplicationContext(), "Passou 1", Toast.LENGTH_SHORT).show();
    	if(codRequisicao == 1){
    		Toast.makeText(getApplicationContext(), "Passou 2", Toast.LENGTH_SHORT).show();
    		if(codResultado == RESULT_OK){
    			Toast.makeText(getApplicationContext(), "Passou 3", Toast.LENGTH_SHORT).show();			
    			Bundle extras = dados.getExtras();
    			
    			if(extras!=null){
    				Toast.makeText(getApplicationContext(), "Passou 4", Toast.LENGTH_SHORT).show();	
		    		try {    				
	    				
		    			Horario newCd		  = new Horario();	    				
		    			newCd.Horario		  = extras.getString("HORARIO");
		    			
	    				
	    				lstHorarios.add(newCd);
	    				Toast.makeText(getApplicationContext(), "Passou 5", Toast.LENGTH_SHORT).show();	
	    				CarregarHorarios(false);
		    		}
    				catch (Exception ex) {
    					//e.printStackTrace();
		    		}
    			} //if(extras!=null){
    		}
    	}
	}

}
