package com.life.educaching.Route2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.life.educaching.Model.HttpHandler;
import com.life.educaching.Model.InformationPictureActivity;
import com.life.educaching.Model.Station_Finished_Activity;
import com.life.educaching.R;
import com.life.educaching.Route1.Route1_station4_TaskPictureActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by theresia on 22.01.17.
 */

public class Route2_station4_TaskMultipleActivity extends AppCompatActivity {


    Button buttonNext;
    Button buttonBack;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button btnDisplay;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    public static String input;

    private String TAG = Route2_station4_TaskMultipleActivity.class.getSimpleName();
    String task_ue;
    String task_description_ue;
    String task_description;

    TextView task_ue_tv;
    TextView task_description_ue_tv;
    TextView task_description_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route2_station4_task_multiple);
        addListenerOnButton();
        setTextHeader();

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        preferences = this.getSharedPreferences("prefsDatei1", MODE_PRIVATE);
        editor = preferences.edit();

        task_description_tv = (TextView) findViewById(R.id.a_aufgabenbeschreibung);
        task_description_ue_tv = (TextView) findViewById(R.id.a_ueAufgabenbeschreibung);
        task_ue_tv = (TextView) findViewById(R.id.a_ueAufgabenloesung);

        new Route2_station4_TaskMultipleActivity.GetContacts().execute();

    }

    public void setTextHeader() {
        TextView myAwesomeTextView = (TextView) findViewById(R.id.text_head);
        myAwesomeTextView.setText("Station 4");
    }




    public void addListenerOnButton() {

        final Context context = this;
        buttonNext = (Button) findViewById(R.id.button_next);
        buttonBack = (Button) findViewById(R.id.button_back);
        buttonNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                int selectedId = radioGroup.getCheckedRadioButtonId();

                radioButton = (RadioButton) findViewById(selectedId);
                if(radioButton == null){
                    input = "Keine Antwort";
                } else {
                    input = radioButton.getText().toString();
                }

                editor.putString("key2", input);
                editor.commit();
                startActivity(new Intent(context, Route2_station4_Finished.class));
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                startActivity(new Intent(context, Route2_station4_InfoVideoActivity.class));
            }
        });
    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "http://educaching.f4.htw-berlin.de/route2station4task.php";
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray aufgabe = jsonObj.getJSONArray("Aufgabe");

                    // looping through All Stations
                    for (int j = 0; j < aufgabe.length(); j++) {
                        JSONObject d = aufgabe.getJSONObject(j);
                        String r_id = d.getString("r_id");
                        String s_id = d.getString("s_id");
                        String a_id = d.getString("a_id");
                        String a_name = d.getString("a_name");
                        String a_ueAufgabenbeschreibung = d.getString("a_ueAufgabenbeschreibung");
                        String a_aufgabenbeschreibung = d.getString("a_aufgabenbeschreibung");
                        String a_ueAufgabenloesung = d.getString("a_ueAufgabenloesung");
                        String a_hilfetext = d.getString("a_hilfetext");

                       task_description_ue=a_ueAufgabenbeschreibung;
                        task_description=a_aufgabenbeschreibung;
                        task_ue=a_ueAufgabenloesung;
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            task_description_ue_tv.setText(task_description_ue);
            task_description_tv.setText(task_description);
            task_ue_tv.setText(task_ue);
        }

    }
}
