package com.life.educaching.Route2;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.life.educaching.Model.DecideRouteActivity;
import com.life.educaching.Model.HttpHandler;
import com.life.educaching.Model.MapMethods;
import com.life.educaching.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Route2_OverviewMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap mMap;
    Button buttonNext;
    Button buttonBack;
    private String TAG = Route2_OverviewMapActivity.class.getSimpleName();
    String routeInfo;
    TextView info_route;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route2_overviewmap);
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        TextView myTextview = (TextView) findViewById(R.id.text_head);
        myTextview.setTypeface(myTypeface);
        setTextHeader();
        addListenerOnButton();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        info_route = (TextView) findViewById(R.id.r_text);
        new Route2_OverviewMapActivity.GetContacts().execute();
    }

    public void setTextHeader() {
        TextView myAwesomeTextView = (TextView) findViewById(R.id.text_head);
        myAwesomeTextView.setText("Übersichtskarte der Route 2");
    }

    public void addListenerOnButton() {

        final Context context = this;
        buttonNext = (Button) findViewById(R.id.button_next);
        buttonBack = (Button) findViewById(R.id.button_back);


        buttonNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                startActivity(new Intent(context, Route2_station1_MapActivity.class));
            }
        });
        buttonBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                startActivity(new Intent(context, DecideRouteActivity.class));
            }
        });


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng startLifeEV = new LatLng(52.4667117, 13.3285014);
        mMap.addMarker(new MarkerOptions().position(startLifeEV).title("Du bist hier").icon(MapMethods.createIcon(this, R.drawable.start_marker, 100, 100)));
        // Ein Marker in der ersten Station hinzufügen und die Kamera bewegen
        LatLng friedrichstrasse = new LatLng(52.5137447, 13.389356700000008);
        mMap.addMarker(new MarkerOptions().position(friedrichstrasse).title("Marker in der 1. Station").icon(MapMethods.createIcon(this, R.drawable.station1_icon, 150, 100)));
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(moeckernbruecke, 14));

        LatLng reichstag = new LatLng(52.5185353, 13.37318849999997);
        mMap.addMarker(new MarkerOptions().position(reichstag).title("Marker in der 2. Station").icon(MapMethods.createIcon(this, R.drawable.station2_icon, 150, 100)));

        LatLng schoenhauserstr = new LatLng(52.5263005, 13.407798899999989);
        mMap.addMarker(new MarkerOptions().position(schoenhauserstr).title("Marker in der 3. Station").icon(MapMethods.createIcon(this, R.drawable.station3_icon, 150, 100)));

        LatLng alexanderplatz = new LatLng(52.5215855, 13.411163999999985);
        mMap.addMarker(new MarkerOptions().position(alexanderplatz).title("Marker in der 4. Station").icon(MapMethods.createIcon(this, R.drawable.ziel_marker, 100, 100)));

        LatLng[] stationen = {startLifeEV, friedrichstrasse, reichstag, schoenhauserstr, alexanderplatz};

        LatLngBounds Route = MapMethods.calculateLatLngBounds(stationen);
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(Route, 110));

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.INTERNET}, 10);

            return;
        } else {
            setCurrentLocation();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    setCurrentLocation();
                }
                return;
        }
    }

    //die nächste Zeile wird als inkorrekt angezeigt, weil der Compiler denkt, dass wir die Überprüfung des Zugriffs nicht gemacht haben, bevor der Standort angezeigt wird. Die Abfrage der Berechtigung erfolge aber schon in der vorigen Methode
    public void setCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
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
            String url = "http://greencaching.de/route2overview.php";
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray route = jsonObj.getJSONArray("Route");

                    // looping through All Stations
                    JSONObject d = route.getJSONObject(0);
                    String r_id = d.getString("r_id");
                    String r_name = d.getString("r_name");
                    routeInfo = d.getString("r_text");

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
            info_route.setText(routeInfo);
        }

    }


}
