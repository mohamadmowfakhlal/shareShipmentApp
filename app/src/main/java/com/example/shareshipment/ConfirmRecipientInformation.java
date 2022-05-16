package com.example.shareshipment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConfirmRecipientInformation extends AppCompatActivity {
    JSONObject js;
    private TextView recipientPhoneNumberTextView;
    private TextView recipientNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent previousIntent = getIntent();
        // receive the value by getStringExtra() method
        // and key must be same which is send by first activity
        Shipment shipment = getIntent().getParcelableExtra("shipment");
        Integer fee = shipment.getFee();
        String shipmentType = shipment.getShipmentType();
        String size = shipment.getSize();
        String weight = shipment.getWeight();
        String recipientPhoneNumber = previousIntent.getStringExtra("recipientPhoneNumber");
        //recipientName is from users table need an api call maybe in previous activity and pass it
        //recipientNameTextView.setText(recipientName);
        String notes = previousIntent.getStringExtra("notes");
        js = new JSONObject();
        try {
            js.put("fee",fee);
            js.put("shipmentType",shipmentType);
            js.put("size",size);
            js.put("weight",weight);
            js.put("notes",notes);
            js.put("status","sent");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            js.put("sentDate",formatter.format(date));
            JSONObject sender = new JSONObject();
            //sender phone number is taken from login
            sender.put("phoneNumber","004542332945");
            js.put("sender",sender);
            JSONObject receiver = new JSONObject();
            receiver.put("phoneNumber",recipientPhoneNumber);
            js.put("receiver",receiver);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_confirm_recipent_information);
        // create the get Intent object
        recipientPhoneNumberTextView =  (TextView) findViewById(R.id.phoneNumber);
        recipientPhoneNumberTextView.setText(recipientPhoneNumber);
        recipientNameTextView =  (TextView) findViewById(R.id.receipentName);
        recipientNameTextView.setText(previousIntent.getStringExtra("recipientName"));
        // call a rest api to get a userName that belong to given phone number
    }

    public void announceShipment(View view){
        String resource = "/shipments";
        CommonParams.JSONRequestWithoutResponse(js,resource,Request.Method.POST,getApplicationContext(),CompleteWindow.class);
    }
}