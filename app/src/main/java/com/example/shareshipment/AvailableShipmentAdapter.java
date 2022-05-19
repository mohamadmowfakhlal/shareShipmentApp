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
import android.widget.Toast;

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
           shipmentSize,
           shipmentWeight,
           shipmentStatus;
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
      assign = convertView.findViewById(R.id.assign);
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
            CommonParams.JSONRequestWithoutResponse(js,"/shipments/deliveryMan", Request.Method.PUT,context,MainFunctionality.class);

         }
      });
      try {
      shipmentId.setText(" " + arrayList.get(position).getInt("shipmentId"));
      shipmentType.setText(arrayList.get(position).getString("shipmentType"));
      shipmentFee.setText(arrayList.get(position).getString("fee"));
      shipmentStatus.setText(arrayList.get(position).getString("status"));
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