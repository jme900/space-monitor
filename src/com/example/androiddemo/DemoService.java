package com.example.androiddemo;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

public class DemoService extends Service  {
	

	private final IBinder mBinder = new StableIPBinder();


	public void onCreate() {
	}

	@Override
	public void onDestroy() {
		stopForeground(true);
		super.onDestroy();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
	    startUp();
		return (START_STICKY);
	}
	


  
	private void startUp() {
		Notification notice;
		//The intent to launch when the user clicks the expanded notification
		Intent intent = new Intent(this, MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

		PendingIntent pendIntent = PendingIntent.getActivity(this, 0, intent, 0);
	    // Build notification
	    // Actions are just fake
	    notice = new NotificationCompat.Builder(this)
	        .setContentTitle(getString(R.string.app_name))
	        .setContentText("Checking for malicious network connections")
	        .setSmallIcon(R.drawable.ic_launcher)
	        .setContentIntent(pendIntent)
	        .build();

		notice.flags |= Notification.FLAG_NO_CLEAR;
		startForeground(1, notice);
		
	}




	public class StableIPBinder extends Binder {
		DemoService getService() {
            // Return this instance of LocalService so clients can call public methods
            return DemoService.this;
        }
    }
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
   
  
 }