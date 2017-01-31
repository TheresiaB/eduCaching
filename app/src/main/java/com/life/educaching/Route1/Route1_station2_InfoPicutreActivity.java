package com.life.educaching.Route1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.life.educaching.Model.DecideRouteActivity;
import com.life.educaching.Model.HttpHandler;
import com.life.educaching.Model.MapsActivity;
import com.life.educaching.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by theresia on 01.12.16.
 */

public class Route1_station2_InfoPicutreActivity extends AppCompatActivity {

    Button buttonNext;
    Button buttonBack;
    private String TAG = Route1_station2_InfoPicutreActivity.class.getSimpleName();
    private ListView lv;

    ArrayList<HashMap<String, String>> routeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route1_station2_info_picture);
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        TextView myTextview = (TextView) findViewById(R.id.text_head);
        myTextview.setTypeface(myTypeface);
        setTextHeader();
        addListenerOnButton();
        routeList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);

        new Route1_station2_InfoPicutreActivity.GetContacts().execute();
    }

    public void setTextHeader() {
        TextView myAwesomeTextView = (TextView) findViewById(R.id.text_head);
        myAwesomeTextView.setText("Station 2");
    }


    public void addListenerOnButton() {

        final Context context = this;
        buttonNext = (Button) findViewById(R.id.button_next);
        buttonBack = (Button) findViewById(R.id.button_back);

        buttonNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                startActivity(new Intent(context, Route1_station2_TaskVideoActivity.class));
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                startActivity(new Intent(context, Route1_station2_MapActivity.class));
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
            Toast.makeText(Route1_station2_InfoPicutreActivity.this, "Json Data is downloading", Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "http://educaching.f4.htw-berlin.de/route1station2info.php";
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
            ListAdapter adapter = new SimpleAdapter(Route1_station2_InfoPicutreActivity.this, routeList, R.layout.activity_route1_station2_info_picture, new String[]{"s_text", "s_ueberschrift"},
                    new int[]{R.id.s_text, R.id.s_ueberschrift});
            lv.setAdapter(adapter);
        }

    }



}
