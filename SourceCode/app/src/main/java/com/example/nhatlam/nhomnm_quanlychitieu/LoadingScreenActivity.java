package com.example.nhatlam.nhomnm_quanlychitieu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoadingScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        LoadingScreenTask loading  = new LoadingScreenTask(getApplicationContext());
       loading.execute();
    }
}
