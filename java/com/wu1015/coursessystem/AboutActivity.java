package com.wu1015.coursessystem;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {
    private ImageButton imageButton;
    private ImageView imageView;
    private ImageButton imageButton2;
    private TextView textView;
    private TextView textView2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        imageButton=findViewById(R.id.imageButton4);
        imageView=findViewById(R.id.imageView2);
        imageButton2=findViewById(R.id.imageButton3);
        textView=findViewById(R.id.textView15);
        textView2=findViewById(R.id.textView19);

        textView.setTextColor(Color.BLUE);
        textView2.setTextColor(Color.BLUE);
        textView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        textView2.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);


        initData();
    }
    public void initData(){
        imageView.setImageResource(R.mipmap.ic_launcher);
//        todo 修改链接
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://github.com/wu1015/CourseSystem");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://github.com/wu1015/CourseSystem");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://wu1015.github.io");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}