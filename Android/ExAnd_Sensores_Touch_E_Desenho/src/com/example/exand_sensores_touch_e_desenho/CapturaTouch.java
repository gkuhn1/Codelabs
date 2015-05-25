package com.example.exand_sensores_touch_e_desenho;

import java.io.File;
import java.io.FileOutputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class CapturaTouch extends View  {
	
	float x= 0.0f;
	private Paint pntCorEEstilo = new Paint();
	private Path caminho = new Path(); //Contorno, dados geométricos, etc
	
	public CapturaTouch(Context context, AttributeSet attrs) 
	{
	    super(context, attrs);

	    pntCorEEstilo.setAntiAlias(true); //Anti-alising
	    pntCorEEstilo.setStrokeWidth(6f);
	    pntCorEEstilo.setColor(Color.BLACK); //Fundo
	    pntCorEEstilo.setStyle(Paint.Style.STROKE); //Plano
	    pntCorEEstilo.setStrokeJoin(Paint.Join.ROUND); //Desenho ficará paralelo ao plano em X
	}
	
	@Override
	protected void onDraw(Canvas pCanvas)
	{
		pCanvas.drawPath(caminho, pntCorEEstilo);
	}
	
	public Bitmap salvarImagem(Context pContext)
	{
		//Antes ver permissões no Manifest
		
	      Bitmap  bitmap = Bitmap.createBitmap(this.getWidth(), this.getHeight(), Bitmap.Config.ARGB_8888);
	      Canvas canvas = new Canvas(bitmap);
	      this.draw(canvas); 

	      String sCaminho = Environment.getExternalStorageDirectory() + "/desenho.png";
	      File file = new File(sCaminho);

	      try
	      {
	           bitmap.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(file));
	           
	           Toast.makeText(pContext, sCaminho, Toast.LENGTH_SHORT).show();
	      }
	      catch (Exception e)
	      {
	    	  Toast.makeText(pContext, "ERRO: " + e.getMessage(), Toast.LENGTH_SHORT).show();
	    	  
	           e.printStackTrace();
	      }

	      return bitmap;
	  }
	
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
	    float posicaoX = event.getX();
	    float posicaoY = event.getY();

	    switch (event.getAction())
	    {
	    	case MotionEvent.ACTION_DOWN:
	    	{
	    		caminho.moveTo(posicaoX, posicaoY);
	    		return true;
	    	}
	    	case MotionEvent.ACTION_MOVE:
	    	{	    	
	    		caminho.lineTo(posicaoX, posicaoY);
	    		break;
	    	}
	    	case MotionEvent.ACTION_UP:
	    	{
	    		//nothing to do
	    		break;
	    	}
	    	default:
	    	{
	    		return false;
	    	}
	    }

	    	//Schedules a repaint.
	    	invalidate();
	    	return true;
	  }

}
