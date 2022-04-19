package com.example.shareshipment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ConfirmActivitionCode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_activition_code);
    }
    public void verify(View view) {
        Intent intent = new Intent(this, Password.class);
        startActivity(intent);
    }
}