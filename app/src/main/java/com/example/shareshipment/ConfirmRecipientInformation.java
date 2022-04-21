package com.example.shareshipment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ConfirmRecipientInformation extends AppCompatActivity {
    JSONObject js;
    private TextView recipientPhoneNumberTextView;
    private TextView recipientNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent previousIntent = getIntent();
        // receive the value by getStringExtra() method
        // and key must be same which is send by first activity
        String fee = previousIntent.getStringExtra("fee");
        String shipmentType = previousIntent.getStringExtra("shipmentType");
        String size = previousIntent.getStringExtra("size");
        String weight = previousIntent.getStringExtra("weight");
        String recipientPhoneNumber = previousIntent.getStringExtra("recipientPhoneNumber");
        //recipientName is from users table need an api call maybe in previous activity and pass it
        //recipientNameTextView.setText(recipientName);
        String notes = previousIntent.getStringExtra("notes");
        js = new JSONObject();
        try {
            js.put("fee",fee);
            js.put("shipmentType",shipmentType);
            js.put("size",size);
            js.put("weight",weight);
            js.put("notes",notes);
            js.put("status","sent");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            js.put("sentDate",formatter.format(date));
            JSONObject sender = new JSONObject();
            sender.put("phoneNumber","33");
            js.put("sender",sender);
            JSONObject receiver = new JSONObject();
            receiver.put("phoneNumber",recipientPhoneNumber);
            js.put("receiver",receiver);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_confirm_recipent_information);
        // create the get Intent object
        recipientPhoneNumberTextView =  (TextView) findViewById(R.id.phoneNumber);
        recipientPhoneNumberTextView.setText(recipientPhoneNumber);
        recipientNameTextView =  (TextView) findViewById(R.id.receipentName);
        // call a rest api to get a userName that belong to given phone number
        JSONObject js = new JSONObject();
        jsonRequest(js,"/users/phone/"+recipientPhoneNumber, Request.Method.GET,getApplicationContext());
    }

    public void ChooseRecipient(View view){
        Intent intent = new Intent(this,CompleteWindow.class);
        String resource = "/shipments";

        CommonParams.jsonRequest(js,resource,Request.Method.POST,getApplicationContext());
        startActivity(intent);
    }

    public void jsonRequest(JSONObject js, final String resource, int method, final Context context){
        RequestQueue queue =  Volley.newRequestQueue(context);
        Intent intent;

        // Make request for JSONObject

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(method, CommonParams.getServerURL()+resource, js,new Response.Listener<JSONObject>() {
            //Toast myToast;
            @Override
            public void onResponse(JSONObject response) {
                //intent.putExtra("userName",response.get("userName"));

                if(response!=null) {
                    try {
                        if(response.get("userName") != null){
                            recipientNameTextView.setText(response.getString("userName"));
                        }else{
                            //myToast = Toast.makeText(context,"failed  !", Toast.LENGTH_SHORT);
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
                System.out.println("error"+error);
                Log.d("JsonObject Error ",error.toString());

                if (error instanceof NetworkError) {
                } else if (error instanceof ServerError) {
                } else if (error instanceof AuthFailureError) {
                } else if (error instanceof ParseError) {
                } else if (error instanceof NoConnectionError) {
                } else if (error instanceof TimeoutError) {
                    Toast.makeText(context,
                            "Oops. Timeout error!",
                            Toast.LENGTH_LONG).show();
                }
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
    }
}