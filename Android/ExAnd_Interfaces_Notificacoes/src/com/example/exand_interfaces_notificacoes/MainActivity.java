package com.example.exand_interfaces_notificacoes;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.app.Notification;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button btnNotificar = (Button)findViewById(R.id.btnNotificar);
		btnNotificar.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				notifLocal();
			}
		});
	}

	private void notifLocal()
	{
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
	    .setSmallIcon(R.drawable.ic_launcher)
	    .setContentTitle("Event tracker")
	    .setContentText("Events received");
		
	NotificationCompat.InboxStyle inboxStyle =
	        new NotificationCompat.InboxStyle();
	String[] events = new String[6];
	// Sets a title for the Inbox style big view
	inboxStyle.setBigContentTitle("Event tracker details:");
	
	// Moves events into the big view
	for (int i=0; i < events.length; i++) {

	    inboxStyle.addLine(events[i]);
	}
	// Moves the big view style object into the notification object.
	mBuilder.setStyle(inboxStyle);

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


}
