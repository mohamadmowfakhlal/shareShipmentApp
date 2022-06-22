package com.example.shareshipment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;

import org.json.JSONArray;

public class MainFunctionality extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_functionaility);
    }
    public void announceShipment(View view) {
        Intent intent = new Intent(this, AnnounceTask.class);
        startActivity(intent);
    }
    public void assignShipment(View view) {
        String phoneNumber = ((MyApplicationData) this.getApplication()).getPhoneNumber();
        String resource = "/shipments/?status=assigned&userType=carrier&phoneNumber="+phoneNumber;
        CommonParams.enhancedJSONArrayRequest(new JSONArray(),resource, Request.Method.GET,getApplicationContext(), Tasks.class,(MyApplicationData) this.getApplication());
    }

    public void deliverShipmentForOther(View view){
        Intent intent = new Intent(this, StartTask.class);
        startActivity(intent);
    }
    public void trackActiveShipment(View view) {
        String phoneNumber = ((MyApplicationData) this.getApplication()).getPhoneNumber();
        String resource = "/shipments/?phoneNumber="+phoneNumber+"&userType=sender";
        CommonParams.enhancedJSONArrayRequest(new JSONArray(),resource, Request.Method.GET,getApplicationContext(), Tasks.class,(MyApplicationData) this.getApplication());
    }

    public void trackExpectedShipment(View view) {
        String phoneNumber = ((MyApplicationData) this.getApplication()).getPhoneNumber();
        String resource = "/shipments/?phoneNumber="+phoneNumber+"&userType=receiver";
        CommonParams.enhancedJSONArrayRequest(new JSONArray(),resource, Request.Method.GET,getApplicationContext(), Tasks.class,(MyApplicationData) this.getApplication());
    }
}