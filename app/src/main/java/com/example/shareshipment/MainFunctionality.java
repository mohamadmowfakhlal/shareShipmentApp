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
        if(getIntent().getStringExtra("city")!=null)
            ((MyApplication) this.getApplication()).setCity(getIntent().getStringExtra("city"));
    }
    public void announceShipment(View view) {
        Intent intent = new Intent(this,AnnounceShipment.class);
        startActivity(intent);
    }
    public void assignShipment(View view) {
        String phoneNumber = ((MyApplication) this.getApplication()).getPhoneNumber();
        String resource = "/shipments/deliveryMan/?status=assigned&deliveryManPhoneNumber="+phoneNumber;
        CommonParams.enhancedJSONArrayRequest(new JSONArray(),resource, Request.Method.GET,getApplicationContext(),AssignShipment.class);
    }

    public void deliverShipmentForOther(View view){
        Intent intent = new Intent(this,DeliverShipment.class);
        startActivity(intent);
    }


}