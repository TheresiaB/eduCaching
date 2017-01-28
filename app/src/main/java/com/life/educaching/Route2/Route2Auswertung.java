package com.life.educaching.Route2;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.life.educaching.R;

/**
 * Created by theresia on 26.01.17.
 */

public class Route2Auswertung extends AppCompatActivity {

    TextView mTextview;
    TextView neu;
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
        setContentView(R.layout.activity_auswertung);
        mTextview = (TextView) findViewById(R.id.loadText);
        neu = (TextView) findViewById(R.id.loadText2);
        videoR2S1 = (VideoView) findViewById(R.id.videoRoute2Station1);
        videoPlayR2S1 = (Button) findViewById(R.id.videoRecordPlay);
        videoR2S2 = (VideoView) findViewById(R.id.videoRoute2Station1S2);
        videoPlayR2S2 = (Button) findViewById(R.id.videoRecordPlayS2);


        preferences = this.getSharedPreferences("prefsDatei1", MODE_PRIVATE);
        String value = preferences.getString("key", "KeinText");
        String value2 = preferences.getString("key2", "KeinText");
        String videoUri = preferences.getString("key3", "KeinText");
        String videoUriS2 = preferences.getString("key4", "KeinText");

        myUriR2S1 = Uri.parse(videoUri);
        myUriR2S2 = Uri.parse(videoUriS2);

        mTextview.setText(value);
        neu.setText(value2);
        videoR2S1.setVideoURI(myUriR2S1);
        videoR2S2.setVideoURI(myUriR2S2);

        videoPlayR2S1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoR2S1.start();
            }
        });

        videoPlayR2S2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoR2S2.start();
            }
        });


    }
}
