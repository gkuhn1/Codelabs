package com.exemplos.aula08_exemplo03_service;

import java.net.MalformedURLException;
import java.net.URL;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class Servico extends Service{

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	@Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this, "Servi�o Iniciado", Toast.LENGTH_LONG).show();
        /* Servi�os startados pelo usu�rio possuem dois modos de opera��o, retornados pelo
         * m�todo onStartCommand: START_STICKY e START_NOT_STICKY
         * START_STICKY: usado para servi�os que s�o explicitamente iniciados e parados
         * quando necess�rio.
         */
        try {
            new BaixaArquivos().execute(
                    new URL("http://www.amazon.com/somefiles.pdf"),
                    new URL("http://www.wrox.com/somefiles.pdf"),
                    new URL("http://www.google.com/somefiles.pdf"),
                    new URL("http://www.learn2develop.net/somefiles.pdf"));

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
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

	class BaixaArquivos extends AsyncTask<URL, Integer, Long>
	{
	    protected Long doInBackground(URL... urls)
	    {
	        int count = urls.length; //4
	        long totalBytesDownloaded = 0;
	        for (int i = 0; i < count; i++) {
	            totalBytesDownloaded += downloadFile(urls[i]);//100
	            
	            //Calcula a % baixada e retorna o progresso
	            publishProgress((int) (((i+1) / (float) count) * 100)); //invoca o m�todo onProgressUpdate
	        }
	        return totalBytesDownloaded;
	    }
	
	    protected void onProgressUpdate(Integer... progress) {
	        Log.d("Baixando Arquivos: ",String.valueOf(progress[0]) + "% baixado");
	        Toast.makeText(getBaseContext(),String.valueOf(progress[0]) + "% baixado",Toast.LENGTH_LONG).show();
	    }
	
	    protected void onPostExecute(Long result) {
	        Toast.makeText(getBaseContext(),"Baixados " + result + " bytes",Toast.LENGTH_LONG).show();
	        stopSelf();
	    }
	}
}