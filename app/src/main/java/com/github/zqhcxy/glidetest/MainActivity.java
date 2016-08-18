package com.github.zqhcxy.glidetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.github.zqhcxy.glidetest.activity.AnimateActivity;
import com.github.zqhcxy.glidetest.activity.BlurImageActivity;
import com.github.zqhcxy.glidetest.activity.GlideLoadActivity;
import com.github.zqhcxy.glidetest.activity.NotificationTargetActivity;
import com.github.zqhcxy.glidetest.activity.TransformationsActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button bt1;
    private Button bt2;
    private Button bt3;
    private Button bt4;
    private Button bt5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);
        bt3 = (Button) findViewById(R.id.bt3);
        bt4 = (Button) findViewById(R.id.bt4);
        bt5 = (Button) findViewById(R.id.bt5);

        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt1:
                Intent intent_load = new Intent(MainActivity.this, GlideLoadActivity.class);
                startActivity(intent_load);
                break;
            case R.id.bt2:
                Intent intent_notify = new Intent(MainActivity.this, NotificationTargetActivity.class);
                startActivity(intent_notify);
                break;
            case R.id.bt3:
                Intent intent_blur = new Intent(MainActivity.this, BlurImageActivity.class);
                startActivity(intent_blur);
                break;
            case R.id.bt4:
                Intent intent_trf = new Intent(MainActivity.this, TransformationsActivity.class);
                startActivity(intent_trf);
                break;
            case R.id.bt5:
                Intent intent_animate = new Intent(MainActivity.this, AnimateActivity.class);
                startActivity(intent_animate);
                break;
        }
    }

}
