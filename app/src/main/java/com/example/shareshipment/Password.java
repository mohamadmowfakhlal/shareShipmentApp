package com.example.shareshipment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;

import org.json.JSONException;
import org.json.JSONObject;

public class Password extends AppCompatActivity {
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
    }
    public void confirm(View view) throws JSONException {
        Intent intent = new Intent(this, MainFunctionality
                .class);
        startActivity(intent);
        password = findViewById(R.id.editTextTextPassword);
        String pass = password.getText().toString();
        JSONObject js = new JSONObject();
        js.put("phoneNumber","004542332945");
        js.put("active","true");
        js.put("password",pass);
        String resource = "/users/";
        CommonParams.jsonRequest(js,resource, Request.Method.PUT,getApplicationContext());
    }
}