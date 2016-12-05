package com.life.educaching;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class StationMapsActivity extends AppCompatActivity {

    Button buttonNext;
    Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stationskarte);
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        TextView myTextview = (TextView) findViewById(R.id.text_head);
        myTextview.setTypeface(myTypeface);

        //wenn du das Video Template sehen möchtest kommentiere die nächste Zeile einfach nur ein!
        //startActivity(new Intent(this, VideoViewActivity.class));
        addListenerOnButton();
    }

    public void addListenerOnButton() {

        final Context context = this;
        buttonNext = (Button) findViewById(R.id.button_next);
        buttonBack = (Button) findViewById(R.id.button_back);


        buttonNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(StationMapsActivity.this, "Button Clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(context, Station1DoneActivity.class));
            }
        });
    }

    public void openMap (View view) {
        Intent intent = new Intent (this, MapsActivity.class);
        startActivity(intent);
    }
}
