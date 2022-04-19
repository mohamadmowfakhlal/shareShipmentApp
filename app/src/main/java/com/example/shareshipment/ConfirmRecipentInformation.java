package com.example.shareshipment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ConfirmRecipentInformation extends AppCompatActivity {
    JSONObject js;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // create the get Intent object
        Intent previousIntent = getIntent();
        // receive the value by getStringExtra() method
        // and key must be same which is send by first activity
       /* String fee = previousIntent.getStringExtra("fee");
        String shipmentType = previousIntent.getStringExtra("shipmentType");
        String size = previousIntent.getStringExtra("size");
        String weight = previousIntent.getStringExtra("weight");
        String recipientPhoneNumber = previousIntent.getStringExtra("recipientPhoneNumber");
        String notes = previousIntent.getStringExtra("notes");
        js = new JSONObject();
        try {
            js.put("fee",fee);
            js.put("shipmentType",shipmentType);
            js.put("size",size);
            js.put("weight",weight);
            js.put("notes",notes);
            js.put("status","sent");
            long millis = System.currentTimeMillis();
            // creating a new object of the class Date
            java.util.Date date = new java.util.Date(millis);
            js.put("sentDate",date);
            JSONObject sender = new JSONObject();
            sender.put("phoneNumber","33");
            js.put("sender",sender);
            JSONObject reciver = new JSONObject();
            reciver.put("phoneNumber",recipientPhoneNumber);
            js.put("reciver",reciver);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        */
        setContentView(R.layout.activity_confirm_recipent_information);
    }

    public void ChooseRecipient(View view){
        Intent intent = new Intent(this,CompleteWindow.class);
        /*RequestQueue queue =  Volley.newRequestQueue(getApplicationContext());
        // Make request for JSONObject
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST, CommonParams.getServerURL()+"/shipment", js,
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


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

        };
        queue.add(jsonObjReq);
        */
        startActivity(intent);
    }
}