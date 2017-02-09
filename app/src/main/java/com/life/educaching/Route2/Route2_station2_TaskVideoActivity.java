package com.life.educaching.Route2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import com.life.educaching.Route1.Route1_station2_Finished;
import com.life.educaching.Route1.Route1_station2_InfoPicutreActivity;

/**
 * Created by theresia on 22.01.17.
 */

public class Route2_station2_TaskVideoActivity extends AppCompatActivity {

    ImageButton mRecordView, mPlayView;
    ImageButton mPlayButtonView;
    VideoView mVideoView;
    private int ACTIVITY_START_CAMERA_APP = 0;
    Button buttonNext;
    Button buttonBack;
    String videoUriString;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route2_station2_task_video);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        addListenerOnButton();
        setTextHeader();
        preferences = this.getSharedPreferences("prefsDatei1", MODE_PRIVATE);
        editor = preferences.edit();

        mRecordView = (ImageButton) findViewById(R.id.video_record_button);
        mPlayView = (ImageButton) findViewById(R.id.video_play_button);
        //mPlayButtonView = (ImageButton) findViewById(R.id.imageButton1);
        mVideoView = (VideoView) findViewById(R.id.videoView2);

        mRecordView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callCaptureVideoIntent = new Intent();
                callCaptureVideoIntent.setAction(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(callCaptureVideoIntent, ACTIVITY_START_CAMERA_APP);
            }
        });

        mPlayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVideoView.start();
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void setTextHeader() {
        TextView myAwesomeTextView = (TextView) findViewById(R.id.text_head);
        myAwesomeTextView.setText("Station 2");
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ACTIVITY_START_CAMERA_APP && resultCode == RESULT_OK) {
            Uri videoUri = data.getData();
            mVideoView.setVideoURI(videoUri);
            videoUriString = videoUri.toString();
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("CaptureVideo Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    public void addListenerOnButton() {

        final Context context = this;
        buttonNext = (Button) findViewById(R.id.button_next);
        buttonBack = (Button) findViewById(R.id.button_back);

        buttonNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(Route2_station2_TaskVideoActivity.this, videoUriString, Toast.LENGTH_SHORT).show();
                editor.putString("key4", videoUriString);
                editor.commit();
                startActivity(new Intent(context, Route2_station2_Finished.class));
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                startActivity(new Intent(context, Route2_station2_InfoVideoActivity.class));
            }
        });
    }
}
