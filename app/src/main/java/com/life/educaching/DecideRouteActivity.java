package com.life.educaching;

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

                Toast.makeText(DecideRouteActivity.this,
                        "OnClickListener : " +
                                "\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();
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
                        whichRoute = "Route 1";
                        i.setClass(DecideRouteActivity.this, InformationPictureActivity.class);
                        buttonTake.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View arg0) {
                                Toast.makeText(DecideRouteActivity.this, "Button Clicked", Toast.LENGTH_SHORT).show();
                                startActivity(i);
                            }
                        });
                        break;
                    case 1:
                        final Intent ir = new Intent();
                        whichRoute = "Route 2";
                        ir.setClass(DecideRouteActivity.this, InformationVideoActivity.class);
                        buttonTake.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View arg0) {
                                Toast.makeText(DecideRouteActivity.this, "Button Clicked", Toast.LENGTH_SHORT).show();
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
