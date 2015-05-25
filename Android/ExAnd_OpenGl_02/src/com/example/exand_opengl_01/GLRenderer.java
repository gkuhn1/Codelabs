package com.example.exand_opengl_01;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

public class GLRenderer implements Renderer
{
	//Antes, fazer classe do tri�ngulo	
	private Triangulo triangulo;
	
	//Construtor
	public GLRenderer()
	{
		this.triangulo = new Triangulo();
	}
	
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
		
		//Garantir que n�o haver� divis�es por zero
		if ((height == 0))
			height = 1;

		//Reseta o Viewport atual
		gl.glViewport(0, 0, width, height);
		
		//Seleciona o tipo de proje��o do "Projection matrix"
		gl.glMatrixMode(GL10.GL_PROJECTION);
		//Reseta o proj. matrix
		gl.glLoadIdentity();

		//Calculate The Aspect Ratio Of The Window
		//Calculo o "Aspect Ration", ou seja, o rendimensionamento da perspectiva de acordo com a imagem exibida.
		GLU.gluPerspective(gl, 45.0f, (float)width / (float)height, 0.1f, 100.0f);

		//Seleciona o tipo de proje��o do "Projection matrix"
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		//Reseta o proj. matrix
		gl.glLoadIdentity(); 				
	}

	//Chamado para desenhar o frame atual
	//� executado por uma thread que desenha cada frame,
	//n�o � necess�rio for�ar sua execu��o
	@Override 
	public void onDrawFrame(GL10 gl)
	{
		// TODO Auto-generated method stub
		
		//Limpa a tela e o Depth  Buffer
		//Depth Buffer s�o os dados correspondentes aos valores Z dos pixels renderizados
		//que cont�m as informa��es para renderiza��es mais complexas, como shaders ou sombras
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		//Reseta o "Modelview Matrix", ou seja, os dados de posicionamento, angula��o e etc da c�mera
		gl.glLoadIdentity();

		//Finalimente o desenho:
		
		//movimenta 5 unidades na tela, deixando a c�mera mais afastada
		gl.glTranslatef(0.0f, 0.0f, -5.0f);	

		triangulo.desenhar(gl);		
	}

}
