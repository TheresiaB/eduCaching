package com.life.educaching.Route2;

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
import com.life.educaching.Route1.Route1_station1_MapActivity;
import com.life.educaching.Route1.Route1_station1_TaskVideoActivity;
import com.life.educaching.Route1.Route1_station3_InfoVideoActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by theresia on 22.01.17.
 */

public class Route2_station3_InfoVideoActivity extends AppCompatActivity {

    ImageButton buttonVideoPlay;
    VideoView myVideoView;
    Button buttonNext;
    Button buttonBack;
    ImageButton buttonVideoPause;
    ImageButton buttonVideoStop;
    VideoView mVideoView2;
    MediaController mediaController;

    private String TAG = Route2_station3_InfoVideoActivity.class.getSimpleName();
    private ListView lv;

    ArrayList<HashMap<String, String>> routeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route2_station3_info_video);
        buttonVideoPlay = (ImageButton)findViewById(R.id.video_play_button);
        buttonVideoPause = (ImageButton)findViewById(R.id.video_pause_button);
        buttonVideoStop = (ImageButton) findViewById(R.id.video_stop_button);
        mediaController = new MediaController(this);

        String uriPath = "android.resource://" + getPackageName() + "/" + R.raw.nano;
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

        routeList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);

        new Route2_station3_InfoVideoActivity.GetContacts().execute();

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
                startActivity(new Intent(context, Route2_station3_TaskTextActivity.class));
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                startActivity(new Intent(context, Route2_station3_MapActivity.class));
            }
        });



    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(Route2_station3_InfoVideoActivity.this, "Json Data is downloading", Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "http://educaching.f4.htw-berlin.de/route2station3info.php";
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

                        // tmp hash map for single Route
                        HashMap<String, String> routes = new HashMap<>();

                        // adding each child node to HashMap key => value
                        routes.put("r_id", ro_id);
                        routes.put("s_id", s_id);
                        routes.put("s_name", s_name);
                        routes.put("s_text", s_text);
                        routes.put("s_ueberschrift", s_ueberschrift);
                        routes.put("s_material", s_material);

                        // adding Route, Station, Aufgabe to route list
                        routeList.add(routes);
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
            ListAdapter adapter = new SimpleAdapter(Route2_station3_InfoVideoActivity.this, routeList, R.layout.activity_route2_station3_info_video, new String[]{"s_text", "s_ueberschrift"},
                    new int[]{R.id.s_text, R.id.s_ueberschrift});
            lv.setAdapter(adapter);
        }

    }
}
