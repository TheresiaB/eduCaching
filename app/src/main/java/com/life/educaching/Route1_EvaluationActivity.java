package com.life.educaching;

import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

public class Route1_EvaluationActivity extends AppCompatActivity {

    ImageButton buttonVideoPlayS1;
    ImageButton buttonVideoPauseS1;

    ImageButton buttonVideoPlayS2;
    ImageButton buttonVideoPauseS2;
    Button buttonNext;
    Button buttonBack;

    ImageButton buttonVideoPlay2;
    ImageButton buttonVideoPause1;
    ImageButton buttonVideoPause2;
    ImageButton buttonVideoStop;
    VideoView mVideoView1;
    VideoView mVideoView2;

    SharedPreferences preferences;
    Uri myUriR1S1;
    Uri myUriR1S2;
    TextView mTextview;
    TextView groupName;
    ImageView picture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route1_evaluation);
        setTextHeader();

        preferences = this.getSharedPreferences("prefsDatei2", MODE_PRIVATE);
        String video1 = preferences.getString("video1", "KeinText");
        String video2 = preferences.getString("video2", "KeinText");
        String name = preferences.getString("name", "KeinText");
        String picture1 = preferences.getString("picture", "KeinText");
        String antwortText = preferences.getString("keyText", "KeinText");



        buttonVideoPlayS1 = (ImageButton) findViewById(R.id.video_play_buttonR1S1);
        buttonVideoPauseS1 = (ImageButton) findViewById(R.id.video_pause_buttonR1S1);
        mVideoView1 = (VideoView) findViewById(R.id.StationVideoR1S1);

        myUriR1S1 = Uri.parse(video1);
        mVideoView1.setVideoURI(myUriR1S1);

        buttonVideoPlayS1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVideoView1.start();
            }
        });

        buttonVideoPauseS1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVideoView1.pause();
            }
        });


        buttonVideoPlayS2 = (ImageButton) findViewById(R.id.video_play_buttonR1S2);
        buttonVideoPauseS2 = (ImageButton) findViewById(R.id.video_pause_buttonR1S2);
        mVideoView2 = (VideoView) findViewById(R.id.StationVideoR1S2);

        myUriR1S2 = Uri.parse(video2);
        mVideoView2.setVideoURI(myUriR1S2);

        buttonVideoPlayS2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVideoView2.start();
            }
        });

        buttonVideoPauseS2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVideoView2.pause();
            }
        });

        groupName = (TextView) findViewById(R.id.groupName);

        mTextview = (TextView) findViewById(R.id.stationR1S3answer);
        groupName.setText(name);

        mTextview.setText(antwortText);










    }

    public void setTextHeader() {
        TextView myAwesomeTextView = (TextView) findViewById(R.id.text_head);
        myAwesomeTextView.setText("Route 2 - Auswertung");
    }

}

