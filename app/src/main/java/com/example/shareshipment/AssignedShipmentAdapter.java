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
   private TextView shipmentId,shipmentIdLabel,
           shipmentType,shipmentTypeLabel,
           shipmentFee, shipmentFeeLabel,
           shipmentSize, shipmentSizeLabel,
           shipmentWeight, shipmentWeightLabel;
   private Button assign,cancel;
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
      shipmentIdLabel = convertView.findViewById(R.id.shipmentIdLabel);
      shipmentType = convertView.findViewById(R.id.ShipmentType);
      shipmentTypeLabel = convertView.findViewById(R.id.shipmentTypeLabel);
      shipmentFee = convertView.findViewById(R.id.ShipmentFee);
      shipmentFeeLabel = convertView.findViewById(R.id.shipmentFeeLabel);
      shipmentSize = convertView.findViewById(R.id.shipmentSize);
      shipmentSizeLabel = convertView.findViewById(R.id.shipmentSizeLabel);
      shipmentWeight = convertView.findViewById(R.id.shipmentWeight);
      shipmentWeightLabel = convertView.findViewById(R.id.shipmentWeightLabel);
      assign = convertView.findViewById(R.id.assign);
      cancel = convertView.findViewById(R.id.cancelShipment);

      assign.setOnClickListener(new View.OnClickListener() {
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
            CommonParams.JSONRequestWithoutResponse(js,"/shipments/deliveryMan", Request.Method.PUT,context,MainFunctionality.class);
            context.startActivity(intent);
         }
      });
      cancel.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            JSONObject js = new JSONObject();
            try {
               js.put("shipmentId",arrayList.get(position).getInt("shipmentId"));
               js.put("status","canceled");
            } catch (JSONException e) {
               e.printStackTrace();
            }
            CommonParams.JSONRequestWithoutResponse(js,"/shipments/sender", Request.Method.PUT,context,MainFunctionality.class);

         }
      });
      try {
      shipmentId.setText(" " + arrayList.get(position).getInt("shipmentId"));
      shipmentType.setText(arrayList.get(position).getString("shipmentType"));
      shipmentFee.setText(arrayList.get(position).getString("fee"));
      if(arrayList.get(position).getString("size") != null)
         shipmentSize.setText(arrayList.get(position).getString("size"));
      if(arrayList.get(position).getString("weight") != null)
         shipmentWeight.setText(arrayList.get(position).getString("weight"));
      } catch (JSONException e) {
         e.printStackTrace();
      }
      return convertView;
   }

}