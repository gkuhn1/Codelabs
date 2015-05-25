package com.exemplos.aula09_exemplo01_broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootBR extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent)
	{
		//VER PERMISS�ES DO MANIFESTO
		//Ver declara��es de BroadCast Receiver
		
		//Execu��o da Activitiy principal
		Intent i = new Intent(context, MainActivity.class);
		
		i.addCategory(Intent.CATEGORY_DEFAULT);
		
		context.startActivity(i);
		
		Log.d("BroadcastReceiver","Estou aqui!");
	}
	

}
