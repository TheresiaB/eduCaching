package com.life.educaching;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        TextView myTextview = (TextView) findViewById(R.id.text_head);
        myTextview.setTypeface(myTypeface);

        //wenn du das Video Template sehen möchtest kommentiere die nächste Zeile einfach nur ein!
        startActivity(new Intent(this, VideoViewActivity.class));
        //addListenerOnButton();
    }

    public void addListenerOnButton() {

        final Context context = this;
        button = (Button) findViewById(R.id.next);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
               Toast.makeText(MainActivity.this, "Button Clicked", Toast.LENGTH_SHORT).show();
               //startActivity(new Intent(context, VideoViewActivity.class));
            }
        });

    }

    public void openMap (View view) {
    Intent intent = new Intent (this, MapsActivity.class);
    startActivity(intent);
    }





}
