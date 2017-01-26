package com.life.educaching.Route1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.life.educaching.Model.MapsActivity;
import com.life.educaching.Model.TaskFotoActivity;
import com.life.educaching.R;

/**
 * Created by theresia on 07.01.17.
 */

public class Route1_station2_Finished extends AppCompatActivity {


    Button buttonNext;
    Button buttonBack;
    Button buttonStationDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route1_station2_finished);
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        TextView myTextview = (TextView) findViewById(R.id.text_head);
        myTextview.setTypeface(myTypeface);
        setTextHeader();
        setText();
        addListenerOnButton();
    }

    public void setTextHeader() {
        TextView myAwesomeTextView = (TextView) findViewById(R.id.text_head);
        myAwesomeTextView.setText("Station 2");
    }

    public void setText() {
        TextView myAwesomeTextView = (TextView) findViewById(R.id.text_finished);
        myAwesomeTextView.setText("Station 2 beendet");
    }

    public void addListenerOnButton() {

        final Context context = this;
        buttonBack = (Button) findViewById(R.id.button_back);
        buttonStationDone = (Button) findViewById(R.id.Station_1_abschließen) ;

        buttonBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                startActivity(new Intent(context, Route1_station2_TaskVideoActivity.class));

            }
        });
        buttonStationDone.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View arg0)
            {
                startActivity(new Intent(context, Route1_station3_MapActivity.class));
            }
        });


    }

    public void openMap (View view) {
        Intent intent = new Intent (this, MapsActivity.class);
        startActivity(intent);
    }

}
