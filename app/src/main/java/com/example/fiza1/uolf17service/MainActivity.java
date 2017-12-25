package com.example.fiza1.uolf17service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    EditText limit;
    TextView percent;
    private static final String TAG = "MainActivity";
    Button start,stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        limit= (EditText) findViewById(R.id.limit);
        percent= (TextView) findViewById(R.id.percent);

        start= (Button) findViewById(R.id.start);
        stop= (Button) findViewById(R.id.Stop);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this,MyIntentService.class);
                intent.putExtra("Data",percent.getText().toString());
                MainActivity.this.startService(intent);
            }
        });

      stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MainActivity.this,MyIntentService.class));

            }
        });
    }

    protected void onStart(){
        EventBus.getDefault().register(this);
        super.onStart();
    }
protected void onStop(){
    EventBus.getDefault().unregister(this);
    super.onStop();
}
@Subscribe(threadMode= ThreadMode.MAIN)
    public void input(Input input){
    Log.d(TAG,"input" +String.valueOf(input.getLimit()));
    percent.setText(String.valueOf(input.getLimit())+ " %");
}
}
