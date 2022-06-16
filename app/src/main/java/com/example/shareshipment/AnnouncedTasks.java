package com.example.shareshipment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AnnouncedTasks extends AppCompatActivity {

    ListView listViewAnnounceTasks;
    AvailableTaskAdapter adapter;
    ArrayList<JSONObject> tasks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announced_shipments);
        Intent intent = getIntent();
        //String jsonArray = intent.getStringExtra("shipments");
        String jsonArray = ((MyApplicationData) getApplication()).getShipments();
        try {
            JSONArray receivedTasks = new JSONArray(jsonArray);
            if (receivedTasks != null) {
                for (int task=0;task<receivedTasks.length();task++){
                    tasks.add(receivedTasks.getJSONObject(task));
                }
            }
            //System.out.println(array.toString(2));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        listViewAnnounceTasks = findViewById(R.id.announceShipments);
        adapter = new AvailableTaskAdapter(this, tasks, listViewAnnounceTasks,((MyApplicationData) this.getApplication()).getPhoneNumber(),"AnnouncedShipments");
        listViewAnnounceTasks.setAdapter(adapter);
    }
}