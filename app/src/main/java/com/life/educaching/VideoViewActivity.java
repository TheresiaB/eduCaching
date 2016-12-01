package com.life.educaching;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoViewActivity extends AppCompatActivity {
    Button buttonVideoPlay;
    VideoView myVideoView;
    Button buttonNext;
    Button buttonBack;
    Button buttonVideoPause;
    Button buttonVideoStop;
    VideoView mVideoView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        buttonVideoPlay = (Button) findViewById(R.id.video_play_button);
        buttonVideoPause = (Button) findViewById(R.id.video_pause_button);
        buttonVideoStop = (Button) findViewById(R.id.video_begin_button);

        myVideoView = (VideoView) findViewById(R.id.videoView);
        String uriPath = "android.resource://" + getPackageName() + "/" + R.raw.boss;
        Uri uri2 = Uri.parse(uriPath);
        mVideoView2 = (VideoView) findViewById(R.id.videoView);
        mVideoView2.setVideoURI(uri2);
        mVideoView2.requestFocus();

        buttonVideoPlay.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVideoView2.start();
            }
        });

        buttonVideoPause.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVideoView2.pause();
            }
        });

        buttonVideoStop.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVideoView2.stopPlayback();
            }
        });


        addListenerOnButton();

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
