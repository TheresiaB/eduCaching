package com.life.educaching;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

public class Alle_Stationen_geschafft extends AppCompatActivity implements OnMapReadyCallback{
    GoogleMap mMap;
    Button buttonNext;
    Button buttonBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alle__stationen_geschafft);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

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

        buttonBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(Alle_Stationen_geschafft.this, "Button Clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(context, VideoViewActivity.class));
            }
        });


    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Ein Marker in der ersten Station hinzufügen und die Kamera bewegen
        LatLng moeckernbruecke = new LatLng(52.49402689999999, 13.375908200000026);
        mMap.addMarker(new MarkerOptions().position(moeckernbruecke).title("Marker in der 1. Station").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(moeckernbruecke, 14));

        LatLng potsdamerplatz = new LatLng(52.5096488, 13.37594409999997);
        mMap.addMarker(new MarkerOptions().position(potsdamerplatz).title("Marker in der 2. Station").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        LatLng hauptbahnhof = new LatLng(52.5250839, 13.369402000000036);
        mMap.addMarker(new MarkerOptions().position(hauptbahnhof).title("Marker in der 3. Station").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        LatLng alexanderplatz = new LatLng(52.5215855, 13.411163999999985);
        mMap.addMarker(new MarkerOptions().position(alexanderplatz).title("Marker in der 4. Station").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        LatLng[] stationen = {moeckernbruecke, potsdamerplatz, hauptbahnhof, alexanderplatz};

        LatLngBounds Route = MapMethods.calculateLatLngBounds(stationen);
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(Route, 50));

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
        mMap.setMyLocationEnabled(true);
    }
}
