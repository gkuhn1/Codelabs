package com.example.exand_interfaces_fragment_01;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
		
		/*Precisa testar se é igual à nulo
		 * pois quando a tela gira, por exemplo
		 * é executado o onCreate e nesse caso
		 * é re-executado este método e acaba readicionado o fragment1
		 * casusando um progrma de interface.
		 * */
		if ((savedInstanceState == null))
		{
			//Trabalhando dinamicamente com os fragments
			Fragment1 frag1 = new Fragment1();
			//Fragment2 frag2 = new Fragment2();
			//Fragment3 frag3 = new Fragment3();
			
			//Gerencia os fragments criados, trabalhando dinamicamente!
			//Importar do V4
			//deve ser dentro do IF, pois dá o commit apenas uma vez
			FragmentTransaction ft = fn.beginTransaction();
			//parametro 1, identificador do view group onde colocaremos o fragment
			//arametro 2, fragment a ser adicionado
			ft.add(R.id.layout_direito, frag1, "frag1");
			ft.commit();
		}
		
		
		String[] lista = new String[] {"Fragment 1", "Altera Texto Fragment 1", "Fragment 2", "Fragment 3"};
		ArrayAdapter<String> adpter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista); 
		
		ListView lv = (ListView)findViewById(R.id.listView1);
		lv.setAdapter(adpter);
		lv.setOnItemClickListener(new OnItemClickListener()
		{
			/*lv é a ListView
			 * A view que recebeu o cli
			 * a posição que recebeu o click
			 * o ID que recebeu o click
			 * */
			@Override
			public void onItemClick(AdapterView<?> lv, View view, int position, long id)
			{
				if ((position == 0)) {				
					//Fragment1 frag1 = (Fragment1)fn.findFragmentById(R.id.fragment1);
					//frag1.alterarTextView("Alterando o texto");
					
					Fragment1 frag1 = new Fragment1();
					FragmentTransaction ft = fn.beginTransaction();
					//repare o replace, pois o add foi lá no onCreate
					//parametro 3, tag, pode ser adicionado via XML, usando o findFragmentByTag,
					//mas como estamos trabalho dinamicamente, precisamos definir a tag agora!
					ft.replace(R.id.layout_direito, frag1, "frag1");
					//permite que ao clicar em "Voltar", retorne ao fragment anterior
					ft.addToBackStack("pilha");
					ft.commit();
				}
				else if ((position == 1)) {				
					Fragment1 frag1 = (Fragment1)fn.findFragmentByTag("frag1");
					
					if ((frag1 != null))
						frag1.alterarTextView("Alterando o texto");
				}
				else if ((position == 2)) {				
					Fragment2 frag2 = new Fragment2();
					FragmentTransaction ft = fn.beginTransaction();					
					ft.replace(R.id.layout_direito, frag2, "frag2");
					ft.addToBackStack("pilha");
					ft.commit();
				}
				else if ((position == 3)) {				
					Fragment3 frag3 = new Fragment3();
					FragmentTransaction ft = fn.beginTransaction();					
					ft.replace(R.id.layout_direito, frag3, "frag3");
					ft.addToBackStack("pilha");
					ft.commit();
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
