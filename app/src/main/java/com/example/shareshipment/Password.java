package com.example.shareshipment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Password extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
    }
    public void confirm(View view) {
        Intent intent = new Intent(this, MainFunctionality
                .class);
        //Todo Update user table by adding a password and provide activition code Rest API should be made with put
        startActivity(intent);
    }
}