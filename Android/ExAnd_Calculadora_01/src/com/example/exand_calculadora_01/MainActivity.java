package com.example.exand_calculadora_01;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity {
	
	EditText edtvalor1, edtvalor2, edtvalor3;
	Button btnCalcular;
	Spinner opcoes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		edtvalor1 = (EditText) findViewById(R.id.editText1);
		edtvalor2 = (EditText) findViewById(R.id.editText2);
		edtvalor3 = (EditText) findViewById(R.id.editText3);
		btnCalcular = (Button) findViewById(R.id.button1);
		opcoes = (Spinner) findViewById(R.id.spinner1);
				
		btnCalcular.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				calcular();
			}
		});
		
		ArrayAdapter<CharSequence> lista = ArrayAdapter.createFromResource(this,
				R.array.opcoes, android.R.layout.simple_spinner_item);
		
		// Determinando o formado para usar quando a lista é exibida
		lista.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);		
		// Aplicando o adapter ao spinner
		opcoes.setAdapter(lista);
	}
	
	private void calcular()
	{
		float valor1 = 0;
		float valor2 = 0;
		float valor3 = 0;
		
		valor1 = Float.parseFloat(edtvalor1.getText().toString());
		valor2 = Float.parseFloat(edtvalor2.getText().toString());
		
		if ((opcoes.getSelectedItem().toString().equals(enumOperacoes.Somar.toString())))
			valor3 = valor1 + valor2;
		
		edtvalor3.setText(String.valueOf(valor3));
		
		
		Intent itResultado = new Intent(getApplicationContext(), Resultado.class);
		Bundle extras = new Bundle();
		extras.putString("resultado", String.valueOf(valor3));
		itResultado.putExtras(extras);
		
		//itResultado.putExtra("resultado", String.valueOf(valor3));
		startActivity(itResultado);
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
