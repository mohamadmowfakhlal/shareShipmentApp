package com.example.shareshipment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AssignShipment extends AppCompatActivity {
    ListView listView;
    MyAdapter adapter;
    ArrayList<JSONObject> shipments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_shipment);
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
        adapter = new MyAdapter(this, shipments,listView);
        listView.setAdapter(adapter);
    }
}