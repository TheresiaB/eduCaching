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

/**
 * Created by karolin on 18.12.16.
 */

public class Startpage_group_register_Activity extends AppCompatActivity {

    Button buttonNewGroup;
    EditText inputText;
    public static String input;
    SharedPreferences preferencesR2;
    SharedPreferences preferencesR1;
    SharedPreferences.Editor editorR2;
    SharedPreferences.Editor editorR1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startpage_group_register);
        addListenerOnButton();
        preferencesR2 = this.getSharedPreferences("prefsDatei1", MODE_PRIVATE);
        editorR2 = preferencesR2.edit();

        preferencesR1 = this.getSharedPreferences("prefsDatei2", MODE_PRIVATE);
        editorR1 = preferencesR1.edit();


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
                editorR2.putString("name", input);
                editorR1.putString("name", input);
                editorR2.commit();
                editorR1.commit();
                startActivity(new Intent(context, DecideRouteActivity.class));
            }
        });
    }
}
