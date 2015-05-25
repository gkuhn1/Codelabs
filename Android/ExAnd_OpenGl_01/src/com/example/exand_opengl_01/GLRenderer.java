package com.example.exand_opengl_01;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;

public class GLRenderer implements Renderer {
	
	//Chamado quando o frame inicia ou reinicia.
	//É executado de acordo com o ciclo de vida das activities
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub
		
	}

	//Chamado quando o frame sofre alterações nas dimensões
	//Ou seja, alterações na viewport, ou retângulo de exibição
	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// TODO Auto-generated method stub
		
	}

	//Chamado para desenhar o frame atual
	//É executado por uma thread que desenha cada frame,
	//não é necessário forçar sua execução
	@Override 
	public void onDrawFrame(GL10 gl) {
		// TODO Auto-generated method stub
		
	}

}
