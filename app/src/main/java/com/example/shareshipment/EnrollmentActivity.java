package com.example.shareshipment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.Request;

public class EnrollmentActivity extends AppCompatActivity {
    EditText fullName;
    EditText phoneNumber;
    EditText city;
    EditText postNumber;
    EditText streetName;
    EditText houseNumber;
    EditText password;
    EditText repeatedPassword;
    FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }
    public void registerUser(View view) {
        fullName =  findViewById(R.id.fullName);
        phoneNumber =  findViewById(R.id.phone);
        city =  findViewById(R.id.city);
        postNumber =  findViewById(R.id.postNumber1);
        streetName = findViewById(R.id.streetName);
        houseNumber =  findViewById(R.id.houseNumber);
        JSONObject user = new JSONObject();
        JSONObject address = new JSONObject();

        try {
            String full_Name = fullName.getText().toString();
            String phone_number = phoneNumber.getText().toString();
            String city_ = city.getText().toString();
            ((MyApplicationData) this.getApplication()).setCity(city_);
            ((MyApplicationData) this.getApplication()).setFullName(full_Name);
            ((MyApplicationData) this.getApplication()).setPhoneNumber(phone_number);
            String postNumber_ = postNumber.getText().toString();
            String streetName_ = streetName.getText().toString();
            String houseNumber_ = houseNumber.getText().toString();
            user.put("userName", full_Name);
            user.put("phoneNumber",phone_number);
            address.put("city",city_);
            address.put("streetName",streetName_);
            address.put("houseNumber",houseNumber_);
            address.put("postNumber",postNumber_);
            user.put("address",address);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String resource = "/users";
        CommonParams.jsonRequestSignIn(user,resource,Request.Method.POST,getApplicationContext(),null,fm);

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
            String phoneNumber = ((MyApplicationData) this.getApplication()).getPhoneNumber();
            js.put("phoneNumber",phoneNumber);
            js.put("active","true");
            js.put("password",passwordValue);
            String resource = "/users/";
            CommonParams.jsonRequestSignIn(js,resource, Request.Method.PUT,getApplicationContext(),MainFunctionality.class);
        }


    }
    public void verifyCode(View view) {
        Fragment fragment = new PasswordFrame();

        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.my_nav_host_fragment, fragment);
        transaction.commit();
    }
}