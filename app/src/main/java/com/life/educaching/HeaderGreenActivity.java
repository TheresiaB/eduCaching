package com.life.educaching;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by theresia on 08.12.16.
 */

public class HeaderGreenActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_green);
        setTextHeader();

    }

    public void setTextHeader(){

        TextView myAwesomeTextView = (TextView)findViewById(R.id.text_head);

        //in your OnCreate() method
        myAwesomeTextView.setText(DecideRouteActivity.whichRoute);
    }
}
