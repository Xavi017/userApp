package com.example.weather_app;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private ExecutorService queue = Executors.newSingleThreadExecutor();

    private final static String KEY = "6112087e259146b376c27017bda9667c";
    private final static String DOMAIN = "https://api.openweathermap.org/data/2.5/weather";
    private final static String IMGDOMAIN = "https://openweathermap.org/img/w/";

    private final static String FORMAT = "https://api.openweathermap.org/data/2.5/weather?q=Cali,co&appid=6498e268f2de120d0cd71288c41cbcc6";

     EditText search_city;
     Button btn_search;
     TextView current_weather,current_min,current_max,val_weather;
     ImageView image;

    private Button btnGo = null;

    private double lng = 0;
    private double lat = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.app_bar);

        search_city = findViewById(R.id.search_city);
        btn_search = findViewById(R.id.btn_search);
        current_weather = findViewById(R.id.current_weather);
        current_max = findViewById(R.id.current_max);
        current_min = findViewById(R.id.current_min);
        val_weather = findViewById(R.id.val_weather);
        image = findViewById(R.id.imagen);
        btnGo = findViewById(R.id.btn_maps);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String query = search_city.getText().toString();
                search(query);

            }
        });

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lng != 0 && lat != 0) {
                    Uri uri = Uri.parse("geo:" + lat + "," + lng);
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);

                }
            }
        });

    }

    public void search(String query){

        final String queryTmp = query;

        Runnable thread = new Runnable() {
            @Override
            public void run() {
                String strUrl = DOMAIN + "?q=" + queryTmp + "&appid=" + KEY + "&units=metric&lang=es";
                URL url = null;
                CAFData remoteData = null;

                try {
                    url = new URL(strUrl);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                if (url != null){
                    remoteData = CAFData.dataWithContentsOfURL(url);
                    Log.d("DemoWeather", remoteData.toText());

                    try {
                        JSONObject root = new JSONObject(remoteData.toText());
                        JSONObject coord = root.getJSONObject("coord");
                        JSONArray weather = root.getJSONArray("weather");
                        JSONObject main = root.getJSONObject("main");

                        String desc = "";
                        String icon = "";
                        Bitmap bitmap = null;

                        lat = coord.getDouble("lat");
                        lng = coord.getDouble("lon");

                        if(weather.length() > 0){
                            JSONObject aWeather = weather.getJSONObject(0);
                            desc = aWeather.getString("description");
                            icon = aWeather.getString("icon");

                            strUrl = IMGDOMAIN + icon + ".png";
                            url = new URL(strUrl);
                            remoteData = CAFData.dataWithContentsOfURL(url);
                            if(remoteData != null) {
                                bitmap = remoteData.toImage();
                            }
                        }


                        final String descTemp = desc;
                        final Bitmap bitmapTemp = bitmap;
                        final float temp = (float) main.getDouble("temp");
                        final float tempMin = (float) main.getDouble("temp_min");
                        final float tempMax = (float) main.getDouble("temp_max");

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                current_weather.setText(String.valueOf(temp));
                                current_min.setText(String.valueOf(tempMin));
                                current_max.setText(String.valueOf(tempMax));
                                val_weather.setText(descTemp);
                                image.setImageBitmap(bitmapTemp);
                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        queue.execute(thread);
    }
}