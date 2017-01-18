package com.life.educaching.Model;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.life.educaching.R;
import com.life.educaching.Route1.Route1_OverviewMapActivity;
import com.life.educaching.Route2.Route2_OverviewMapActivity;

/**
 * Created by theresia on 01.12.16.
 */

public class DecideRouteActivity extends  AppCompatActivity{

    private Spinner spinner1;
    private Button btnSubmit;
    Button buttonTake;
    public static String whichRoute;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decide_route);
        addListenerOnButton();
        addListenerOnSpinnerItemSelection();
        ButtonTakeRoute();
    }



    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        btnSubmit = (Button) findViewById(R.id.button_take_route);
        btnSubmit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

            }

        });
    }

    public void ButtonTakeRoute() {


        spinner1 = (Spinner) findViewById(R.id.spinner1);
        final Context context = this;
        buttonTake = (Button) findViewById(R.id.button_take_route);


        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                System.err.println("**************" + arg2);

                switch (arg2) {
                    case 0:
                        final Intent i = new Intent();
                        i.setClass(DecideRouteActivity.this, Route1_OverviewMapActivity.class);
                        buttonTake.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View arg0) {
                                startActivity(i);
                            }
                        });
                        break;
                    case 1:
                        final Intent ir = new Intent();
                        ir.setClass(DecideRouteActivity.this, Route2_OverviewMapActivity.class);
                        buttonTake.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View arg0) {
                                startActivity(ir);
                            }
                        });
                        break;
                }}

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
    }
}
