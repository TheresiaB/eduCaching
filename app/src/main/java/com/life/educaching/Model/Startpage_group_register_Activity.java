package com.life.educaching.Model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.life.educaching.R;
import com.life.educaching.Route2.Route2_station3_TaskTextActivity;

/**
 * Created by karolin on 18.12.16.
 */

public class Startpage_group_register_Activity extends AppCompatActivity {

    Button buttonNewGroup;
    EditText inputText;
    public static String input;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startpage_group_register);
        addListenerOnButton();
        preferences = this.getSharedPreferences("prefsDatei1", MODE_PRIVATE);
        editor = preferences.edit();


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

    }

    public void addListenerOnButton() {

        final Context context = this;
        buttonNewGroup = (Button) findViewById(R.id.button_newgroup);
        inputText = (EditText) findViewById(R.id.textfield_groupname);

        buttonNewGroup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                input = inputText.getText().toString();
                Toast.makeText(Startpage_group_register_Activity.this, input, Toast.LENGTH_SHORT).show();
                editor.putString("name", input);
                editor.commit();
                startActivity(new Intent(context, DecideRouteActivity.class));
            }
        });
    }
}
