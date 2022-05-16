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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Recipient extends AppCompatActivity {
    EditText recipientPhoneNumber;
    EditText notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipent);
        recipientPhoneNumber = (EditText) findViewById(R.id.recipientPhoneNumber);
        notes = (EditText) findViewById(R.id.notes);
    }
    public void ChooseRecipient(View view){
        jsonRequest(new JSONObject(),"/users/"+recipientPhoneNumber.getText().toString(), Request.Method.GET,getApplicationContext(),ConfirmRecipientInformation.class);



    }

    public void jsonRequest(JSONObject js, final String resource, int method, final Context context,final Class ConfirmRecipientInformation){
        RequestQueue queue =  Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(method, CommonParams.getServerURL()+resource, js,new Response.Listener<JSONObject>() {
            //Toast myToast;
            @Override
            public void onResponse(JSONObject response) {
                //intent.putExtra("userName",response.get("userName"));

                if(response!=null) {
                    try {
                        if(response.get("userName") != null){
                            //recipientNameTextView.setText(response.getString("userName"));
                            Intent intent = new Intent(context,ConfirmRecipientInformation);
                            // create the get Intent object
                            Shipment shipment = getIntent().getParcelableExtra("shipment");
                            intent.putExtra("shipment", shipment);
                            intent.putExtra("recipientName",response.getString("userName"));
                            intent.putExtra("recipientPhoneNumber",recipientPhoneNumber.getText().toString());
                            intent.putExtra("notes",notes.getText().toString());
                            context.startActivity(intent);
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