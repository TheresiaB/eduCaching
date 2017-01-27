package com.life.educaching.Route2;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.SupportMapFragment;
import com.life.educaching.R;

/**
 * Created by theresia on 26.01.17.
 */

public class Route2Auswertung extends AppCompatActivity {

    TextView mTextview;
    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auswertung);
        mTextview = (TextView) findViewById(R.id.loadText);
        /*mTextview.setText(getIntent().getStringExtra("input"));
        */
        preferences = this.getSharedPreferences("prefsDatei1", MODE_PRIVATE);
        String value = preferences.getString("key", "KeinText");

        mTextview.setText(value);

    }
}
