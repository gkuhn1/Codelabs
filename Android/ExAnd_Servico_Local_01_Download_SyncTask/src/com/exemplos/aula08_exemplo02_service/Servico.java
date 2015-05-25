package com.exemplos.aula08_exemplo02_service;

import java.net.MalformedURLException;
import java.net.URL;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class Servico extends Service{

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	@Override
    public int onStartCommand(Intent intent, int flags, int startId) {
		//Este servi�o continua executanto at� que seja parado de forma expl�cita
        Toast.makeText(this, "Servi�o Iniciado", Toast.LENGTH_LONG).show();
        /* Servi�os startados pelo usu�rio possuem dois modos de opera��o, retornados pelo
         * m�todo onStartCommand: START_STICKY e START_NOT_STICKY
         * START_STICKY: usado para servi�os que s�o explicitamente iniciados e parados
         * quando necess�rio.
         */
        
         try{
        	 int resultado = downloadFile(new URL("http://www.amazon.com.arquivo.pdf"));
        	 //O toast somente � exibido ap�s o download terminar
        	 Toast.makeText(getBaseContext(), "Download " + resultado +" bytes", Toast.LENGTH_LONG).show();
         }
         catch(MalformedURLException e){
        	 e.printStackTrace();
         }
        
        return START_STICKY;
    }
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Toast.makeText(this, "Servi�o Encerrado", Toast.LENGTH_LONG).show();
	}
	
	private int downloadFile(URL url){
		try{
			Thread.sleep(5000);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		return 100;
	}
}
