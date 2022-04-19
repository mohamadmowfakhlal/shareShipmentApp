package com.example.shareshipment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Recipent extends AppCompatActivity {
    EditText recipientPhoneNumber;
    EditText notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipientPhoneNumber = (EditText) findViewById(R.id.recipientPhoneNumber);
        notes = (EditText) findViewById(R.id.notes);
        setContentView(R.layout.activity_recipent);
    }
    public void ChooseRecipient(View view){
        Intent intent = new Intent(this,ConfirmRecipentInformation.class);

        // create the get Intent object
        /*Intent previousIntent = getIntent();

        // receive the value by getStringExtra() method
        // and key must be same which is send by first activity
        String fee = previousIntent.getStringExtra("fee");
        String shipmentType = previousIntent.getStringExtra("shipmentType");
        String size = previousIntent.getStringExtra("size");
        String weight = previousIntent.getStringExtra("weight");
        intent.putExtra("shipmentType",shipmentType);
        intent.putExtra("fee",fee);
        intent.putExtra("size",size);
        intent.putExtra("weight",weight);
        intent.putExtra("recipientPhoneNumber",recipientPhoneNumber.getText().toString());
        intent.putExtra("notes",notes.getText().toString());

         */
        startActivity(intent);

    }
}