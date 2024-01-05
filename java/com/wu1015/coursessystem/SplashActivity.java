package com.wu1015.coursessystem;

import static android.os.SystemClock.sleep;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageView=findViewById(R.id.imageViewSplash);
        imageView.setImageResource(R.mipmap.ic_launcher_round);

        Thread thread=new Thread(){
            @Override
            public void run() {
                try {sleep(1000);
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);

                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
//        todo 显示的话要用线程 记得写blog
        thread.start();
    }
}