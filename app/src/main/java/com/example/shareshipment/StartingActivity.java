package com.example.shareshipment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class StartingActivity extends AppCompatActivity implements LocationListener {
    LocationManager locationManager;
    final static String[] PERMISSIONS = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
    final static int PERMISSON_ALL=1;
    String pickupCity;
    EditText phoneNumber;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrollment);
        locationManager =  (LocationManager) getSystemService(LOCATION_SERVICE);
        if(Build.VERSION.SDK_INT>=23){
            requestPermissions(PERMISSIONS,PERMISSON_ALL);
        }
        requestLocation();
        final AlertDialog.Builder builder =  new AlertDialog.Builder(this);
        final String action = Settings.ACTION_LOCATION_SOURCE_SETTINGS;
        final String message = "Do you want open GPS setting?";
        //ToDo check if GPS has been enable before if not show the dialog
        builder.setMessage(message)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface d, int id) {
                                startActivity(new Intent(action));
                                d.dismiss();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface d, int id) {
                                d.cancel();
                            }
                        });
        builder.create().show();

    }
    public void signUp(View view) {
        Intent intent = new Intent(this, EnrollmentActivity.class);
        startActivity(intent);

  }


    public void signIn(View view) throws JSONException {
        //ToDo API verification for phone number and password

        phoneNumber = findViewById(R.id.PhoneNumber);
        password  = findViewById(R.id.password);
        JSONObject login = new JSONObject();
        login.put("phoneNumber",phoneNumber.getText().toString());
        login.put("password",password.getText().toString());
        String resource = "/users/login";
        CommonParams.JSONRequest(login,resource, Request.Method.POST,getApplicationContext(),MainFunctionality.class);
        ((MyApplicationData) this.getApplication()).setPhoneNumber(phoneNumber.getText().toString());
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
                pickupCity = address.get(0).getLocality();
                ((MyApplicationData) this.getApplication()).setCity(pickupCity);
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
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER,10,10,this);
            }
        }
    }
}