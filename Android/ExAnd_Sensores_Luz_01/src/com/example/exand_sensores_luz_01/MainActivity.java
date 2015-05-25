package com.example.exand_sensores_luz_01;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.os.Build;

//Atenção para o implements
public class MainActivity extends ActionBarActivity implements SensorEventListener {

	private SensorManager sensorManager;
	private Sensor sensorDeLuz;
	EditText edtValor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		sensorManager =(SensorManager)getSystemService(Context.SENSOR_SERVICE);
	    sensorDeLuz = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
	    edtValor = (EditText)findViewById(R.id.editText1);

	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		//Executar ação determinada quando algo ocorrer
	}
	
	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		
		//O sensor de lux retorna 1 valor único.
		//Alguns sensores retornarão 3 valores, um para cada vetor.
		float lux = event.values[0];
		edtValor.setText(String.valueOf(lux));
	}
	
	@Override
	protected void onResume()
	{
		super.onResume();
		
		//Ao iniciar a aplicação, registra o listener do sensor de luz, com valores defaults
		sensorManager.registerListener(this, sensorDeLuz, SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	protected void onPause()
	{
		super.onPause();
		
		//Desregistrar
		sensorManager.unregisterListener(this);
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
