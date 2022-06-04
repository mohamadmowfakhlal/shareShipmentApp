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

    ListView listView;
    AvailableShipmentAdapter adapter;
    ArrayList<JSONObject> shipments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announced_shipments);
        Intent intent = getIntent();
        String jsonArray = intent.getStringExtra("shipments");

        try {
            JSONArray array = new JSONArray(jsonArray);
            if (array != null) {
                for (int i=0;i<array.length();i++){
                    shipments.add((JSONObject) array.getJSONObject(i));
                }
            }
            //System.out.println(array.toString(2));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        listView = findViewById(R.id.list);
        adapter = new AvailableShipmentAdapter(this, shipments,listView,((MyApplication) this.getApplication()).getPhoneNumber(),"AnnouncedShipments");
        listView.setAdapter(adapter);
    }
}