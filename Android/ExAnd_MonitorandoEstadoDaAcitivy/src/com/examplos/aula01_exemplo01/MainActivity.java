package com.examplos.aula01_exemplo01;

import android.os.Bundle;
import android.app.Activity;
//import android.view.Menu;
import android.util.Log;


public class MainActivity extends Activity {
	String tag= "Eventos";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(tag,"Estou no evento onCreate()");
    }
    
    public void onStart(){
        super.onStart();
        Log.d(tag, "Estou no evento onStart()");
    }    
    public void onRestart(){
        super.onRestart();
        Log.d(tag, "Estou no evento onRestart()");
    }    
    public void onResume(){
        super.onResume();
        Log.d(tag, "Estou no evento onResume()");
    }
    public void onPause(){
        super.onPause();
        Log.d(tag, "Estou no evento onPause()");
    }    
    public void onStop(){
        super.onStop();
        Log.d(tag, "Estou no evento onStop()");
    }    
    public void onDestroy(){
        super.onDestroy();
        Log.d(tag, "Estou no evento onDestroy()");
    }   

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
*/
}
