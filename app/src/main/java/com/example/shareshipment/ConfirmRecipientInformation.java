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
        //Shipment shipment = getIntent().getParcelableExtra("shipment");
        Shipment shipment = ((MyApplication) this.getApplication()).getShipment();

        Integer fee = shipment.getFee();
        String shipmentType = shipment.getShipmentType();
        String size = shipment.getSize();
        String weight = shipment.getWeight();
        String dealine = shipment.getDeadline();
        String recipientPhoneNumber = previousIntent.getStringExtra("recipientPhoneNumber");
        String notes = previousIntent.getStringExtra("notes");
        String productImage = ((MyApplication) this.getApplication()).getProductImage();
        js = new JSONObject();
        try {
            js.put("fee",fee);
            js.put("shipmentType",shipmentType);
            js.put("size",size);
            js.put("weight",weight);
            js.put("notes",notes);
            js.put("deadline",dealine);
            js.put("status","sent");
            js.put("productImage",productImage);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            js.put("sentDate",formatter.format(date));
            JSONObject sender = new JSONObject();
            //sender phone number is taken from login
            String phoneNumber = ((MyApplication) this.getApplication()).getPhoneNumber();
            sender.put("phoneNumber",phoneNumber);
            js.put("sender",sender);
            JSONObject receiver = new JSONObject();
            receiver.put("phoneNumber",recipientPhoneNumber);
            js.put("receiver",receiver);
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
        CommonParams.JSONRequestWithoutResponse(js,resource,Request.Method.POST,getApplicationContext(),CompleteWindow.class);
    }
}