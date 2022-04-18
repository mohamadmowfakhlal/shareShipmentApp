package com.example.shareshipment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class mainFunctionaility extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_functionaility);
    }
    public void annouceShipment(View view) {
        Intent intent = new Intent(this,AnnounceShipment.class);
        startActivity(intent);
    }
}