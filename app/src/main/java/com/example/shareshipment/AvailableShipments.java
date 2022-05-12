package com.example.shareshipment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AvailableShipments extends AppCompatActivity {

    ListView listView;
    MyAdapter adapter;
    ArrayList<JSONObject> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_shipments);
        Intent intent = getIntent();
        String jsonArray = intent.getStringExtra("shipments");

        try {
            JSONArray array = new JSONArray(jsonArray);
            if (array != null) {
                for (int i=0;i<array.length();i++){
                    arrayList.add((JSONObject) array.getJSONObject(i));
                }
            }
            //System.out.println(array.toString(2));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        listView = findViewById(R.id.list);

        adapter = new MyAdapter(this, arrayList);
        listView.setAdapter(adapter);
    }


}