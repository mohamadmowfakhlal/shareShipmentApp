package com.example.shareshipment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class SignUp extends AppCompatActivity {
    String serverURL = "http://ec2-3-123-230-170.eu-central-1.compute.amazonaws.com:8080";
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
        RequestQueue queue =  Volley.newRequestQueue(getApplicationContext());
        fullName =  (EditText) findViewById(R.id.editTextTextPersonName2);
        phoneNumber = (EditText) findViewById(R.id.phone);
        city = (EditText) findViewById(R.id.city);
        postNumber = (EditText) findViewById(R.id.postNumber1);
        streetName = (EditText) findViewById(R.id.streetName);
        houseNumber = (EditText) findViewById(R.id.houseNumber);
        JSONObject js = new JSONObject();
        try {
            String full_Name = fullName.getText().toString();
            String phone_number = phoneNumber.getText().toString();
            String city_ = city.getText().toString();
            String postNumber_ = postNumber.getText().toString();
            String streetName_ = streetName.getText().toString();
            String houseNumber_ = houseNumber.getText().toString();
            js.put("userName", full_Name);
            js.put("phoneNumber",phone_number);
            js.put("city",city_);
            js.put("streetName",streetName_);
            js.put("houseNumber",houseNumber_);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Make request for JSONObject
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST, serverURL+"/users", js,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response!=null) {
                            try {
                                if(response.get("session") != null){
                                    //Intent intent = new Intent(getActivity(), DeviceScanActivity.class);
                                    //intent.putExtra("username", user_name);
                                    //startActivity(intent);
                                }else{
                                    //myToast = Toast.makeText(getActivity(), "failed to login !", Toast.LENGTH_SHORT);
                                    //myToast.show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        }) {

            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

        };
        queue.add(jsonObjReq);
        Intent intent = new Intent(this, ConfirmActivitionCode.class);
        startActivity(intent);
    }
}