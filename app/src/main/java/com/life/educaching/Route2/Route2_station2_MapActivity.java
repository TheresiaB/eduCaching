package com.life.educaching.Route2;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.life.educaching.Model.DecideRouteActivity;
import com.life.educaching.Model.MapMethods;
import com.life.educaching.R;
import com.life.educaching.Route1.Route1_station1_MapActivity;
import com.life.educaching.Model.TaskTextAudioActivity;

public class Route2_station2_MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap mMap;
    Button buttonNext;
    //Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route2_station2__map);
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        TextView myTextview = (TextView) findViewById(R.id.text_head);
        myTextview.setTypeface(myTypeface);
        setTextHeader();
        addListenerOnButton();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void setTextHeader() {
        TextView myAwesomeTextView = (TextView) findViewById(R.id.text_head);
        myAwesomeTextView.setText("Route 2 - Station 2");
    }

    public void addListenerOnButton() {

        final Context context = this;
        buttonNext = (Button) findViewById(R.id.button_next);
        //buttonBack = (Button) findViewById(R.id.button_back);


        buttonNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                startActivity(new Intent(context, Route2_station2_InfoVideoActivity.class));
            }
        });
        /*buttonBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                startActivity(new Intent(context, Route2_station1_Finished.class));
            }
        });*/
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Marker in der 3. Station hinzufügen und die Kamera bewegen
        LatLng reichstag = new LatLng(52.5185353, 13.37318849999997);
        mMap.addMarker(new MarkerOptions().position(reichstag).title("Marker in der 2. Station").icon(MapMethods.createIcon(this, R.drawable.station2_icon, 150, 100)));

        LatLng friedrichstrasse = new LatLng(52.5137447, 13.389356700000008);
        mMap.addMarker(new MarkerOptions().position(friedrichstrasse).title("Du bist hier")).showInfoWindow();

        LatLngBounds route = MapMethods.calculateLatLngBounds(new LatLng[]{friedrichstrasse, reichstag});
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(route, 110));

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
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);

    }

}
