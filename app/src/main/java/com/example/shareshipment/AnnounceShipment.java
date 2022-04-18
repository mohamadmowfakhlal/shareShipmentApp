package com.example.shareshipment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class AnnounceShipment extends AppCompatActivity {
    TextView feeLabel;
    EditText fee;
    Spinner size ;
    Spinner weight ;
    TextView weightLabel;
    TextView sizeLabel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announce_shipment);
         size = (Spinner) findViewById(R.id.spinner);
         weight = (Spinner) findViewById(R.id.spinner2);
        weightLabel = (TextView) findViewById(R.id.textView14);
        sizeLabel = (TextView) findViewById(R.id.textView16);
        sizeLabel.setVisibility(View.GONE);
        weightLabel.setVisibility(View.GONE);
        size.setVisibility(View.GONE);
        weight.setVisibility(View.GONE);

    }
    public void letter(View view) {
        sizeLabel.setVisibility(View.GONE);
        weightLabel.setVisibility(View.GONE);
        size.setVisibility(View.GONE);
        weight.setVisibility(View.GONE);
    }
    public void luggage(View view) {
        size.setVisibility(View.VISIBLE);
        weight.setVisibility(View.VISIBLE);
        weightLabel.setVisibility(View.VISIBLE);
        sizeLabel.setVisibility(View.VISIBLE);
        ArrayAdapter<CharSequence> adapterSize = ArrayAdapter.createFromResource(this,
                R.array.size_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterSize.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        size.setAdapter(adapterSize);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.weight_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        weight.setAdapter(adapter);
    }



}