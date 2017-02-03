package com.life.educaching.Route1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.life.educaching.Model.HttpHandler;
import com.life.educaching.Model.InformationPictureActivity;
import com.life.educaching.Model.InformationVideoActivity;
import com.life.educaching.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by theresia on 21.01.17.
 */

public class Route1_station4_TaskPictureActivity extends AppCompatActivity {

    Button buttonNext;
    Button buttonBack;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    public static String input;
    Uri mUri = null;
    String pictureString;
    private String TAG = Route1_station4_TaskPictureActivity.class.getSimpleName();
    private ListView lv;
    Bitmap inputPicture;


    ArrayList<HashMap<String, String>> routeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route1_station4_task_picture);
        addListenerOnButton();
        setTextHeader();
        routeList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);
        preferences = this.getSharedPreferences("prefsDatei2", MODE_PRIVATE);
        editor = preferences.edit();

        new Route1_station4_TaskPictureActivity.GetContacts().execute();
    }

    public void setTextHeader() {
        TextView myAwesomeTextView = (TextView) findViewById(R.id.text_head);
        myAwesomeTextView.setText("Station 4");
    }


    static final int REQUEST_IMAGE_CAPTURE = 1;

    public void dispatchTakePictureIntent(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView mImageView = (ImageView) findViewById(R.id.fotoCameraView);
            mImageView.setImageBitmap(imageBitmap);
            inputPicture = imageBitmap;
            //Button button = (Button) findViewById(R.id.fotoAufnehmen);
            //button.setText("Ein Neues Foto machen");
        }
    }


    public static String encodeToBase64(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
        return imageEncoded;
    }

    public void addListenerOnButton() {

        final Context context = this;
        buttonNext = (Button) findViewById(R.id.button_next);
        buttonBack = (Button) findViewById(R.id.button_back);
        buttonNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if(inputPicture == null){
                    pictureString = "drawable://" + R.drawable.fragezeichen;
                    editor.putString("picture", pictureString);
                } else {
                    editor.putString("picture", encodeToBase64(inputPicture));
                }

                editor.commit();
                startActivity(new Intent(context, Route1_station4_Finished.class));
            }
        });
        buttonBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                startActivity(new Intent(context, Route1_station4_InfoPictureActivity.class));
            }
        });

    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(Route1_station4_TaskPictureActivity.this, "Json Data is downloading", Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "http://educaching.f4.htw-berlin.de/route1station4task.php";
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

                        // tmp hash map for single Route
                        HashMap<String, String> routes = new HashMap<>();

                        // adding each child node to HashMap key => value
                        routes.put("r_id", r_id);
                        routes.put("s_id", s_id);
                        routes.put("a_id", a_id);
                        routes.put("a_name", a_name);
                        routes.put("a_ueAufgabenbeschreibung", a_ueAufgabenbeschreibung);
                        routes.put("a_aufgabenbeschreibung", a_aufgabenbeschreibung);
                        routes.put("a_ueAufgabenloesung", a_ueAufgabenloesung);
                        routes.put("a_hilfetext", a_hilfetext);

                        // adding Route, Station, Aufgabe to route list
                        routeList.add(routes);
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
            ListAdapter adapter = new SimpleAdapter(Route1_station4_TaskPictureActivity.this, routeList, R.layout.activity_route1_station4_task_picture, new String[]{"a_ueAufgabenbeschreibung", "a_aufgabenbeschreibung","a_ueAufgabenloesung"},
                    new int[]{R.id.a_ueAufgabenbeschreibung, R.id.a_aufgabenbeschreibung, R.id.a_ueAufgabenloesung});
            lv.setAdapter(adapter);
        }

    }
}
