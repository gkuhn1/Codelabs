package com.eduardoptorezan.exand_bibliotecamusical;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.os.Build;

public class ListarMusicos extends ListActivity {
	
	ArrayList<Musico> lstMusicos = new ArrayList<Musico>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_listar_musicos);

		 CarregarMusicos();               			                  

		  //Está disponível porque esta classe extende o ListActivity, dessa forma, setamos a fonte dos dados;
		    setListAdapter(new ItemMusicoAdapter(this, lstMusicos)); 
		    
		    ListView lv = getListView(); //Obtem a partir do handle da lista da janela  
			
	    	lv.setTextFilterEnabled(true); //mantem a lista habilitada
	    	
	    	lv.setLongClickable(true); //permite o clique longo
	    	
	    	//implementação do clique longo no item
	    	lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
	    	    public boolean onItemLongClick(AdapterView<?> av, View v, int pos, long id) {
	    	    	
	    	        return true;
	    	    }
	    	});

	    	//implementação do clique longo na lista
	    	lv.setOnLongClickListener(new OnLongClickListener() {    		    		
	            //@Override
	            public boolean onLongClick(View v) {
	                // TODO Auto-generated method stub            	
	                return true;
	            }
	        });
	    	
	    	//implementação do clique curto nos itens da lista
	    	lv.setOnItemClickListener(new OnItemClickListener(){
	    		
	    		public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
	    			
	    			// When clicked, show a toast with the TextView text
	    		}
	    	});
	}

	public void CarregarMusicos()
	{
	//obtem o dado serializado do Bundle
	  lstMusicos = (ArrayList<Musico>) getIntent().getSerializableExtra("listaMusicos");
	  
	  if ((lstMusicos != null) &&
		  ( lstMusicos.size() > 0)) {					 
		  Toast.makeText(getApplicationContext(), "Total de músicos carregados: " + lstMusicos.size(), Toast.LENGTH_SHORT).show();	 
	  }
	  else
	  {
		  Toast.makeText(getApplicationContext(), "Nenhum músico carregado.", Toast.LENGTH_SHORT).show();
	  }
   } //CarregarCarros

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.listar_musicos, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_listar_musicos,
					container, false);
			return rootView;
		}
	}

}
