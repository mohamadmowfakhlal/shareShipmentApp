package com.example.shareshipment;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.android.volley.Request;
import org.json.JSONObject;

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
        ((MyApplicationData) this.getApplication()).setRecipientPhoneNumber(recipientPhoneNumber.getText().toString());
        ((MyApplicationData) this.getApplication()).setNotes(notes.getText().toString());
        CommonParams.jsonRequestSignIn(new JSONObject(),"/users/",Request.Method.GET,getApplicationContext(),ConfirmRecipientInformation.class,recipientPhoneNumber.getText().toString());
    }
}