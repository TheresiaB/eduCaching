package com.life.educaching.Route2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.life.educaching.Model.DecideRouteActivity;
import com.life.educaching.Model.MapsActivity;
import com.life.educaching.R;
import com.life.educaching.Route1.Route1_station4_MapActivity;
import com.life.educaching.Route1.Route1_station4_TaskPictureActivity;

/**
 * Created by theresia on 05.01.17.
 */

public class Route2_station1_InfoPictureActivity extends AppCompatActivity {


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




}
