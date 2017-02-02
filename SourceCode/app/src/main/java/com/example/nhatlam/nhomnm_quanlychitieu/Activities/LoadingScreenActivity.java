package com.example.nhatlam.nhomnm_quanlychitieu.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nhatlam.nhomnm_quanlychitieu.AsyncTask.LoadingScreenTask;
import com.example.nhatlam.nhomnm_quanlychitieu.R;

public class LoadingScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        LoadingScreenTask loading  = new LoadingScreenTask(getApplicationContext());
       loading.execute();
    }
}
