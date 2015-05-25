package com.exemplos.aula09_exemplo03_broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.widget.Toast;

public class MeuBroadcastReceiver extends BroadcastReceiver {
  @Override
  public void onReceive(Context context, Intent intent) {
      Toast.makeText(context, "Chegou a hora!.",Toast.LENGTH_LONG).show();
      // Vibrate the mobile phone
      Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
      vibrator.vibrate(2000);
  }

} 
