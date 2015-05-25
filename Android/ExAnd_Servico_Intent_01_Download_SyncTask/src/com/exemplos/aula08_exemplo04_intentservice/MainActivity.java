package com.exemplos.aula08_exemplo04_intentservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {
    Button btnStart;
    Button btnStop;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = (Button) findViewById(R.id.btnStartService);
        btnStop = (Button) findViewById(R.id.btnStopService);
        
        btnStart.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) {
    			startService(new Intent(getBaseContext(),MeuIntentService.class));
    			//startService executa onStartCommand do Serviço
    		}
    	});
        
        btnStop.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) {
    			stopService(new Intent(getBaseContext(),MeuIntentService.class));
    			//stopService executa onStopService do Servico
    		}
    	});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
