package com.life.educaching.Model;

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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.life.educaching.R;

import java.io.File;

/**
 * Created by priscilla on 16.01.17.
 */

public class CaptureVideoActivity extends AppCompatActivity {

    ImageButton buttonRecordVideo;
    ImageButton buttonVideoPlay;
    VideoView mVideoView;
    private int ACTIVITY_START_CAMERA_APP = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_video);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTextHeader();



        buttonRecordVideo = (ImageButton) findViewById(R.id.video_record_button);
        buttonVideoPlay = (ImageButton) findViewById(R.id.video_play_button);
        mVideoView = (VideoView) findViewById(R.id.videoView2);

        buttonRecordVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callCaptureVideoIntent = new Intent();
                callCaptureVideoIntent.setAction(MediaStore.ACTION_VIDEO_CAPTURE);

                startActivityForResult(callCaptureVideoIntent, ACTIVITY_START_CAMERA_APP);
            }
        });

        buttonVideoPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVideoView.start();
            }
        });


    }

    public void setTextHeader() {
        TextView myAwesomeTextView = (TextView) findViewById(R.id.text_head);
        myAwesomeTextView.setText("Route 1");
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ACTIVITY_START_CAMERA_APP && resultCode == RESULT_OK) {
            Uri videoUri = data.getData();
            mVideoView.setVideoURI(videoUri);
        }
    }
}






