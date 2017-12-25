package com.example.fiza1.uolf17service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {


private boolean running=true;
    private static  final String TAG="MyIntentService";

    public MyIntentService() {

        super("MyIntentService");
    }



    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
       int i=0;
        int value=Integer.parseInt(intent.getExtras().get("Value:").toString());
        try{
            while(running && (i<value)){
                EventBus.getDefault().post(new Input(i));
                Log.d(TAG,"onHandleIntent:" +i);

                Thread.sleep(1000);
                i++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
public void onDestroy(){
    running=false;
    super.onDestroy();
}
}


