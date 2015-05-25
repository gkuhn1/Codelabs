package com.example.exand_webservice_01;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity implements Runnable {

	//Projeto exemplo de como funciona, fazer turma replicar o código 
	//VER PERMISSÕES
	//VER IMPORTS
	//Implements Runnable
	//Não usar TOASTs
	
	 private static final String SOAP_ACTION = "http://tempuri.org/HelloWorld";  
	 private static final String SOAP_METHOD = "HelloWorld";  
	 private static final String NAMESPACE = "http://tempuri.org/";  
	 private static final String URL = "http://www.hypersonictechnologies.com/Example.asmx";
	
	ProgressDialog dialogDeProgresso;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
		//Toast.makeText(getApplicationContext(), "PASSOU 1", Toast.LENGTH_SHORT);
		dialogDeProgresso = ProgressDialog.show(MainActivity.this, "", "Consultando...", true);

		//Toast.makeText(getApplicationContext(), "PASSOU 2", Toast.LENGTH_SHORT);
		
		//Conhecem o significado disso?
		new Thread(this).start();	
	}
	
	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		
		consultar();
	}
	
	public void consultar()
	{
		try
		{
			SoapObject request = new SoapObject(NAMESPACE, SOAP_METHOD);  
	           SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(  
	                     SoapEnvelope.VER11);  
	           envelope.dotNet = true;  
	           envelope.setOutputSoapObject(request);  
	           AndroidHttpTransport aht = new AndroidHttpTransport(URL);  
	           @SuppressWarnings("unused")  
	           Object result;  
	           try {  
	                aht.call(SOAP_ACTION, envelope);  
	                result = (Object) envelope.getResponse();
	   
	        } catch (Exception e) {
	        	dialogDeProgresso.dismiss();
	        	Log.v("ws", "ERRO :"+e.getMessage());
	            e.printStackTrace();
	        }
		}
		finally
		{
			dialogDeProgresso.dismiss();
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

	
}
