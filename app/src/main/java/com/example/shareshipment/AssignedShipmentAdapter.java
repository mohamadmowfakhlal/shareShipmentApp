package com.example.shareshipment;

import android.content.Context;
import android.content.Intent;
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

public class AssignedShipmentAdapter extends BaseAdapter {
   private Context context;
   private ArrayList<JSONObject> arrayList;
   private String carriorPhoneNumber;
   private TextView shipmentId,
           shipmentType,
           shipmentFee,
           shipmentSize,shipmentSizeLabel,
           shipmentWeight,shipmentWeightLabel,
           shipmentStatus;
   private Button deliver,cancel;
   public AssignedShipmentAdapter(Context context, ArrayList<JSONObject> arrayList, ListView listView,String deliveryMan) {
      this.context = context;
      this.arrayList = arrayList;
      this.carriorPhoneNumber = deliveryMan;
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
      convertView = LayoutInflater.from(context).inflate(R.layout.rowassigned, parent, false);
      shipmentId = convertView.findViewById(R.id.shipmentId);
      shipmentType = convertView.findViewById(R.id.ShipmentType);
      shipmentFee = convertView.findViewById(R.id.ShipmentFee);
      shipmentSize = convertView.findViewById(R.id.shipmentSize);
      shipmentSizeLabel = convertView.findViewById(R.id.shipmentSizeLabel);
      shipmentWeightLabel = convertView.findViewById(R.id.shipmentWeightLabel);
      shipmentWeight = convertView.findViewById(R.id.shipmentWeight);
      shipmentStatus = convertView.findViewById(R.id.shipmentStatus);
      deliver = convertView.findViewById(R.id.cancel);
      cancel = convertView.findViewById(R.id.cancelShipment);

      deliver.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Intent intent = new Intent(context, MainFunctionality.class);
            JSONObject js = new JSONObject();
            try {
               js.put("shipmentId",arrayList.get(position).getInt("shipmentId"));
               js.put("status","delivered");
               JSONObject deliveryMan = new JSONObject();
               deliveryMan.put("phoneNumber", carriorPhoneNumber);
               js.put("deliveryMan",deliveryMan);
            } catch (JSONException e) {
               e.printStackTrace();
            }
            CommonParams.JSONRequestWithoutResponse(js,"/shipments", Request.Method.PUT,context,MainFunctionality.class);
            context.startActivity(intent);
         }
      });
      cancel.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            JSONObject js = new JSONObject();
            try {
               js.put("shipmentId",arrayList.get(position).getInt("shipmentId"));
               js.put("status","sent");
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