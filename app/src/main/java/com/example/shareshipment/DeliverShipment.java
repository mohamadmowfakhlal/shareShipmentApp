package com.example.shareshipment;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.android.volley.Request;

import org.json.JSONArray;


public class DeliverShipment extends AppCompatActivity {
    EditText destinationCity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver_shipment);
    }

    public void findShipment(View view){
        //ToDo the source city should be taken from login session
        destinationCity = (EditText) findViewById(R.id.destinationCity);
        // source city is taken from login
        String sourcecity = ((MyApplication) this.getApplication()).getCity();

        String resource = "/shipments/search/?sourceCity="+ sourcecity + "&destinationCity="+destinationCity.getText().toString();
        CommonParams.enhancedJSONArrayRequest(new JSONArray(),resource, Request.Method.GET,getApplicationContext(),AvailableShipments.class);
    }

}