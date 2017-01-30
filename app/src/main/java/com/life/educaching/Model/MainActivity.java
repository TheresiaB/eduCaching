package com.life.educaching.Model;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.life.educaching.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private ListView lv;

    ArrayList<HashMap<String, String>> routeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        routeList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);

        new GetContacts().execute();
    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this, "Json Data is downloading", Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "http://educaching.f4.htw-berlin.de/db.php";
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray route = jsonObj.getJSONArray("Route");

                    // looping through All Routes
                    for (int i = 0; i < route.length(); i++) {
                        JSONObject c = route.getJSONObject(i);
                        String r_id = c.getString("r_id");
                        String r_name = c.getString("r_name");
                        String r_text = c.getString("r_text");

                        // Getting JSON Array node
                        JSONArray station = c.getJSONArray("Station");

                        // looping through All Stations
                        for (int j = 0; j < station.length(); j++) {
                            JSONObject d = station.getJSONObject(j);
                            String ro_id = d.getString("r_id");
                            String s_id = d.getString("s_id");
                            String s_name = d.getString("s_name");
                            String s_text = d.getString("s_text");
                            String s_ueberschrift = d.getString("s_ueberschrift");
                            String s_material = d.getString("s_material");

                            JSONArray aufgabe = d.getJSONArray("Aufgabe");

                            // looping through All Aufgaben
                            for (int k = 0; k < aufgabe.length(); k++) {
                                JSONObject e = aufgabe.getJSONObject(k);
                                String rou_id = e.getString("r_id");
                                String st_id = e.getString("s_id");
                                String a_id = e.getString("a_id");
                                String a_name = e.getString("a_name");
                                String a_ueAufgabenbeschreibung = e.getString("a_ueAufgabenbeschreibung");
                                String a_aufgabenbeschreibung = e.getString("a_aufgabenbeschreibung");
                                String a_ueAufgabenloesung = e.getString("a_ueAufgabenloesung");
                                String a_hilfetext = e.getString("a_hilfetext");

                                // tmp hash map for single Route
                                HashMap<String, String> routes = new HashMap<>();

                                // adding each child node to HashMap key => value
                                routes.put("r_id", r_id);
                                routes.put("r_name", r_name);
                                routes.put("r_text", r_text);
                                routes.put("r_id", ro_id);
                                routes.put("s_id", s_id);
                                routes.put("s_name", s_name);
                                routes.put("s_text", s_text);
                                routes.put("s_ueberschrift", s_ueberschrift);
                                routes.put("s_material", s_material);
                                routes.put("r_id", rou_id);
                                routes.put("s_id", st_id);
                                routes.put("a_id", a_id);
                                routes.put("a_name", a_name);
                                routes.put("a_ueAufgabenbeschreibung", a_ueAufgabenbeschreibung);
                                routes.put("a_aufgabenbeschreibung", a_aufgabenbeschreibung);
                                routes.put("a_ueAufgabenloesung", a_ueAufgabenloesung);
                                routes.put("a_hilfetext", a_hilfetext);

                                // adding Route, Station, Aufgabe to route list
                                routeList.add(routes);
                            }
                        }
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
          /*  super.onPostExecute(result);
            ListAdapter adapter = new SimpleAdapter(MainActivity.this, routeList, R.layout.list_route, new String[]{"r_id", "r_name", "r_text", "r_id", "s_id", "s_name", "s_text", "s_ueberschrift", "s_material", "r_id", "s_id", "a_id", "a_name", "a_ueAufgabenbeschreibung", "a_aufgabenbeschreibung", "a_ueAufgabenloesung", "a_hilfetext"},
                    new int[]{R.id.r_id, R.id.r_name, R.id.r_text, R.id.ro_id, R.id.s_id, R.id.s_name, R.id.s_text, R.id.s_ueberschrift, R.id.s_material, R.id.rou_id, R.id.st_id, R.id.a_id, R.id.a_ueAufgabenbeschreibung, R.id.a_aufgabenbeschreibung, R.id.a_ueAufgabenloesung, R.id.a_hilfetext});
            lv.setAdapter(adapter);*/
        }
    }

}