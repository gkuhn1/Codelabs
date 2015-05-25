package com.eduardoptorezan.exand_olamundo;


import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity {
	
	final String fsTagLog = "AulaAndroid"; 
	
	
	Button button1, button2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.buttonDois);
		
		button1.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) {
    			
    			Toast.makeText(getApplicationContext(), "Clicou no botão 1",Toast.LENGTH_LONG).show();
    			
    		}
    	});
		
		button2.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) {
    			
    			int i = 0;
    			String s = "teste";
    			
    			try {
    			
	    			Toast.makeText(getApplicationContext(), "Veja o LogCat",Toast.LENGTH_LONG).show();
	    			
	    			Log.d(fsTagLog, "Clicou no botão 1");
	    			
	    			i = Integer.parseInt(s);
    			}
    			catch (Exception ex)
    			{
    				Log.d(fsTagLog, "ERRO: " + ex.getMessage());
    			}
    			
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
