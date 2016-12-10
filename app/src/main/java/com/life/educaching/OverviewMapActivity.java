package com.life.educaching;

import android.*;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

public class OverviewMapActivity extends AppCompatActivity implements OnMapReadyCallback{
GoogleMap mMap;
    Button buttonNext;
    Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uebersichtskarte);
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        TextView myTextview = (TextView) findViewById(R.id.text_head);
        myTextview.setTypeface(myTypeface);
        setTextHeader();
        addListenerOnButton();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void setTextHeader(){

        TextView myAwesomeTextView = (TextView)findViewById(R.id.text_head);

        //in your OnCreate() method
        myAwesomeTextView.setText(RouteActivity.whichRoute);
    }

    public void addListenerOnButton() {

        final Context context = this;
        buttonNext = (Button) findViewById(R.id.button_next);
        buttonBack = (Button) findViewById(R.id.button_back);


        buttonNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(OverviewMapActivity.this, "Button Clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(context, StationMapsActivity.class));
            }
        });
        buttonBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(OverviewMapActivity.this, "Button Clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(context, VideoViewActivity.class));
            }
        });


    }


    /**
     * die Methode, die Markers setzt und die aktuelle Position zu ermitteln hilft
     * @param googleMap
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Ein Marker in der ersten Station hinzufügen und die Kamera bewegen
        LatLng moeckernbruecke = new LatLng(52.49402689999999, 13.375908200000026);
        mMap.addMarker(new MarkerOptions().position(moeckernbruecke).title("Marker in der 1. Station"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(moeckernbruecke, 14));

        LatLng potsdamerplatz = new LatLng(52.5096488, 13.37594409999997);
        mMap.addMarker(new MarkerOptions().position(potsdamerplatz).title("Marker in der 2. Station"));

        LatLng hauptbahnhof = new LatLng(52.5250839, 13.369402000000036);
        mMap.addMarker(new MarkerOptions().position(hauptbahnhof).title("Marker in der 3. Station"));

        LatLng alexanderplatz = new LatLng(52.5215855, 13.411163999999985);
        mMap.addMarker(new MarkerOptions().position(alexanderplatz).title("Marker in der 4. Station"));

        LatLng [] stationen = {moeckernbruecke, potsdamerplatz, hauptbahnhof, alexanderplatz};

        LatLngBounds Route = calculateLalLngBounds(stationen);
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(Route, 50));

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.INTERNET}, 10);

            return;
        }
        else
        {
            setCurrentLocation();
        }

    }

    /**
     * Methode zum Errechen der unteren linken (southwest) und oberen rechten (northeast) Ecke der Fläche, die auf dem Bildschirm angezeigt wird (LatLngBounds)
     * @param stationen
     * @return
     */
    public LatLngBounds calculateLalLngBounds(LatLng [] stationen)
{
    if (stationen.length>1) {
        //minimalen Breitengrad finden
        double south = stationen[0].latitude;
        for (int i = 1; i < stationen.length; i++) {
            if (south > stationen[i].latitude) {
                south = stationen[i].latitude;
            }
        }
        //maximalen Breitengrad finden
        double north = stationen[0].latitude;
        for (int i = 1; i < stationen.length; i++) {
            if (north < stationen[i].latitude) {
                north = stationen[i].latitude;
            }
        }
//minimalen Längengrad finden
        double west = stationen[0].longitude;
        for (int i = 1; i < stationen.length; i++) {
            if (west > stationen[i].longitude) {
                west = stationen[i].longitude;
            }
        }
//maximalen Längengrad finden
        double east = stationen[0].longitude;
        for (int i = 1; i < stationen.length; i++) {
            if (east < stationen[i].longitude) {
                east = stationen[i].longitude;
            }
        }
        LatLng southwest = new LatLng(south, west);
        LatLng northeast = new LatLng(north, east);
        return new LatLngBounds(southwest, northeast);
    }
    return new LatLngBounds(stationen[0], stationen[0]);
}
    /**
     * Methode, die aufgerufen wird, wenn der Nutzer den Zugriff auf den Standort zugelassen hat und zurück in der App ist
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
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

    public void setCurrentLocation() {
        mMap.setMyLocationEnabled(true);
    }
}
