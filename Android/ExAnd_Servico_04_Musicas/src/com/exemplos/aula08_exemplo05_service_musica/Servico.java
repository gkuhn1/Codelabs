package com.exemplos.aula08_exemplo05_service_musica;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class Servico extends Service{
	private static final String MEDIA_PATH = new String("/SDCARD/");
	private List<String> musicas = new ArrayList<String>();
	private MediaPlayer mp = new MediaPlayer();
	private int posAtual = 0;
	 
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	
	@Override
    public int onStartCommand(Intent intent, int flags, int startId) {
		try {
        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.
        Toast.makeText(this, "Serviço Iniciado", Toast.LENGTH_LONG).show();
        /* Serviços startados pelo usuário possuem dois modos de operação, retornados pelo
         * método onStartCommand: START_STICKY e START_NOT_STICKY
         * START_STICKY: usado para serviços que são explicitamente iniciados e parados
         * quando necessário.
         */
        posAtual = 0;
        atualizaListaMusicas();
        if ((musicas != null) && (musicas.size() > 0))
        	tocaMusica(MEDIA_PATH + musicas.get(posAtual));
        
		}
		catch (Exception e) {
			Log.v("MP3", "1-" + e.getMessage());
		}
		
		return START_STICKY;
    }
    public void atualizaListaMusicas() 
    {
    	try
    	{
	    	File home = new File(MEDIA_PATH);
	    	
	    	if ((home != null))
	    	{
		    	if (home.listFiles( new Mp3Filter()).length > 0)
		    	{
		    		for (File file : home.listFiles( new Mp3Filter()))
		    		{
		    			musicas.add(file.getName());
		    		}		
				}
	    	}
    	}
	     catch (Exception e) {
			Log.v("MP3", "2-" + e.getMessage());
		}
    	
    }

	
	@Override
	public void onDestroy() 
	{
		try {
		super.onDestroy();
		Toast.makeText(this, "Serviço Encerrado", Toast.LENGTH_LONG).show();
    	mp.reset();
    	mp.stop();
    	posAtual = musicas.size();
		}
		catch (Exception e) {
			Log.v("MP3", "3-" + e.getMessage());
		}
	}
	
	private void tocaMusica(String caminho) {
		try {
			mp.reset();
			mp.setDataSource(caminho);
			mp.prepare();
			mp.start();
			
		// O listener abaixo "detecta" quando uma música acaba e inicia a próxima da lista
			mp.setOnCompletionListener(new OnCompletionListener() {
				public void onCompletion(MediaPlayer arg0) {
					proxMusica(++posAtual);
				}
			});
		} catch (IOException e) {
			Log.v("MP3", "4-" + e.getMessage());
		}
	}
	private void proxMusica(int posicao) {
		try {
			if ((musicas != null) && (musicas.size() > 0)) {
				if (posicao < musicas.size()) {
						tocaMusica(MEDIA_PATH + musicas.get(posAtual));
				}
		}
		}
		catch (Exception e) {
			Log.v("MP3", "5-" + e.getMessage());
		}
	}
}
class Mp3Filter implements FilenameFilter {

    public boolean accept(File dir, String name) {
        return (name.endsWith(".mp3"));
    }
}
