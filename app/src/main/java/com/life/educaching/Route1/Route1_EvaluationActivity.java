package com.life.educaching.Route1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.life.educaching.Model.DecideRouteActivity;
import com.life.educaching.Model.Startpage_group_register_Activity;
import com.life.educaching.R;

import java.io.IOException;
import java.io.InputStream;

public class Route1_EvaluationActivity extends AppCompatActivity {

    ImageButton buttonVideoPlayS1;
    ImageButton buttonVideoPauseS1;

    ImageButton buttonVideoPlayS2;
    ImageButton buttonVideoPauseS2;
    Button buttonNext;
    Button buttonBack;
    VideoView mVideoView1;
    VideoView mVideoView2;

    ImageButton buttonAudioPlay;
    ImageButton buttonAudioPause;

    SharedPreferences preferences;
    Uri myUriR1S1;
    Uri myUriR1S2;
    TextView mTextview;
    TextView groupName;

    MediaPlayer mediaPlayer ;
    ImageView imageView;
    TextView noPicture;
    Bitmap bitmap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route1_evaluation);
        setTextHeader();
        addListenerOnButton();

        preferences = this.getSharedPreferences("prefsDatei2", MODE_PRIVATE);
        String video1 = preferences.getString("video1", "KeinText");
        String video2 = preferences.getString("video2", "KeinText");
        String name = preferences.getString("name", "KeinText");
        String picture1 = preferences.getString("picture", "KeinText");
        String antwortText = preferences.getString("keyText", "KeinText");
        final String antwortTon = preferences.getString("keyTon", "KeinText");
        final String picture = preferences.getString("picture", "KeinText");


        imageView = (ImageView) findViewById(R.id.auswertungFoto);

        buttonVideoPlayS1 = (ImageButton) findViewById(R.id.video_play_buttonR1S1);
        buttonVideoPauseS1 = (ImageButton) findViewById(R.id.video_pause_buttonR1S1);
        mVideoView1 = (VideoView) findViewById(R.id.StationVideoR1S1);

        myUriR1S1 = Uri.parse(video1);
        mVideoView1.setVideoURI(myUriR1S1);
        mVideoView1.seekTo(0);

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
        mVideoView2.seekTo(100);

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

        groupName = (TextView) findViewById(R.id.groupNameR1);

        mTextview = (TextView) findViewById(R.id.stationR1S3answer);
        groupName.setText(name);

        buttonAudioPlay = (ImageButton) findViewById(R.id.audioPlay);
        buttonAudioPause = (ImageButton) findViewById(R.id.audioPause);


        if(antwortTon.contains("KeinText")){
            mTextview.setText(antwortText);
        } else {
            mTextview.setText("Ihr habt eine Tonaufnahme gemacht");
            mediaPlayer = new MediaPlayer();
            buttonAudioPlay.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Toast.makeText(Route1_EvaluationActivity.this, "Button gedrückt", Toast.LENGTH_SHORT).show();
                    try {
                        mediaPlayer.setDataSource(antwortTon);
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mediaPlayer.start();

                }
            });
            buttonAudioPause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mVideoView1.pause();
                }
            });

        }


            imageView.setImageBitmap(decodeToBase64(picture));


    }

    public void setTextHeader() {
        TextView myAwesomeTextView = (TextView) findViewById(R.id.text_head);
        myAwesomeTextView.setText("Route 1 - Auswertung");
    }

    public static Bitmap decodeToBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }

    public void addListenerOnButton() {

        final Context context = this;
        buttonNext = (Button) findViewById(R.id.buttonToStartpage);


        buttonNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                startActivity(new Intent(context, Startpage_group_register_Activity.class));
            }
        });
    }
}

