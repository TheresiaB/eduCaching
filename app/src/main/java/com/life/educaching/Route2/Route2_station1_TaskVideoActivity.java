package com.life.educaching.Route2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.life.educaching.Model.HttpHandler;
import com.life.educaching.R;
import com.life.educaching.Route1.Route1_station1_TaskVideoActivity;
import com.life.educaching.Route1.Route1_station2_Finished;
import com.life.educaching.Route1.Route1_station2_InfoPicutreActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by theresia on 05.01.17.
 */
public class Route2_station1_TaskVideoActivity extends AppCompatActivity {

    ImageButton mRecordView, mPlayView, mPause;
    ImageButton mPlayButtonView;
    VideoView mVideoView;
    private int ACTIVITY_START_CAMERA_APP = 0;
    Button buttonNext;
    Button buttonBack;
    private GoogleApiClient client;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    public static String input;
    Uri mUri = null;
    String videoUriString;
    private String TAG = Route2_station1_TaskVideoActivity.class.getSimpleName();
    private ListView lv;
    ArrayList<HashMap<String, String>> routeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route2_station1_task_video);
        addListenerOnButton();
        setTextHeader();
        preferences = this.getSharedPreferences("prefsDatei1", MODE_PRIVATE);
        editor = preferences.edit();

        mRecordView = (ImageButton) findViewById(R.id.video_record_button);
        mPlayView = (ImageButton) findViewById(R.id.video_play_button);
        //mPlayButtonView = (ImageButton) findViewById(R.id.imageButton1);
        mVideoView = (VideoView) findViewById(R.id.videoView2);
        mPause = (ImageButton) findViewById(R.id.taskVideoPauseS);

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
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        routeList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);

        new Route2_station1_TaskVideoActivity.GetContacts().execute();
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

    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("CaptureVideo Page")
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
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    public String getFileNameFromUrl(String path) {
        String[] pathArray = path.split("/");
        return pathArray[pathArray.length - 1];
    }



    public void addListenerOnButton() {

        final Context context = this;
        buttonNext = (Button) findViewById(R.id.button_next);
        buttonBack = (Button) findViewById(R.id.button_back);

        buttonNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(Route2_station1_TaskVideoActivity.this, videoUriString, Toast.LENGTH_SHORT).show();
                editor.putString("key3", videoUriString);
                editor.commit();
                startActivity(new Intent(context, Route2_station1_Finished.class));
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                startActivity(new Intent(context, Route2_station1_InfoPictureActivity.class));
            }
        });
    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(Route2_station1_TaskVideoActivity.this, "Json Data is downloading", Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "http://educaching.f4.htw-berlin.de/route2station1task.php";
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray aufgabe = jsonObj.getJSONArray("Aufgabe");

                    // looping through All Stations
                    for (int j = 0; j < aufgabe.length(); j++) {
                        JSONObject d = aufgabe.getJSONObject(j);
                        String r_id = d.getString("r_id");
                        String s_id = d.getString("s_id");
                        String a_id = d.getString("a_id");
                        String a_name = d.getString("a_name");
                        String a_ueAufgabenbeschreibung = d.getString("a_ueAufgabenbeschreibung");
                        String a_aufgabenbeschreibung = d.getString("a_aufgabenbeschreibung");
                        String a_ueAufgabenloesung = d.getString("a_ueAufgabenloesung");
                        String a_hilfetext = d.getString("a_hilfetext");

                        // tmp hash map for single Route
                        HashMap<String, String> routes = new HashMap<>();

                        // adding each child node to HashMap key => value
                        routes.put("r_id", r_id);
                        routes.put("s_id", s_id);
                        routes.put("a_id", a_id);
                        routes.put("a_name", a_name);
                        routes.put("a_ueAufgabenbeschreibung", a_ueAufgabenbeschreibung);
                        routes.put("a_aufgabenbeschreibung", a_aufgabenbeschreibung);
                        routes.put("a_ueAufgabenloesung", a_ueAufgabenloesung);
                        routes.put("a_hilfetext", a_hilfetext);

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
            ListAdapter adapter = new SimpleAdapter(Route2_station1_TaskVideoActivity.this, routeList, R.layout.activity_route2_station1_task_video, new String[]{"a_ueAufgabenbeschreibung", "a_aufgabenbeschreibung","a_ueAufgabenloesung"},
                    new int[]{R.id.a_ueAufgabenbeschreibung, R.id.a_aufgabenbeschreibung, R.id.a_ueAufgabenloesung});
            lv.setAdapter(adapter);
        }

    }
}
