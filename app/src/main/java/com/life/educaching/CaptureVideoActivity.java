package com.life.educaching;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import java.io.File;

public class CaptureVideoActivity extends AppCompatActivity {

    Button mRecordView,mPlayView;
    VideoView mVideoView;
    private int ACTIVITY_START_CAMERA_APP = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_video);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecordView = (Button) findViewById (R.id.video_record_button);
        mPlayView = (Button) findViewById(R.id.video_play_button);
        mVideoView = (VideoView)findViewById(R.id.videoView2);

        mRecordView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent callCaptureVideoIntent = new Intent ();
                callCaptureVideoIntent.setAction(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(callCaptureVideoIntent, ACTIVITY_START_CAMERA_APP);
            }
        });

        mPlayView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mVideoView.start();
            }
        });
    }

    protected void onActivityResult (int requestCode, int resultCode, Intent data){
        if(requestCode == ACTIVITY_START_CAMERA_APP && resultCode == RESULT_OK){
            Uri videoUri = data.getData ();
            mVideoView.setVideoURI(videoUri);
        }
    }

}
