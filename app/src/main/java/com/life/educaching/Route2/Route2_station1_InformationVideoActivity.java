package com.life.educaching.Route2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.life.educaching.Model.DecideRouteActivity;
import com.life.educaching.R;

/**
 * Created by theresia on 05.01.17.
 */

public class Route2_station1_InformationVideoActivity extends AppCompatActivity {
    ImageButton buttonVideoPlay;
    VideoView myVideoView;
    Button buttonNext;
    Button buttonBack;
    ImageButton buttonVideoPause;
    ImageButton buttonVideoStop;
    VideoView mVideoView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route2_station1_information_video);
        buttonVideoPlay = (ImageButton)findViewById(R.id.video_play_button);
        buttonVideoPause = (ImageButton)findViewById(R.id.video_pause_button);
        //buttonVideoStop = (Button) findViewById(R.id.video_begin_button);

        String uriPath = "android.resource://" + getPackageName() + "/" + R.raw.diesendungmitdermauslotuseffekt;
        Uri uri2 = Uri.parse(uriPath);
        mVideoView2 = (VideoView) findViewById(R.id.route2Station1Video);
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

        setTextHeader();
        addListenerOnButton();

    }

    public void setTextHeader(){

        TextView myAwesomeTextView = (TextView)findViewById(R.id.text_head);

        //in your OnCreate() method
        myAwesomeTextView.setText(DecideRouteActivity.whichRoute);
    }

    public void addListenerOnButton() {

        final Context context = this;
        buttonNext = (Button) findViewById(R.id.button_next);
        buttonBack = (Button) findViewById(R.id.button_back);
        buttonNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(Route2_station1_InformationVideoActivity.this, "Button Clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(context, Route2_station1_TaskActivity.class));
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(Route2_station1_InformationVideoActivity.this, "Button Clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(context, Route2_station1_MapActivity.class));
            }
        });



    }




}
