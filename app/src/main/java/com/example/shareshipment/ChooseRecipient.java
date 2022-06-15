package com.example.shareshipment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChooseRecipient extends AppCompatActivity {
    EditText recipientPhoneNumber;
    EditText notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipent);
        recipientPhoneNumber =  findViewById(R.id.recipientPhoneNumber);
        notes = findViewById(R.id.notes);

    }
    public void ChooseRecipient(View view){
        ((MyApplication) this.getApplication()).setRecipientPhoneNumber(recipientPhoneNumber.getText().toString());
        ((MyApplication) this.getApplication()).setNotes(notes.getText().toString());
        CommonParams.jsonRequestSignIn(new JSONObject(),"/users/",Request.Method.GET,getApplicationContext(),ConfirmRecipientInformation.class,recipientPhoneNumber.getText().toString());
    }
}