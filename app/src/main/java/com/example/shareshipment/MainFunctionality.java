package com.example.shareshipment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class MainFunctionality extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_functionaility);
    }
    public void announceShipment(View view) {
        Intent intent = new Intent(this,AnnounceShipment.class);
        startActivity(intent);
    }
    public void assignShipment(View view) {

        String resource = "/shipments/deliveryMan/?status=assigned&deliveryManPhoneNumber=004542332945";
        jsonRequest(new JSONArray(),resource, Request.Method.GET,getApplicationContext());
    }
    public void deliverShipmentForOther(View view){
        Intent intent = new Intent(this,DeliverShipment.class);
        startActivity(intent);
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
                    Intent intent = new Intent(getApplicationContext(), AssignShipment.class);
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