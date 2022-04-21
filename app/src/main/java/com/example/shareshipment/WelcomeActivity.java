package com.example.shareshipment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }
    public void signUp(View view) {
        //ToDo call to rest api to send a sms to verify the phone number is a correct one
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }
}