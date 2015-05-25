package com.example.exand_opengl_01;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Triangulo
{
	
	/*Verificar:
	 * - Imports
	 * */
	
	//Um buffer para armazenar os v�rtices do objeto 3D
	private FloatBuffer bufferDasCordenadas;
	
	//Tri�ngulo � composto por tr�s lados, portanto suas cordenadas s�o:
	//(Imporante, como precisaremos dos valores de x a y, z ficar� sempre com zero)
	private float verticesDoTriangulo[] = {
			-0.5f, -0.5f,  0.0f,		// primeiro lado (x,y,z)
			 0.5f, -0.5f,  0.0f,		// segundo lado
			 0.0f,  0.5f,  0.0f			// terceiro lado
	};
	
	//Construtor
	public Triangulo()
	{
		// a float has 4 bytes so we allocate for each coordinate 4 bytes
		//Um float tem 3 bytes, desa forma n�s precisamos alocar para cada cordenada do tri�ngulo 4 bytes
		ByteBuffer cordenadaBB = ByteBuffer.allocateDirect(verticesDoTriangulo.length * 4);
		cordenadaBB.order(ByteOrder.nativeOrder());
		
		//Aloca��o de mem�ria necess�ria
		bufferDasCordenadas = cordenadaBB.asFloatBuffer();
		
		//Preenche o bufferDasCordenadas com o os v�rtices do tri�ngulo
		bufferDasCordenadas.put(verticesDoTriangulo);
		
		//Define o in�cio do cursos no in�cio do buffer
		bufferDasCordenadas.position(0);
	}
	
	public void desenhar(GL10 gl)
	{
		//Define como array de v�rtices
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		
		//Define a cor
		gl.glColor4f(0.0f, 1.0f, 0.0f, 0.5f);
		
		//Atribu� o buffer das cordenadas
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, bufferDasCordenadas);

		//Desenha os v�rtices como "triangle strip"
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, verticesDoTriangulo.length / 3);
		
		//Desabilita o "client state" antes de sair, ou seja, elimina o buffer para desenho
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}

}
