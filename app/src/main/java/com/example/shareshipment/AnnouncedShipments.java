package com.example.shareshipment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AnnouncedShipments extends AppCompatActivity {

    ListView listViewAnnounceShipments;
    AvailableShipmentAdapter adapter;
    ArrayList<JSONObject> shipments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announced_shipments);
        Intent intent = getIntent();
        //String jsonArray = intent.getStringExtra("shipments");
        String jsonArray = ((MyApplication) getApplication()).getShipments();
        try {
            JSONArray receivedShipments = new JSONArray(jsonArray);
            if (receivedShipments != null) {
                for (int i=0;i<receivedShipments.length();i++){
                    shipments.add((JSONObject) receivedShipments.getJSONObject(i));
                }
            }
            //System.out.println(array.toString(2));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        listViewAnnounceShipments = findViewById(R.id.announceShipments);
        adapter = new AvailableShipmentAdapter(this, shipments, listViewAnnounceShipments,((MyApplication) this.getApplication()).getPhoneNumber(),"AnnouncedShipments");
        listViewAnnounceShipments.setAdapter(adapter);
    }
}