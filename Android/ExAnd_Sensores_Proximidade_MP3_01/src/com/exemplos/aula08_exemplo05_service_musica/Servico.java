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
	private static final String MEDIA_PATH = new String("/sdcard/");
	private List<String> musicas = new ArrayList<String>();
	private MediaPlayer mp = new MediaPlayer();
	private int posAtual = 0;
	 
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
        posAtual = 0;
        atualizaListaMusicas();
        tocaMusica(MEDIA_PATH + musicas.get(posAtual));
        return START_STICKY;
    }
    public void atualizaListaMusicas() 
    {
    	File home = new File(MEDIA_PATH);
    	
    	if (home.listFiles( new Mp3Filter()).length > 0)
    	{
    		for (File file : home.listFiles( new Mp3Filter()))
    		{
    			musicas.add(file.getName());
    		}		
		}
    }

	
	@Override
	public void onDestroy() 
	{
		super.onDestroy();
		Toast.makeText(this, "Servi�o Encerrado", Toast.LENGTH_LONG).show();
    	mp.reset();
    	mp.stop();
    	posAtual = musicas.size();
	}
	
	private void tocaMusica(String caminho) {
		try {
			mp.reset();
			mp.setDataSource(caminho);
			mp.prepare();
			mp.start();
			
		// O listener abaixo "detecta" quando uma m�sica acaba e inicia a pr�xima da lista
			mp.setOnCompletionListener(new OnCompletionListener() {
				public void onCompletion(MediaPlayer arg0) {
					proxMusica(++posAtual);
				}
			});
		} catch (IOException e) {
			Log.v(getString(R.string.app_name), e.getMessage());
		}
	}
	private void proxMusica(int posicao) {
		if (posicao < musicas.size()) {
			tocaMusica(MEDIA_PATH + musicas.get(posAtual));
		}
	}
}
class Mp3Filter implements FilenameFilter {
    public boolean accept(File dir, String name) {
        return (name.endsWith(".mp3"));
    }
}
