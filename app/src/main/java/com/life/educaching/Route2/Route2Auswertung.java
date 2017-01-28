package com.life.educaching.Route2;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.SupportMapFragment;
import com.life.educaching.R;

/**
 * Created by theresia on 26.01.17.
 */

public class Route2Auswertung extends AppCompatActivity {

    TextView mTextview;
    TextView neu;
    VideoView video;
    Button videoPlay;
    SharedPreferences preferences;
    Uri myUri;
    private GoogleApiClient client;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auswertung);
        mTextview = (TextView) findViewById(R.id.loadText);
        neu = (TextView) findViewById(R.id.loadText2);
        video = (VideoView) findViewById(R.id.videoRoute2Station1);
        videoPlay = (Button) findViewById(R.id.videoRecordPlay);
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        preferences = this.getSharedPreferences("prefsDatei1", MODE_PRIVATE);
        String value = preferences.getString("key", "KeinText");
        String value2 = preferences.getString("key2", "KeinText");
        String videoUri = preferences.getString("key3", "KeinText");

        myUri = Uri.parse(videoUri);

        mTextview.setText(value);
        neu.setText(value2);
        video.setVideoURI(myUri);

        videoPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                video.start();
            }
        });

    }
}
