package com.example.shareshipment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
public class AvailableShipmentAdapter extends BaseAdapter {
   private Context context;
   private ArrayList<JSONObject> arrayList;
   String deliveryManPhoneNumber;
   private TextView shipmentId,
           shipmentType,
           shipmentFee,
           shipmentSize,shipmentSizeLabel,
           shipmentWeight,shipmentWeightLabel,
           shipmentStatus,
           senderPhoneNumber,receiverPhoneNumber,
           pickupAddress,destinationAddress;
   private Button assign;
   public AvailableShipmentAdapter(Context context, ArrayList<JSONObject> arrayList, ListView listView,String deliveryMan) {
      this.context = context;
      this.arrayList = arrayList;
      this.deliveryManPhoneNumber = deliveryMan;
   }
   @Override
   public int getCount() {
      return arrayList.size();
   }
   @Override
   public Object getItem(int position) {
      return position;
   }
   @Override
   public long getItemId(int position) {
      return position;
   }
   @Override
   public View getView(final int position, View convertView, ViewGroup parent) {
      convertView = LayoutInflater.from(context).inflate(R.layout.row, parent, false);
      shipmentId = convertView.findViewById(R.id.shipmentId);
      shipmentType = convertView.findViewById(R.id.ShipmentType);
      shipmentFee = convertView.findViewById(R.id.ShipmentFee);
      shipmentSize = convertView.findViewById(R.id.shipmentSize);
      shipmentWeight = convertView.findViewById(R.id.shipmentWeight);
      shipmentStatus = convertView.findViewById(R.id.shipmentStatus);
      assign = convertView.findViewById(R.id.cancel);
      shipmentWeightLabel = convertView.findViewById(R.id.shipmentWeightLabel);
      shipmentSizeLabel = convertView.findViewById(R.id.shipmentSizeLabel);
      senderPhoneNumber = convertView.findViewById(R.id.senderNumber);
      receiverPhoneNumber = convertView.findViewById(R.id.receiverNumber);
      pickupAddress = convertView.findViewById(R.id.pickupAddress);
      destinationAddress = convertView.findViewById(R.id.destinationAddress);
      assign.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            JSONObject js = new JSONObject();
            try {
               js.put("shipmentId",arrayList.get(position).getInt("shipmentId"));
               js.put("status","assigned");
               JSONObject deliveryMan = new JSONObject();
               deliveryMan.put("phoneNumber",deliveryManPhoneNumber);
               js.put("deliveryMan",deliveryMan);
            } catch (JSONException e) {
               e.printStackTrace();
            }
            CommonParams.JSONRequestWithoutResponse(js,"/shipments", Request.Method.PUT,context,MainFunctionality.class);
         }
      });
      try {
      shipmentId.setText(" " + arrayList.get(position).getInt("shipmentId"));
      shipmentType.setText(arrayList.get(position).getString("shipmentType"));
      shipmentFee.setText(arrayList.get(position).getString("fee"));
      shipmentStatus.setText(arrayList.get(position).getString("status"));
      JSONObject sender = arrayList.get(position).getJSONObject("sender");
      JSONObject receiver = arrayList.get(position).getJSONObject("receiver");
      senderPhoneNumber.setText(sender.getString("phoneNumber"));
      receiverPhoneNumber.setText(receiver.getString("phoneNumber"));
      JSONObject senderAddress = sender.getJSONObject("address");
      pickupAddress.setText(senderAddress.getString("city")+senderAddress.getString("streetName")+senderAddress.getString("streetName")+senderAddress.getString("houseNumber"));
      JSONObject receiverAddress = receiver.getJSONObject("address");
      destinationAddress.setText(receiverAddress.getString("city")+receiverAddress.getString("streetName")+senderAddress.getString("streetName")+senderAddress.getString("houseNumber"));

         if(arrayList.get(position).getString("size") != "null"){
            shipmentSize.setText(arrayList.get(position).getString("size"));
         }
         else{
            shipmentSize.setText("");
            shipmentSizeLabel.setVisibility(View.GONE);
         }
         if(arrayList.get(position).getString("weight") != "null"){
            shipmentWeight.setText(arrayList.get(position).getString("weight"));
         }
         else{
            shipmentWeight.setText("");
            shipmentWeightLabel.setVisibility(View.GONE);
         }
      } catch (JSONException e) {
         e.printStackTrace();
      }
      return convertView;
   }

}