package com.example.shareshipment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;

import org.json.JSONException;
import org.json.JSONObject;

public class SignIn extends AppCompatActivity {
    EditText phoneNumber;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }

    public void signIn(View view) throws JSONException {
        //ToDo API verification for phone number and password

        phoneNumber = findViewById(R.id.PhoneNumber);
        password  = findViewById(R.id.password);
        JSONObject login = new JSONObject();
        login.put("phoneNumber",phoneNumber.getText().toString());
        login.put("password",password.getText().toString());
        String resource = "/users/login";
        CommonParams.jsonRequestSignIn(login,resource, Request.Method.POST,getApplicationContext(),MainFunctionality.class);
        ((MyApplication) this.getApplication()).setPhoneNumber(phoneNumber.getText().toString());
    }
}