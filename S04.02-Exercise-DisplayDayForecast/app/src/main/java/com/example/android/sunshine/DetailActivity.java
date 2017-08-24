package com.example.android.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // done TODO (2) Display the weather forecast that was passed from MainActivity
        TextView mDisplayWeather = (TextView) findViewById(R.id.tv_weather_display);
        Intent intentDetailActivity = getIntent();
        if (intentDetailActivity != null){
            if (intentDetailActivity.hasExtra(Intent.EXTRA_TEXT))
                mDisplayWeather.setText(intentDetailActivity.getStringExtra(Intent.EXTRA_TEXT));
        }
    }
}