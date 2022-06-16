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
    JSONObject task;
    private TextView recipientPhoneNumberTextView;
    private TextView recipientNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent previousIntent = getIntent();
        Shipment shipment = ((MyApplicationData) this.getApplication()).getShipment();
        Integer fee = shipment.getFee();
        String shipmentType = shipment.getShipmentType();
        String size = shipment.getSize();
        String weight = shipment.getWeight();
        String deadline = shipment.getDeadline();
        String recipientPhoneNumber = ((MyApplicationData) this.getApplication()).getRecipientPhoneNumber();
        String notes = ((MyApplicationData) this.getApplication()).getNotes();
        String productImage = ((MyApplicationData) this.getApplication()).getProductImage();
        task = new JSONObject();
        try {
            task.put("fee",fee);
            task.put("shipmentType",shipmentType);
            task.put("size",size);
            task.put("weight",weight);
            task.put("notes",notes);
            task.put("deadline",deadline);
            task.put("status","sent");
            task.put("image",productImage);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            task.put("sentDate",formatter.format(date));
            JSONObject sender = new JSONObject();
            //sender phone number is taken from login
            String phoneNumber = ((MyApplicationData) this.getApplication()).getPhoneNumber();
            sender.put("phoneNumber",phoneNumber);
            task.put("sender",sender);
            JSONObject receiver = new JSONObject();
            receiver.put("phoneNumber",recipientPhoneNumber);
            task.put("receiver",receiver);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_confirm_recipent_information);
        // create the get Intent object
        recipientPhoneNumberTextView =  findViewById(R.id.phoneNumber);
        recipientPhoneNumberTextView.setText(recipientPhoneNumber);
        recipientNameTextView =   findViewById(R.id.receipentName);
        recipientNameTextView.setText(previousIntent.getStringExtra("recipientName"));
        // call a rest api to get a userName that belong to given phone number
    }

    public void announceShipment(View view){
        String resource = "/shipments";
        CommonParams.jsonRequestSignIn(task,resource,Request.Method.POST,getApplicationContext(),CompleteWindow.class);
    }
}