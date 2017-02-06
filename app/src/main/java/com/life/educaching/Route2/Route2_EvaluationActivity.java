package com.life.educaching.Route2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.VideoView;

import com.life.educaching.Model.Startpage_group_register_Activity;
import com.life.educaching.R;

public class Route2_EvaluationActivity extends AppCompatActivity {

    ImageButton buttonVideoPlayS1;
    ImageButton buttonVideoPauseS1;
    ImageButton buttonVideoPlayS2;
    ImageButton buttonVideoPause2;

    VideoView mVideoView1;
    VideoView mVideoView2;


    TextView mTextview;
    TextView textStation4;
    TextView groupName;
    SharedPreferences preferences;
    Uri myUriR2S1;
    Uri myUriR2S2;
    Button buttonNext;
    Button buttonBack;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route2_evaluation);
        setTextHeader();




        preferences = this.getSharedPreferences("prefsDatei1", MODE_PRIVATE);
        String name = preferences.getString("name", "KeinText");
        String value = preferences.getString("key", "KeinText");
        String value2 = preferences.getString("key2", "KeinText");
        String videoUri = preferences.getString("key3", "KeinText");
        String videoUriS2 = preferences.getString("key4", "KeinText");

        groupName = (TextView) findViewById(R.id.groupName);

        mTextview = (TextView) findViewById(R.id.station3answer);
        textStation4 = (TextView) findViewById(R.id.station4answer);


        buttonVideoPlayS1 = (ImageButton) findViewById(R.id.video_play_button);
        buttonVideoPauseS1 = (ImageButton) findViewById(R.id.video_pause_button);



        buttonVideoPlayS2 = (ImageButton) findViewById(R.id.video_play_button3);
        buttonVideoPause2 = (ImageButton) findViewById(R.id.video_pause_button3);

        mVideoView1 = (VideoView) findViewById(R.id.StationVideo1);
        mVideoView2 = (VideoView) findViewById(R.id.StationVideo2);

        myUriR2S1 = Uri.parse(videoUri);
        myUriR2S2 = Uri.parse(videoUriS2);

        mVideoView1.setVideoURI(myUriR2S1);
        mVideoView2.setVideoURI(myUriR2S2);

        groupName.setText(name);

        if(value.contains("KeinText")){
            mTextview.setText("Ihr habt eine Tonaufnahme gemacht");
        } else {
            mTextview.setText(value);
        }

        textStation4.setText(value2);



        buttonVideoPlayS1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVideoView1.start();
            }
        });

        buttonVideoPlayS2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVideoView2.start();
            }
        });

    }

    public void setTextHeader() {
        TextView myAwesomeTextView = (TextView) findViewById(R.id.text_head);
        myAwesomeTextView.setText("Route 2 - Auswertung");
    }

    public void addListenerOnButton() {

        final Context context = this;
        buttonNext = (Button) findViewById(R.id.route2Beenden);


        buttonNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                startActivity(new Intent(context, Startpage_group_register_Activity.class));
            }
        });
    }

}

