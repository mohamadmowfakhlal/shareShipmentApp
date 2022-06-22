package com.example.shareshipment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Tasks extends AppCompatActivity {

    ListView listViewAnnounceTasks;
    TasksAdapter adapter;
    ArrayList<JSONObject> tasks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announced_shipments);
        String jsonArray = ((MyApplicationData) getApplication()).getShipments();
        try {
            JSONArray tasks = new JSONArray(jsonArray);
            if (tasks != null) {
                for (int task=0;task<tasks.length();task++){
                    this.tasks.add(tasks.getJSONObject(task));
                }
            }
            //System.out.println(array.toString(2));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        listViewAnnounceTasks = findViewById(R.id.announceShipments);
        String type = ((MyApplicationData) this.getApplication()).getType();
        adapter = new TasksAdapter(this, tasks, listViewAnnounceTasks,((MyApplicationData) this.getApplication()).getPhoneNumber(),type);
        listViewAnnounceTasks.setAdapter(adapter);
    }
}