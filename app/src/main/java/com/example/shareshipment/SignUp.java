package com.example.shareshipment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.Request;

public class SignUp extends AppCompatActivity {
    EditText fullName;
    EditText phoneNumber;
    EditText city;
    EditText postNumber;
    EditText streetName;
    EditText houseNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }
    public void signUp(View view) {
        fullName =  (EditText) findViewById(R.id.editTextTextPersonName2);
        phoneNumber = (EditText) findViewById(R.id.phone);
        city = (EditText) findViewById(R.id.city);
        postNumber = (EditText) findViewById(R.id.postNumber1);
        streetName = (EditText) findViewById(R.id.streetName);
        houseNumber = (EditText) findViewById(R.id.houseNumber);
        JSONObject js = new JSONObject();
        JSONObject address = new JSONObject();

        try {
            String full_Name = fullName.getText().toString();
            String phone_number = phoneNumber.getText().toString();
            String city_ = city.getText().toString();
            String postNumber_ = postNumber.getText().toString();
            String streetName_ = streetName.getText().toString();
            String houseNumber_ = houseNumber.getText().toString();
            js.put("userName", full_Name);
            js.put("phoneNumber",phone_number);
            address.put("city",city_);
            address.put("streetName",streetName_);
            address.put("houseNumber",houseNumber_);
            address.put("postNumber",postNumber_);
            js.put("address",address);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String resource = "/users";
        CommonParams.jsonRequest(js,resource,Request.Method.POST,getApplicationContext());
        Intent intent = new Intent(this, ConfirmActivationCode.class);
        startActivity(intent);
    }


}