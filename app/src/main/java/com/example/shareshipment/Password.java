package com.example.shareshipment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;

import org.json.JSONException;
import org.json.JSONObject;

public class Password extends AppCompatActivity {
    EditText password;
    EditText repeatedPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
    }
    public void confirm(View view) throws JSONException {
        password = findViewById(R.id.password);
        String passwordValue = password.getText().toString();
        repeatedPassword = findViewById(R.id.repeatedPasswordValue);
        String repeatedPasswordValue = repeatedPassword.getText().toString();
        if(!passwordValue.equals(repeatedPasswordValue) ){
            Toast.makeText(getApplicationContext(),
                    "The password is not identical!",
                    Toast.LENGTH_LONG).show();
        }else{
            JSONObject js = new JSONObject();
            //phoneNumber should be taken from sign up information
            String phoneNumber = ((MyApplication) this.getApplication()).getPhoneNumber();
            js.put("phoneNumber",phoneNumber);
            js.put("active","true");
            js.put("password",passwordValue);
            String resource = "/users/";
            CommonParams.JSONRequestWithoutResponse(js,resource, Request.Method.PUT,getApplicationContext(),MainFunctionality.class);
        }
    }
}