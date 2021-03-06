package com.life.educaching.Route1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.life.educaching.Model.HttpHandler;
import com.life.educaching.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by theresia on 21.01.17.
 */

public class Route1_station3_InfoVideoActivity extends AppCompatActivity {

    ImageButton buttonVideoPlay;
    VideoView myVideoView;
    Button buttonNext;
    Button buttonBack;
    ImageButton buttonVideoPause;
    ImageButton buttonVideoStop;
    VideoView mVideoView2;
    MediaController mediaController;
    private String TAG = Route1_station3_InfoVideoActivity.class.getSimpleName();

    protected String info_text;
    protected String info_ue;
    TextView info_textview;
    TextView info_ue_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route1_station3_info_video);
        buttonVideoPlay = (ImageButton)findViewById(R.id.video_play_button);
        buttonVideoPause = (ImageButton)findViewById(R.id.video_pause_button);
        buttonVideoStop = (ImageButton) findViewById(R.id.video_stop_button);
        mediaController = new MediaController(this);

        String uriPath = "android.resource://" + getPackageName() + "/" + R.raw.licht;
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

        info_textview=(TextView) findViewById(R.id.s_text);
        info_ue_textview=(TextView) findViewById(R.id.s_ueberschrift);

        new Route1_station3_InfoVideoActivity.GetContacts().execute();

    }

    public void setTextHeader() {
        TextView myAwesomeTextView = (TextView) findViewById(R.id.text_head);
        myAwesomeTextView.setText("Station 3");
    }

    public void addListenerOnButton() {

        final Context context = this;
        buttonNext = (Button) findViewById(R.id.button_next);
        buttonBack = (Button) findViewById(R.id.button_back);
        buttonNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                startActivity(new Intent(context, Route1_station3_TaskTextActivity.class));
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                startActivity(new Intent(context, Route1_station3_MapActivity.class));
            }
        });



    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "http://educaching.f4.htw-berlin.de/route1station3info.php";
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray station = jsonObj.getJSONArray("Station");

                    // looping through All Stations
                    for (int j = 0; j < station.length(); j++) {
                        JSONObject d = station.getJSONObject(j);
                        String ro_id = d.getString("r_id");
                        String s_id = d.getString("s_id");
                        String s_name = d.getString("s_name");
                        String s_text = d.getString("s_text");
                        String s_ueberschrift = d.getString("s_ueberschrift");
                        String s_material = d.getString("s_material");
                        info_text = s_text;
                        info_ue = s_ueberschrift;
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            info_textview.setText(info_text);
            info_ue_textview.setText(info_ue);
        }

    }

}
