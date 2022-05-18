package com.example.shareshipment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;

import org.json.JSONArray;

public class CompleteWindow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_window);
    }
    public void assignShipment(View view) {
        String phoneNumber = ((MyApplication) this.getApplication()).getPhoneNumber();
        String resource = "/shipments/sender/?status=sent&senderPhoneNumber="+phoneNumber;
        CommonParams.enhancedJSONArrayRequest(new JSONArray(),resource, Request.Method.GET,getApplicationContext(),AnnouncedShipments.class);
    }
}