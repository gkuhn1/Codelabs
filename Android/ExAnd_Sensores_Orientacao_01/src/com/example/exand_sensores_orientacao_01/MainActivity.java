package com.example.exand_sensores_orientacao_01;

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
import android.widget.Button;
import android.widget.EditText;
import android.os.Build;

public class MainActivity extends ActionBarActivity implements SensorEventListener {

	EditText EditX, EditY, EditZ;
	Button btnTeste;
	
	private SensorManager sensorManager;
	private Sensor sensorDeOrientacao;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		sensorManager =(SensorManager)getSystemService(Context.SENSOR_SERVICE);
		sensorDeOrientacao = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

		EditX = (EditText)findViewById(R.id.editTextX);
		EditY = (EditText)findViewById(R.id.editTextY);
		EditZ = (EditText)findViewById(R.id.editTextZ);
		btnTeste = (Button)findViewById(R.id.button1);
		 
	}
	
	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
	
		float x = event.values[0];
		EditX.setText(String.valueOf(x));
		
		float y = event.values[1];
		EditY.setText(String.valueOf(y));
		
		float z = event.values[2];
		EditZ.setText(String.valueOf(z));
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		
		//Ao iniciar a aplicação, registra o listener do sensor de luz, com valores defaults
		sensorManager.registerListener(this, sensorDeOrientacao, SensorManager.SENSOR_ORIENTATION);
	}
	
	protected void onPause()
	{
		super.onPause();
		
		//Desregistrar
		sensorManager.unregisterListener(this);
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
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
