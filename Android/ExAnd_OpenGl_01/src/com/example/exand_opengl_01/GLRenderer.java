package com.example.exand_opengl_01;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;

public class GLRenderer implements Renderer {
	
	//Chamado quando o frame inicia ou reinicia.
	//� executado de acordo com o ciclo de vida das activities
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub
		
	}

	//Chamado quando o frame sofre altera��es nas dimens�es
	//Ou seja, altera��es na viewport, ou ret�ngulo de exibi��o
	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// TODO Auto-generated method stub
		
	}

	//Chamado para desenhar o frame atual
	//� executado por uma thread que desenha cada frame,
	//n�o � necess�rio for�ar sua execu��o
	@Override 
	public void onDrawFrame(GL10 gl) {
		// TODO Auto-generated method stub
		
	}

}
