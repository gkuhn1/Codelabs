package com.exemplos.aula08_exemplo06_service_numeros;

import java.util.Random;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class Servico extends Service{
	private final IBinder meuBinder = new LocalBinder();
	    private final Random gerador = new Random();

	    public class LocalBinder extends Binder {
	        Servico getService() {
	            return Servico.this;
	        }
	    }
	    
	    @Override
	    public IBinder onBind(Intent intent) {
	        return meuBinder;
	    }

	    public int getRandomNumber() {
	      return gerador.nextInt(100);
	    }
}
