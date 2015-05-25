package com.example.exand_sensores_proximidade_galeria_01;



import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity implements SensorEventListener {

	private SensorManager sensorManager;
	private Sensor sensorDeOrientacao;
	EditText edit;
	float x = 0.0f, y = 0.0f; 
	
	Integer[] imageIDs = {
            R.drawable.img_01,
            R.drawable.img_02,
            R.drawable.img_03,
            R.drawable.img_04,
            R.drawable.img_05,
            R.drawable.img_06
    };
		
	int posAtual = -1;
	ImageView imageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		sensorManager =(SensorManager)getSystemService(Context.SENSOR_SERVICE);
		sensorDeOrientacao = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

		edit = (EditText)findViewById(R.id.editText1);
		imageView = (ImageView) findViewById(R.id.imageView1);
		
		
		
		
        CarregarImagem();
	}
	
	@Override
	protected void onResume()
	{
		super.onResume();
		
		//Ao iniciar a aplicação, registra o listener do sensor de luz, com valores defaults
		sensorManager.registerListener(this, sensorDeOrientacao, SensorManager.SENSOR_PROXIMITY);
	}
	
	protected void onPause()
	{
		super.onPause();
		
		//Desregistrar
		sensorManager.unregisterListener(this);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		float posicaoX = event.getX();
	    float posicaoY = event.getY();
		
	    // get pointer index from the event object
	    int pointerIndex = event.getActionIndex();

	    // get pointer ID
	    int pointerId = event.getPointerId(pointerIndex);

	    // get masked (not specific to a pointer) action
	    int maskedAction = event.getActionMasked();

	    switch (maskedAction) {

		    case MotionEvent.ACTION_DOWN:
		    {
		      // TODO use data
		    	//Toast.makeText(getApplicationContext(), "ACTION_DOWN", Toast.LENGTH_SHORT).show();
		      break;		    	
		    }
		    case MotionEvent.ACTION_POINTER_DOWN:
		    {
		      // TODO use data
		    	//Toast.makeText(getApplicationContext(), "ACTION_POINTER_DOWN", Toast.LENGTH_SHORT).show();
		      break;
		    }
		    case MotionEvent.ACTION_MOVE:
		    { // a pointer was moved
		      // TODO use data
		    	//Toast.makeText(getApplicationContext(), "ACTION_MOVE", Toast.LENGTH_SHORT).show();
		    	
		    	if ((posicaoX - x) > 10)
		    	{
		    		Toast.makeText(getApplicationContext(), "Anterior: " + x + " - Atual: " + posicaoX, Toast.LENGTH_SHORT).show();
		    		
		    		x = posicaoX;
		    		CarregarImagem();
		    	}
		    	
		      break;
		    }
		    case MotionEvent.ACTION_UP:
		    { // a pointer was moved
			      // TODO use data
		    	//Toast.makeText(getApplicationContext(), "ACTION_UP", Toast.LENGTH_SHORT).show();
			      break;
			}
		    case MotionEvent.ACTION_POINTER_UP:
		    { // a pointer was moved
			      // TODO use data
		    	//Toast.makeText(getApplicationContext(), "ACTION_POINTER_UP", Toast.LENGTH_SHORT).show();
			      break;
			}
		    case MotionEvent.ACTION_CANCEL:
		    {
		      // TODO use data
		    	//Toast.makeText(getApplicationContext(), "ACTION_CANCEL", Toast.LENGTH_SHORT).show();
		      break;
		    }
	    }

	    return true;
	} 
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
	    // TODO Auto-generated method stub
	    super.dispatchTouchEvent(ev);
	    
	    if(imageView.onTouchEvent(ev))
	    {
	        return imageView.onTouchEvent(ev);
	    }
	    else
	    {
	        return false;
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

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		
		if ((event.sensor.getType() == Sensor.TYPE_PROXIMITY))
	    {
			float proxAtual = event.values[0];
			
			if ((proxAtual <= 3.0))			
				CarregarImagem();
			
			edit.setText(String.valueOf(proxAtual));
	    }
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
		
		
	}

	private void CarregarImagem()
	{
		Log.v("IMG", "ENTROU 1");
		
		Toast tMsg = Toast.makeText(getApplicationContext(), "Próxima imagem", Toast.LENGTH_SHORT);
		
		try {
		
			tMsg.show();
			
		if ((posAtual + 1 > imageIDs.length - 1))
			posAtual = 0;
		else
			posAtual = posAtual + 1;
			
		
		imageView.setImageResource(imageIDs[posAtual]);
		
		}
		finally {
			//tMsg.cancel();
		}
	}

}
