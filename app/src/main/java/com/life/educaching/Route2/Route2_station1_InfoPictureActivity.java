package com.life.educaching.Route2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
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

import com.life.educaching.Model.DecideRouteActivity;
import com.life.educaching.Model.HttpHandler;
import com.life.educaching.Model.MapsActivity;
import com.life.educaching.R;
import com.life.educaching.Route1.Route1_station1_InfoVideoActivity;
import com.life.educaching.Route1.Route1_station4_MapActivity;
import com.life.educaching.Route1.Route1_station4_TaskPictureActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by theresia on 05.01.17.
 */

public class Route2_station1_InfoPictureActivity extends AppCompatActivity {
    private String TAG = Route2_station1_InfoPictureActivity.class.getSimpleName();
    String info_ue;
    String info_text;
    TextView info_ue_textview;
    TextView info_textview;

    Button buttonNext;
    Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route2_station1_info_picture);
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        TextView myTextview = (TextView) findViewById(R.id.text_head);
        myTextview.setTypeface(myTypeface);
        setTextHeader();
        addListenerOnButton();
       info_ue_textview =(TextView) findViewById(R.id.s_ueberschrift);
        info_textview=(TextView) findViewById(R.id.s_text);

        new Route2_station1_InfoPictureActivity.GetContacts().execute();
    }

    public void setTextHeader() {
        TextView myAwesomeTextView = (TextView) findViewById(R.id.text_head);
        myAwesomeTextView.setText("Station 1");
    }


    public void addListenerOnButton() {

        final Context context = this;
        buttonNext = (Button) findViewById(R.id.button_next);
        buttonBack = (Button) findViewById(R.id.button_back);

        buttonNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                startActivity(new Intent(context, Route2_station1_TaskVideoActivity.class));
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                startActivity(new Intent(context, Route2_station1_MapActivity.class));
            }
        });
    }

    public void openMap (View view) {
        Intent intent = new Intent (this, MapsActivity.class);
        startActivity(intent);
    }


    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(Route2_station1_InfoPictureActivity.this, "Json Data is downloading", Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "http://educaching.f4.htw-berlin.de/route2station1info.php";
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
                        info_ue=s_ueberschrift;
                        info_text=s_text;
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
            info_ue_textview.setText(info_ue);
            info_textview.setText(info_text);
        }

    }

}
