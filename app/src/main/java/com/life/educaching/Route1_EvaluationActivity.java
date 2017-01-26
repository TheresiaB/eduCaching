package com.life.educaching;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.VideoView;

public class Route1_EvaluationActivity extends AppCompatActivity {

    ImageButton buttonVideoPlay;
    ImageButton buttonVideoPause;
    Button buttonNext;
    Button buttonBack;

    ImageButton buttonVideoPlay2;
    ImageButton buttonVideoPause2;
    ImageButton buttonVideoStop;
    VideoView mVideoView1;
    VideoView mVideoView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route1__evaluation);
        buttonVideoPlay = (ImageButton)findViewById(R.id.video_play_button);
        buttonVideoPause = (ImageButton)findViewById(R.id.video_pause_button);

        buttonVideoPlay2 = (ImageButton)findViewById(R.id.video_play_button2);
        buttonVideoPause2 = (ImageButton)findViewById(R.id.video_pause_button2);

        String uriPath = "android.resource://" + getPackageName() + "/" + R.raw.diesendungmitdermauslotuseffekt;
        Uri uri1 = Uri.parse(uriPath);
        mVideoView1 = (VideoView) findViewById(R.id.StationVideo1);
        mVideoView1.setVideoURI(uri1);

        buttonVideoPlay.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVideoView1.start();
            }
        });
        buttonVideoPause.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVideoView1.pause();
            }
        });




        String uriPath2 = "android.resource://" + getPackageName() + "/" + R.raw.diesendungmitdermauslotuseffekt;
        Uri uri2 = Uri.parse(uriPath2);
        mVideoView2 = (VideoView) findViewById(R.id.StationVideo2);
        mVideoView2.setVideoURI(uri2);

        buttonVideoPlay2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVideoView2.start();
            }
        });
        buttonVideoPause2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVideoView2.pause();
            }
        });

    }








}

