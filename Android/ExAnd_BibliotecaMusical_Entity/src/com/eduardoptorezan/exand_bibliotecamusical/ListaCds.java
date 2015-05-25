package com.eduardoptorezan.exand_bibliotecamusical;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.ListActivity;
import android.database.Cursor;
import android.database.SQLException;
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

public class ListaCds extends ListActivity{
	
	ArrayList<CD> lstCds = new ArrayList<CD>();

	@Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	super.onCreate(savedInstanceState);
    	
    	//Como é uma lista, não devemos setar uma activity para executar, uma vez que a classe já implementa o necessário, que pode ser obtido pelo método getListView (usado à baixo) 
        //setContentView(R.layout.lista_cds);                
        
	    CarregarCds(); //Carrega lista de cds recebidos por parâmetros              			                  

	  //Está disponível porque esta classe extende o ListActivity, dessa forma, setamos a fonte dos dados;
	    setListAdapter(new ItemCDAdapter(this, lstCds)); 
	    
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
            
    } //OnCreate
	
	public void CarregarCds()
	{
	//obtem o dado serializado do Bundle
	  lstCds = (ArrayList<CD>) getIntent().getSerializableExtra("listaCds");
	  
	  if ((lstCds != null) &&
		  ( lstCds.size() > 0)) {					 
		  Toast.makeText(getApplicationContext(), "Total de cds carregados: " + lstCds.size(), Toast.LENGTH_SHORT).show();	 
	  }
	  else
	  {
		  Toast.makeText(getApplicationContext(), "Nenhum cds carregado.", Toast.LENGTH_SHORT).show();
	  }
   } //CarregarCarros

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lista_cds, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_lista_cds,
					container, false);
			return rootView;
		}
	}

}
