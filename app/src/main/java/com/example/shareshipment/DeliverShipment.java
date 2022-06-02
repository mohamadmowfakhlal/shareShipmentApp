package com.example.shareshipment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;

import org.json.JSONArray;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class DeliverShipment extends AppCompatActivity implements LocationListener {
    EditText destinationCity;
    String sourcecity;
    final static String[] PERMISSIONS = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
    final static int PERMISSON_ALL=1;
    LocationManager locationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver_shipment);
        locationManager =  (LocationManager) getSystemService(LOCATION_SERVICE);
        if(Build.VERSION.SDK_INT>=23){
            requestPermissions(PERMISSIONS,PERMISSON_ALL);
        }
        requestLocation();
    }

    public void searchForTasks(View view){
        //ToDo the source city should be taken from login session
        destinationCity = (EditText) findViewById(R.id.destinationCity);
        // source city is taken from login
        //sourcecity = ((MyApplication) this.getApplication()).getCity();
        String resource = "/shipments/search/?sourceCity="+ sourcecity + "&destinationCity="+destinationCity.getText().toString();
        CommonParams.enhancedJSONArrayRequest(new JSONArray(),resource, Request.Method.GET,getApplicationContext(),AvailableShipments.class);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        //Log.d("myLog","got location" + location.getLatitude());
        //Toast.makeText(this,"Got location " + location.getLatitude(),Toast.LENGTH_SHORT).show();
        locationManager.removeUpdates(this);
        Geocoder geoCoder = new Geocoder(this, Locale.getDefault());
        StringBuilder builder = new StringBuilder();
        try {
            List<Address> address = geoCoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            int maxLines = address.get(0).getMaxAddressLineIndex();

            for (int i=0; i<=maxLines; i++) {
                String addressStr = address.get(0).getAddressLine(i);
                sourcecity = address.get(0).getLocality();
                builder.append(addressStr);
                builder.append(" ");
            }

            String fnialAddress = builder.toString(); //This is the complete address.
            Toast.makeText(this,"Got location " + fnialAddress,Toast.LENGTH_SHORT).show();

            //Toast.makeText(this,"Got location " + fnialAddress,Toast.LENGTH_SHORT).show();

        } catch (IOException e) {}
        catch (NullPointerException e) {}
        locationManager.removeUpdates(this);
    }

    @Override
    public  void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,@NonNull int[] grantsResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantsResults);
        if(grantsResults.length>0 && grantsResults[0] == PackageManager.PERMISSION_GRANTED){
            //request Location Now
            requestLocation();
        }
    }
    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }
    public void requestLocation(){
        if(locationManager == null){
            locationManager =  (LocationManager) getSystemService(LOCATION_SERVICE);
        }
        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER,10,10,this);
            }
        }
    }

}