package com.life.educaching;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by theresia on 12.12.16.
 */

public class TaskMultipleChoice extends AppCompatActivity {

    Button buttonNext;
    Button buttonBack;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button btnDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_multiplechoice);
        addListenerOnButton();
        onRadioButtonClicked();
    }


    public void onRadioButtonClicked() {
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        btnDisplay = (Button) findViewById(R.id.btnDisplay);

        btnDisplay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);

                Toast.makeText(TaskMultipleChoice.this,
                        radioButton.getText(), Toast.LENGTH_SHORT).show();

            }

        });
    }


    public void addListenerOnButton() {

        final Context context = this;
        buttonNext = (Button) findViewById(R.id.button_next);
        buttonBack = (Button) findViewById(R.id.button_back);
        buttonNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(TaskMultipleChoice.this, "Button Clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(context, Station_Finished_Activity.class));
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(TaskMultipleChoice.this, "Button Clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(context, InformationPictureActivity.class));
            }
        });



    }

}
