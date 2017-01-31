package com.life.educaching.Route2;

import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.VideoView;

import com.life.educaching.R;

public class Route2_EvaluationActivity extends AppCompatActivity {

    ImageButton buttonVideoPlayS1;
    ImageButton buttonVideoPauseS1;
    ImageButton buttonVideoPlayS2;
    ImageButton buttonVideoPause2;
    ImageButton buttonVideoPlay3;
    ImageButton buttonVideoPause3;
    ImageButton buttonVideoStop;
    VideoView mVideoView1;
    VideoView mVideoView2;
    VideoView mVideoView3;

    TextView mTextview;
    TextView neu;
    TextView groupName;
    VideoView videoR2S1;
    VideoView videoR2S2;
    Button videoPlayR2S1;
    Button videoPlayR2S2;
    SharedPreferences preferences;
    Uri myUriR2S1;
    Uri myUriR2S2;



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
        neu = (TextView) findViewById(R.id.station4answer);


        buttonVideoPlayS1 = (ImageButton) findViewById(R.id.video_play_button);
        buttonVideoPauseS1 = (ImageButton) findViewById(R.id.video_pause_button);



        buttonVideoPlayS2 = (ImageButton) findViewById(R.id.video_play_button);
        buttonVideoPause2 = (ImageButton) findViewById(R.id.video_pause_button3);

        mVideoView1 = (VideoView) findViewById(R.id.StationVideo1);
        mVideoView2 = (VideoView) findViewById(R.id.StationVideo2);

        myUriR2S1 = Uri.parse(videoUri);
        myUriR2S2 = Uri.parse(videoUriS2);

        mVideoView1.setVideoURI(myUriR2S1);
        mVideoView2.setVideoURI(myUriR2S2);

        groupName.setText(name);

        mTextview.setText(value);
        neu.setText(value2);



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

}

