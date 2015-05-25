package com.exemplos.aula09_exemplo02_broadcastreceiver;
import android.os.Bundle;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

public class FoneReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle extras = intent.getExtras();
		if (extras != null) {
			String state = extras.getString(TelephonyManager.EXTRA_STATE);
			
/* EXTRA_STATE: chave usada com ACTION_PHONE_STATE_CHANGED broadcast contendo o 
 * novo estado da chamada
 * EXTRA_STATE_IDLE
 * EXTRA_STATE_RINGING
 * 
 * EXTRA_INCOMING_NUMBER
 */
			Log.w("Fone", state);
			if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
				String phoneNumber = extras.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
				Log.w("Fone", "Chamada Telefonica de " + phoneNumber);
			}
		}
	}
}

