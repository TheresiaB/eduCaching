package com.life.educaching;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by theresia on 01.12.16.
 */

public class RouteActivity extends  AppCompatActivity{

    private Spinner spinner1;
    private Button btnSubmit;
    Button buttonTake;


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

                Toast.makeText(RouteActivity.this,
                        "OnClickListener : " +
                                "\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void ButtonTakeRoute() {

        final Context context = this;
        buttonTake = (Button) findViewById(R.id.button_take_route);

        buttonTake.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(RouteActivity.this, "Button Clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(context, PictureActivity.class));
            }
        });
    }
}
