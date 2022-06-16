package com.example.shareshipment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

public class AnnounceTask extends AppCompatActivity implements View.OnClickListener{
    Spinner size ;
    Spinner weight ;
    Spinner deadline;
    TextView weightLabel;
    TextView sizeLabel;
    String shipmentType;
    EditText fee;
    ImageView itemPhoto;
    private static final int RESULT_LOAD_IMAGE = 1;
    private String productImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announce_shipment);
        size = findViewById(R.id.size);
        weight =  findViewById(R.id.weight);
        weightLabel =  findViewById(R.id.weightLabel);
        sizeLabel =  findViewById(R.id.sizeLabel);
        fee =  findViewById(R.id.fee);
        deadline =  findViewById(R.id.deadline);
        itemPhoto = findViewById(R.id.itemPhoto);
        itemPhoto.setOnClickListener(this);
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
        Shipment shipment = new Shipment(shipmentType,Integer.parseInt(fee.getText().toString()),weightValue,sizeValue,deadline.getSelectedItem().toString());
        Intent intent = new Intent(this, ChooseRecipient.class);
        intent.putExtra("shipment", shipment);
        ((MyApplicationData) this.getApplication()).setShipment(shipment);
        startActivity(intent);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.itemPhoto:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent,RESULT_LOAD_IMAGE);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri selectedImage;
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
}