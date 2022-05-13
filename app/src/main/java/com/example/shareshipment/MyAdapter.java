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
public class MyAdapter extends BaseAdapter {
   private Context context;
   private ArrayList<JSONObject> arrayList;
   private TextView shipmentId,shipmentIdLabel,
           shipmentType,shipmentTypeLabel,
           shipmentFee, shipmentFeeLabel,
           shipmentSize, shipmentSizeLabel,
           shipmentWeight, shipmentWeightLabel;
   private Button assign;
   public MyAdapter(Context context, ArrayList<JSONObject> arrayList, ListView listView) {
      this.context = context;
      this.arrayList = arrayList;
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
      assign.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Intent intent = new Intent(context, MainFunctionality.class);
            JSONObject js = new JSONObject();


            try {
               js.put("shipmentId",arrayList.get(position).getInt("shipmentId"));
               js.put("status","assigned");
               JSONObject deliveryMan = new JSONObject();
               deliveryMan.put("phoneNumber","004542332945");
               js.put("deliveryMan",deliveryMan);
            } catch (JSONException e) {
               e.printStackTrace();
            }
            CommonParams.jsonRequest(js,"/shipments/deliveryMan", Request.Method.PUT,context);
            context.startActivity(intent);
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