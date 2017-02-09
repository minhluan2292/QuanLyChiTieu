package com.example.nhatlam.nhomnm_quanlychitieu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ThongTinTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_test);
        //ActionBar actionbar = getActionBar();
        //actionbar.hide();

        ImageView imgGT = (ImageView)findViewById(R.id.gioithieu);
        imgGT.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(v.getContext(), Acti0.class);
                startActivity(i);
            }
        });

        ImageView imgBack = (ImageView)findViewById(R.id.btnBak);
        imgBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Intent i = new Intent(v.getContext(), MainActivity.class);
                //startActivity(i);
            }
        });

        Button btnFace = (Button)findViewById(R.id.btnFace);
        btnFace.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent myWebLink = new Intent(Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse("https://www.facebook.com"));
                startActivity(myWebLink);
            }
        });

        Button btnGplus = (Button)findViewById(R.id.btnGooglePlus);
        btnGplus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent myWebLink = new Intent(Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse("https://plus.google.com"));
                startActivity(myWebLink);
            }
        });
        Button btnTw = (Button)findViewById(R.id.btnTweet);
        btnTw.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent myWebLink = new Intent(Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse("https://twitter.com"));
                startActivity(myWebLink);
            }
        });
    }
}
