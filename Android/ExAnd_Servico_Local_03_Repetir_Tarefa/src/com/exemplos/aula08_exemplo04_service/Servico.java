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
        Toast.makeText(this, "Serviço Iniciado", Toast.LENGTH_LONG).show();
        /* Serviços startados pelo usuário possuem dois modos de operação, retornados pelo
         * método onStartCommand: START_STICKY e START_NOT_STICKY
         * START_STICKY: usado para serviços que são explicitamente iniciados e parados
         * quando necessário.
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
		Toast.makeText(this, "Serviço Encerrado", Toast.LENGTH_LONG).show();
	}
	
    private void repeteTarefa() {
        timer.scheduleAtFixedRate( new TimerTask() {
            public void run() {
                Log.d("Servico", String.valueOf(++contador));
            }
        }, 0, INTERVALO_ATUALIZACAO);
    }
}
