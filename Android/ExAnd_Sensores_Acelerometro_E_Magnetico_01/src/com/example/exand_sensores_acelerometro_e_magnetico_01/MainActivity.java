package com.example.exand_sensores_acelerometro_e_magnetico_01;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
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
import android.os.Build;

public class MainActivity extends ActionBarActivity implements SensorEventListener {
	
	//Começar o exemplo copiando todos os imports
	
	Float fViewBussola; //Controle de exibição da view!
	float[] vetorPosGravidade;
	float[] vetorPosMagnetico;
	
	public class ViewDaBussola extends View
	{
	    Paint pntCorEEstilo = new Paint();
	    
	    public ViewDaBussola(Context context)
	    {
	      super(context);
	      
	      pntCorEEstilo.setColor(0xff00ff00); //Verde
	      
	      pntCorEEstilo.setStyle(Style.STROKE); //Geometria e texto irão ser mantidos no mesmo plano	      
	      pntCorEEstilo.setStrokeWidth(2);
	      
	      pntCorEEstilo.setAntiAlias(true); //Filtro anti-alisante
	    };
	 
	    protected void onDraw(Canvas pCanvas)
	    {
	      //canvas => quadro, local de desenho
	    	
	      int width = getWidth();
	      int height = getHeight();
	      int centroDoEixoX = width/2;
	      int centroDoEixoY = height/2;
	      
	      //Desenha linha do centro ao inicio e do centro ao fim verticalmente
	      pCanvas.drawLine(centroDoEixoX, 0, centroDoEixoX, height, pntCorEEstilo);	      
	    //Desenha linha do centro ao inicio e do centro ao fim horizontalmente
	      pCanvas.drawLine(0, centroDoEixoY, width, centroDoEixoY, pntCorEEstilo);
	      
	      //Gira o canvas através do controle da variável fViewBussola
	      //-Converte as cordenadas cartesianas em cordenada polar, mais informações em google.com   
	      if ((fViewBussola != null))
	    	  pCanvas.rotate(-fViewBussola*360/(2*3.14159f), centroDoEixoX, centroDoEixoY);
	      
	      
	      //Desenha novas linhas indicando as posições N e S par alterar de acordo com o posiconamento encontrado.
	      pntCorEEstilo.setColor(0xff0000ff); //Vermelhor
	      
	      pCanvas.drawLine(centroDoEixoX, -1000, centroDoEixoX, +1000, pntCorEEstilo);
	      pCanvas.drawLine(-1000, centroDoEixoY, 1000, centroDoEixoY, pntCorEEstilo);
	      pCanvas.drawText("N", centroDoEixoX+5, centroDoEixoY-10, pntCorEEstilo);
	      pCanvas.drawText("S", centroDoEixoX-10, centroDoEixoY+15, pntCorEEstilo);
	      
	      pntCorEEstilo.setColor(0xff00ff00); ////Verde
	    }
	  }

	
	SensorManager sensorManager;
	Sensor sensorAcelerometro, sensorMagnetico;
	ViewDaBussola viewDaBussola;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		//setContentView(R.layout.activity_main);
		
		
		sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
		sensorAcelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sensorMagnetico = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
	    
		///////
	    viewDaBussola = new ViewDaBussola(this);
	    setContentView(viewDaBussola);
	    ///////
	}
	
	@Override
	protected void onResume()
	{
		
	    super.onResume();
	    
	    sensorManager.registerListener(this, sensorAcelerometro, SensorManager.SENSOR_DELAY_UI);
	    sensorManager.registerListener(this, sensorMagnetico, SensorManager.SENSOR_DELAY_UI);
	  }
	@Override
	protected void onPause()
	{
	    super.onPause();
	    
	    sensorManager.unregisterListener(this);
	}
	@Override
	public void onSensorChanged(SensorEvent event)
	{
		// TODO Auto-generated method stub	 
		//O método é chamado pelos listeners dos 2 vetores, por isso a necessidade do teste!		
	    if ((event.sensor.getType() == Sensor.TYPE_ACCELEROMETER))
	    	vetorPosGravidade = event.values;
	    	    
	    if ((event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD))
	    	vetorPosMagnetico = event.values;
	      
	    if ((vetorPosGravidade) != null && (vetorPosMagnetico != null))
	    {
	    	
	      float valoresDeRotacao[] = new float[9];
	      float valoresDeInclinacao[] = new float[9];
	      
	      //Através do SensorManager, obtem a orientação com os valores de posicionamento
	      //gravitacional e magnetico
	      boolean bExecutou = SensorManager.getRotationMatrix(valoresDeRotacao, valoresDeInclinacao, vetorPosGravidade, vetorPosMagnetico);
	      
	      if ((bExecutou))
	      {
	    	  
	        float valoresDeOrientacao[] = new float[3];
	        
	        //Através do SensorManager, obtem os valores de Rotação ao redor do eixo Y
	        SensorManager.getOrientation(valoresDeRotacao, valoresDeOrientacao);
	        
	        fViewBussola = valoresDeOrientacao[0]; // orientation contains: azimut, pitch and roll
	        
	      }
	    }
	    
	    viewDaBussola.invalidate();
	  }
	  
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy)
	{  
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
