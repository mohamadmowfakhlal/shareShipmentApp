package com.example.shareshipment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class AnnounceShipment extends AppCompatActivity {
    Spinner size ;
    Spinner weight ;
    TextView weightLabel;
    TextView sizeLabel;
    String shipmentType;
    EditText fee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announce_shipment);
        size = (Spinner) findViewById(R.id.size);
        weight = (Spinner) findViewById(R.id.weight);
        weightLabel = (TextView) findViewById(R.id.weightLabel);
        sizeLabel = (TextView) findViewById(R.id.sizeLabel);
        fee = (EditText) findViewById(R.id.fee);
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
        shipmentType ="letter";

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
        shipmentType = "luggage";
    }

    public void selectRecipient(View view)  {
        String weightValue = null;
        String sizeValue = null;

        if(weight.getVisibility()!= View.GONE)
            weightValue= weight.getSelectedItem().toString();
        if(size.getVisibility() != View.GONE)
            sizeValue = size.getSelectedItem().toString();

        Shipment shipment = new Shipment(shipmentType,Integer.parseInt(fee.getText().toString()),weightValue,sizeValue);
        Intent intent = new Intent(this, ChooseRecipient.class);
        intent.putExtra("shipment", shipment);
        startActivity(intent);
    }

}