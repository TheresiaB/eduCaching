package com.life.educaching;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

        setTextHeader();
        addListenerOnButton();

    }

    public void setTextHeader(){

        TextView myAwesomeTextView = (TextView)findViewById(R.id.text_head);

        //in your OnCreate() method
        myAwesomeTextView.setText(RouteActivity.whichRoute);
    }

    public void addListenerOnButton() {

        final Context context = this;
        buttonNext = (Button) findViewById(R.id.button_next);
        buttonBack = (Button) findViewById(R.id.button_back);
        buttonNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(VideoViewActivity.this, "Button Clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(context, _Route1_OverviewMapActivity.class));
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(VideoViewActivity.this, "Button Clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(context, RouteActivity.class));
            }
        });



    }






}
