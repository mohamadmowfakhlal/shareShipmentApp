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

public class Recipent extends AppCompatActivity {
    EditText recipientPhoneNumber;
    EditText notes;
    private String test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipent);
        recipientPhoneNumber = (EditText) findViewById(R.id.recipientPhoneNumber);
        notes = (EditText) findViewById(R.id.notes);
    }
    public void ChooseRecipient(View view){
        Intent intent = new Intent(this, ConfirmRecipientInformation.class);
        // create the get Intent object
        Intent previousIntent = getIntent();
        // receive the value by getStringExtra() method
        // and key must be same which is send by first activity
        String fee = previousIntent.getStringExtra("fee");
        String shipmentType = previousIntent.getStringExtra("shipmentType");
        String size = previousIntent.getStringExtra("size");
        String weight = previousIntent.getStringExtra("weight");
        intent.putExtra("shipmentType",shipmentType);
        intent.putExtra("fee",fee);
        intent.putExtra("size",size);
        intent.putExtra("weight",weight);
        //New fields added on this activity
        intent.putExtra("recipientPhoneNumber",recipientPhoneNumber.getText().toString());
        intent.putExtra("notes",notes.getText().toString());
        startActivity(intent);
    }



}