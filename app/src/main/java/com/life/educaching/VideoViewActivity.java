package com.life.educaching;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class VideoViewActivity extends AppCompatActivity {
    Button clk;
    VideoView videov;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        clk = (Button) findViewById(R.id.button);
        videov = (VideoView) findViewById(R.id.videoView);
        VideoOnClickListener videoOnClickListener = new VideoOnClickListener();
        clk.setOnClickListener(videoOnClickListener);
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

}
