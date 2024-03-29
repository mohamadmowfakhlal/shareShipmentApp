package com.example.shareshipment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.Request;

import org.json.JSONArray;


public class StartTask extends AppCompatActivity  {
    EditText destinationCity;
    Spinner sourceCitySpinner;
    String currentLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver_shipment);
        sourceCitySpinner = (Spinner) findViewById(R.id.cities);

        ArrayAdapter<CharSequence> adapterCities = ArrayAdapter.createFromResource(this,
                R.array.city_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterCities.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        sourceCitySpinner.setAdapter(adapterCities);
    }

    public void searchForTasks(View view) {
        destinationCity = (EditText) findViewById(R.id.destinationCity);

        // source city is taken from GPS
        currentLocation = ((MyApplicationData) this.getApplication()).getCity();
        //show the busy sign
        String resource;
        String selectedLocation= sourceCitySpinner.getSelectedItem().toString();
        ((MyApplicationData) this.getApplication()).setType("searchTasks");
        if(selectedLocation.equals("current Location"))
             resource = "/shipments/search/?sourceCity="+ currentLocation + "&destinationCity="+destinationCity.getText().toString();
        else
            resource = "/shipments/search/?sourceCity="+ selectedLocation + "&destinationCity="+destinationCity.getText().toString();
        CommonParams.enhancedJSONArrayRequest(new JSONArray(),resource, Request.Method.GET,getApplicationContext(), Tasks.class,(MyApplicationData) this.getApplication());
    }



}