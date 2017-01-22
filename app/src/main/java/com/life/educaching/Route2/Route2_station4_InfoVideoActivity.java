package com.life.educaching.Route2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.life.educaching.R;
import com.life.educaching.Route1.Route1_station1_MapActivity;
import com.life.educaching.Route1.Route1_station1_TaskVideoActivity;

/**
 * Created by theresia on 22.01.17.
 */

public class Route2_station4_InfoVideoActivity extends AppCompatActivity {


    ImageButton buttonVideoPlay;
    VideoView myVideoView;
    Button buttonNext;
    Button buttonBack;
    ImageButton buttonVideoPause;
    ImageButton buttonVideoStop;
    VideoView mVideoView2;
    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route2_station4_info_video);
        buttonVideoPlay = (ImageButton)findViewById(R.id.video_play_button);
        buttonVideoPause = (ImageButton)findViewById(R.id.video_pause_button);
        buttonVideoStop = (ImageButton) findViewById(R.id.video_stop_button);
        mediaController = new MediaController(this);

        String uriPath = "android.resource://" + getPackageName() + "/" + R.raw.diesendungmitdermauslotuseffekt;
        Uri uri2 = Uri.parse(uriPath);
        mVideoView2 = (VideoView) findViewById(R.id.route2Station1Video);
        mVideoView2.setVideoURI(uri2);
        mVideoView2.setMediaController(mediaController);
        mediaController.setAnchorView(mVideoView2);


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
                mVideoView2.resume();
            }
        });

        setTextHeader();
        addListenerOnButton();

    }

    public void setTextHeader() {
        TextView myAwesomeTextView = (TextView) findViewById(R.id.text_head);
        myAwesomeTextView.setText("Station 4");
    }

    public void addListenerOnButton() {

        final Context context = this;
        buttonNext = (Button) findViewById(R.id.button_next);
        buttonBack = (Button) findViewById(R.id.button_back);
        buttonNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                startActivity(new Intent(context, Route2_station4_TaskMultipleActivity.class));
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                startActivity(new Intent(context, Route2_station4_MapActivity.class));
            }
        });



    }
}
