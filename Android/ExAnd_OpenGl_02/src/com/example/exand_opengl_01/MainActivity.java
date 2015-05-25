package com.example.exand_opengl_01;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.os.Build;

public class MainActivity extends ActionBarActivity
{
	//Criar antes o GLRenderer
	//Atenção aos imports
	//Atenção ao setContentView
	//Atenção ao onResume e ao onPause
	
	/* Entendendo:
	 * O glSurfaceView é a instância responsável por exibir o contexto OpenGL
	 * Quando instânciado, fará com que a visão normal da activitie seja substituída
	 * pela renderização em branco.
	 * Quando executado, o onResume e onPause, fazem com que consigamos interromper o
	 * processo de renderização de acordo com o ciclo de vida da activity
	 */
	
	/* glSurfaceView => View no formato OpenGL */
	private GLSurfaceView glSurfaceView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);

		// Define uma janela sem título
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		// Define como tela cheia.
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
							 WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//
		/* Inicia a visão OpenGL e cria
		 * uma instância nesta activitie
		 */		
		glSurfaceView = new GLSurfaceView(this);
			         
		/* Define o objeto de renderização criado
		 * como o objeto de renderização padrão desta activities */
		glSurfaceView.setRenderer(new GLRenderer());
		setContentView(glSurfaceView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onResume()
	{
		super.onResume();
		glSurfaceView.onResume();
	}
		 	
	@Override
	protected void onPause()
	{
		super.onPause();
		glSurfaceView.onPause();
	}

}
