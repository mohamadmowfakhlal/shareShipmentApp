package com.example.shareshipment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AnnounceTask extends AppCompatActivity {
    Spinner size ;
    Spinner weight ;
    Spinner deadline;
    TextView weightLabel;
    TextView sizeLabel;
    String shipmentType;
    EditText fee;
    ImageView itemPhoto;
    JSONObject task;
    private static final int RESULT_LOAD_IMAGE = 1;
    private String productImage;
    EditText recipientPhoneNumber ;
    EditText notes;
    Shipment shipment;
    FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
    }
    public void letter(View view) {
        size = findViewById(R.id.size);
        weight =  findViewById(R.id.weight);
        weightLabel =  findViewById(R.id.weightLabel);
        sizeLabel =  findViewById(R.id.sizeLabel);
        fee =  findViewById(R.id.fee);
        deadline =  findViewById(R.id.deadline);
        itemPhoto = findViewById(R.id.itemPhoto);
        sizeLabel.setVisibility(View.GONE);
        weightLabel.setVisibility(View.GONE);
        size.setVisibility(View.GONE);
        weight.setVisibility(View.GONE);
        shipmentType ="letter";

    }
    public void luggage(View view) {
        size = findViewById(R.id.size);
        weight =  findViewById(R.id.weight);
        weightLabel =  findViewById(R.id.weightLabel);
        sizeLabel =  findViewById(R.id.sizeLabel);
        fee =  findViewById(R.id.fee);
        deadline =  findViewById(R.id.deadline);
        itemPhoto = findViewById(R.id.itemPhoto);
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
        size = findViewById(R.id.size);
        weight =  findViewById(R.id.weight);
        weightLabel =  findViewById(R.id.weightLabel);
        sizeLabel =  findViewById(R.id.sizeLabel);
        fee =  findViewById(R.id.fee);
        deadline =  findViewById(R.id.deadline);
        itemPhoto = findViewById(R.id.itemPhoto);

        sizeLabel.setVisibility(View.GONE);
        weightLabel.setVisibility(View.GONE);
        size.setVisibility(View.GONE);
        weight.setVisibility(View.GONE);
        ArrayAdapter<CharSequence> adapterDeadline = ArrayAdapter.createFromResource(this,
                R.array.deadline_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterDeadline.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        deadline.setAdapter(adapterDeadline);

        String weightValue = null;
        String sizeValue = null;
        if(weight.getVisibility()!= View.GONE)
            weightValue= weight.getSelectedItem().toString();
        if(size.getVisibility() != View.GONE)
            sizeValue = size.getSelectedItem().toString();
        shipment = new Shipment(shipmentType,Integer.parseInt(fee.getText().toString()),weightValue,sizeValue,deadline.getSelectedItem().toString());
        task = new JSONObject();
        try {
            task.put("fee",Integer.parseInt(fee.getText().toString()));
            task.put("shipmentType",shipmentType);
            task.put("size",sizeValue);
            task.put("weight",weightValue);
            task.put("deadline",deadline.getSelectedItem().toString());
            task.put("status","sent");
            task.put("image",productImage);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Fragment fragment = new SelectReceiverFragment();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.my_nav_host_fragment_create_task, fragment);
        transaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri selectedImage;
        itemPhoto = findViewById(R.id.itemPhoto);
        if(resultCode==RESULT_OK && data !=null){
            selectedImage = data.getData(  );
            itemPhoto.setImageURI(selectedImage);
            Bitmap image = ((BitmapDrawable)itemPhoto.getDrawable()).getBitmap();
            ByteArrayOutputStream imageBytes = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG,100,imageBytes);
            productImage = Base64.encodeToString(imageBytes.toByteArray(),Base64.DEFAULT);
            ((MyApplicationData) this.getApplication()).setProductImage(productImage);
        }
    }

    public void ChooseRecipient(View view){
        recipientPhoneNumber =  findViewById(R.id.recipientPhoneNumber);
        notes = findViewById(R.id.notes);
        ((MyApplicationData) this.getApplication()).setRecipientPhoneNumber(recipientPhoneNumber.getText().toString());
        ((MyApplicationData) this.getApplication()).setNotes(notes.getText().toString());
        CommonParams.JSONRequest(new JSONObject(),"/users/", Request.Method.GET,getApplicationContext(),fm,recipientPhoneNumber.getText().toString());
    }

    public void announceShipment(View view){
        try {

            task.put("notes",notes);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            task.put("sentDate",formatter.format(date));
            JSONObject sender = new JSONObject();
            //sender phone number is taken from login
            String phoneNumber = ((MyApplicationData) this.getApplication()).getPhoneNumber();
            sender.put("phoneNumber",phoneNumber);
            task.put("sender",sender);
            JSONObject receiver = new JSONObject();
            receiver.put("phoneNumber",((MyApplicationData) this.getApplication()).getRecipientPhoneNumber());
            task.put("receiver",receiver);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String resource = "/shipments";

        CommonParams.JSONRequest(task,resource,Request.Method.POST,getApplicationContext(),fm);
    }


    public void searchSentShipment(View view) {
        String phoneNumber = ((MyApplicationData) this.getApplication()).getPhoneNumber();
        String resource = "/shipments/?status=sent&userType=sender&phoneNumber="+phoneNumber;
        ((MyApplicationData) this.getApplication()).setType("AnnouncedShipments");
        CommonParams.enhancedJSONArrayRequest(new JSONArray(),resource, Request.Method.GET,getApplicationContext(), Tasks.class,(MyApplicationData) this.getApplication());
    }
}