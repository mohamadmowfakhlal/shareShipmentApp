package com.example.shareshipment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
public class MyAdapter extends BaseAdapter {
   private Context context;
   private ArrayList<JSONObject> arrayList;
   private TextView shipmentId, shipmentType, shipmentFee,shipmentSize,shipmentWeight;
   private Button assign;
   public MyAdapter(Context context, ArrayList<JSONObject> arrayList) {
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
   public View getView(int position, View convertView, ViewGroup parent) {
      convertView = LayoutInflater.from(context).inflate(R.layout.row, parent, false);
      shipmentId = convertView.findViewById(R.id.shipmentId);
      shipmentType = convertView.findViewById(R.id.ShipmentType);
      shipmentFee = convertView.findViewById(R.id.ShipmentFee);
      shipmentSize = convertView.findViewById(R.id.shipmentSize);
      shipmentWeight = convertView.findViewById(R.id.shipmentWeight);
      assign = convertView.findViewById(R.id.assign);
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