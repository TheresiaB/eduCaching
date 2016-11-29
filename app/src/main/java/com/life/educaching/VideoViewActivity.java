package com.life.educaching;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoViewActivity extends AppCompatActivity {
    Button clk;
    VideoView videov;
    Button buttonNext;
    Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_video);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        clk = (Button) findViewById(R.id.button);
        videov = (VideoView) findViewById(R.id.videoView);
        VideoOnClickListener videoOnClickListener = new VideoOnClickListener();
        clk.setOnClickListener(videoOnClickListener);

        setContentView(R.layout.activity_information_video);
        addListenerOnButton();
    }

    public class VideoOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            videoplay();
        }
    }

    public void videoplay()
    {
        String videopath = "android.resource://"+getPackageName()+"/"+R.raw.boss;
        Uri uri = Uri.parse(videopath);
        videov.setVideoURI(uri);
        videov.start();
    }

    public void addListenerOnButton() {

        final Context context = this;
        buttonNext = (Button) findViewById(R.id.next);
        buttonBack = (Button) findViewById(R.id.button_back);
        buttonBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(VideoViewActivity.this, "Button Clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(context, MainActivity.class));
            }
        });

    }




}
