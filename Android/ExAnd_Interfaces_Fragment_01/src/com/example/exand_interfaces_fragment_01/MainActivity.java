package com.example.exand_interfaces_fragment_01;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.os.Build;

public class MainActivity extends FragmentActivity {

	
	//Declarar para pode acessar os fragments criados
	//Ao adicionar o import do FragmentManager,
	//pegar a segunda opção, ou à que corresponder ao v4
	FragmentManager fn = getSupportFragmentManager();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		String[] lista = new String[] {"Fragment 1", "Altera Texto Fragment 1", "Fragment 2", "Fragment 3"};
		ArrayAdapter<String> adpter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista); 
		
		ListView lv = (ListView)findViewById(R.id.listView1);
		lv.setAdapter(adpter);
		lv.setOnItemClickListener(new OnItemClickListener()
		{
			
			@Override
			public void onItemClick(AdapterView<?> lv, View view, int position, long id)
			{
				if ((position == 1)) {				
					Fragment1 frag1 = (Fragment1)fn.findFragmentById(R.id.fragment1);
					frag1.alterarTextView("Alterando o texto");
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

}
