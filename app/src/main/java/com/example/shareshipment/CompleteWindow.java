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
        String phoneNumber = ((MyApplicationData) this.getApplication()).getPhoneNumber();
        String resource = "/shipments/?status=sent&userType=sender&phoneNumber="+phoneNumber;
        CommonParams.enhancedJSONArrayRequest(new JSONArray(),resource, Request.Method.GET,getApplicationContext(), AnnouncedTasks.class,(MyApplicationData) this.getApplication());
    }
}