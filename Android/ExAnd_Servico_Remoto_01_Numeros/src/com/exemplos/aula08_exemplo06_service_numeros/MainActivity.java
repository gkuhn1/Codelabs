package com.exemplos.aula08_exemplo06_service_numeros;


import com.exemplos.aula08_exemplo06_service_numeros.Servico.LocalBinder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {
	Servico servico;
	boolean conectado = false;
	
    Button btnStart;
    Button btnStatus;
    Button btnStop;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = (Button) findViewById(R.id.btnStartService);
        btnStatus = (Button) findViewById(R.id.btnStatusService);
        btnStop = (Button) findViewById(R.id.btnStopService);
        
        btnStart.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) {
    			
    			bindService(new Intent(getBaseContext(),Servico.class),
    					conexao, Context.BIND_AUTO_CREATE);
    			
    		}
    	});
        
        btnStatus.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) {
    			if (conectado) {
    		        // Call a method from the LocalService.
    		        // However, if this call were something that might hang, then this request should
    		        // occur in a separate thread to avoid slowing down the activity performance.
    		        int num = servico.getRandomNumber();
    		        Toast.makeText(MainActivity.this, "Número: " + num, Toast.LENGTH_SHORT).show();
    		    }
    		}
    	});
 
        
        btnStop.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) {
    			unbindService(conexao);
    	        conectado = false;
    		}
    	});
        
       	
   }//fim do onCreate
    
	@Override
	protected void onStart() {
	    super.onStart();
	    // Bind to LocalService
	    Intent intent = new Intent(this, Servico.class);
	    bindService(intent, conexao, Context.BIND_AUTO_CREATE);
	} 

	@Override
	protected void onStop() {
	    super.onStop();
	    // Desconecta do serviço
	    if (conectado) {
	        unbindService(conexao);
	        conectado = false;
	    }
	}
    
    ServiceConnection conexao = new ServiceConnection() {
		public void onServiceConnected(ComponentName name, IBinder srv) {
			LocalBinder binder = (LocalBinder) srv;
	        servico = binder.getService();
	        conectado = true;
	    }

		public void onServiceDisconnected(ComponentName name) {
			conectado = false;
		}
	};

}   
    
