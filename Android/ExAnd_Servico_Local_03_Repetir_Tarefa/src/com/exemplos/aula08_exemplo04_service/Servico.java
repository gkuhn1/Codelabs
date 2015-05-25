package com.exemplos.aula08_exemplo04_service;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class Servico extends Service{
	int contador = 0;
    static final int INTERVALO_ATUALIZACAO = 1000;
    private Timer timer = new Timer();
    
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	@Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.
        Toast.makeText(this, "Servi�o Iniciado", Toast.LENGTH_LONG).show();
        /* Servi�os startados pelo usu�rio possuem dois modos de opera��o, retornados pelo
         * m�todo onStartCommand: START_STICKY e START_NOT_STICKY
         * START_STICKY: usado para servi�os que s�o explicitamente iniciados e parados
         * quando necess�rio.
         */
        repeteTarefa();
        
        return START_STICKY;
    }
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		 if (timer != null){
	            timer.cancel();
	     }
		Toast.makeText(this, "Servi�o Encerrado", Toast.LENGTH_LONG).show();
	}
	
    private void repeteTarefa() {
        timer.scheduleAtFixedRate( new TimerTask() {
            public void run() {
                Log.d("Servico", String.valueOf(++contador));
            }
        }, 0, INTERVALO_ATUALIZACAO);
    }
}
