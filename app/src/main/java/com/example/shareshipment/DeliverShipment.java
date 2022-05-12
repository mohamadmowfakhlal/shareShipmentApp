package com.example.shareshipment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DeliverShipment extends AppCompatActivity {
    EditText destinationCity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver_shipment);
    }

    public void findShipment(View view){
        //ToDo API get shipment for source and destination city
        // call a rest api to get a userName that belong to given phone number
        destinationCity = (EditText) findViewById(R.id.destinationCity);
        // source city is taken from login
        String resource = "/shipments/pickup/?sourceCity=aarhus&destinationCity="+destinationCity.getText().toString();
        jsonRequest(new JSONArray(),resource, Request.Method.GET,getApplicationContext());

    }

    public void jsonRequest(JSONArray js, final String resource, int method, final Context context){
        RequestQueue queue =  Volley.newRequestQueue(context);
        Intent intent;

        // Make request for JSONObject

        JsonArrayRequest jsonObjReq = new JsonArrayRequest(method, CommonParams.getServerURL()+resource, js,new Response.Listener<JSONArray>() {
            //Toast myToast;
            @Override
            public void onResponse(JSONArray  response) {
                System.out.println("null");
                //intent.putExtra("userName",response.get("userName"));
                if(response!=null) {
                    System.out.println("Test");
                    Intent intent = new Intent(getApplicationContext(), AvailableShipments.class);
                    intent.putExtra("shipments",response.toString());
                    startActivity(intent);
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