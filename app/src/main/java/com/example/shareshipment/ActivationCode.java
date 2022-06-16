package com.example.shareshipment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActivationCode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_activition_code);
    }
    public void verify(View view) {
        Intent intent = new Intent(this, PasswordActivity.class);
        //toDo Rest call to verify that the code that received by sms is equal to one that is provided by that make us sure that owner of phone number is
        //real owner
        //JSONObject js = new JSONObject();
        //String resource = "/users";
        //CommonParams.jsonRequest(js,resource, Request.Method.GET,getApplicationContext());
        startActivity(intent);
    }
}