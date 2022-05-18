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

public class AnnouncedShipmentAdapter extends BaseAdapter {
   private Context context;
   private ArrayList<JSONObject> arrayList;
   String senderPhoneNumber;
   private TextView shipmentId,shipmentIdLabel,
           shipmentType,shipmentTypeLabel,
           shipmentFee, shipmentFeeLabel,
           shipmentSize, shipmentSizeLabel,
           shipmentWeight, shipmentWeightLabel;
   private Button cancel;
   public AnnouncedShipmentAdapter(Context context, ArrayList<JSONObject> arrayList, ListView listView, String sender) {
      this.context = context;
      this.arrayList = arrayList;
      this.senderPhoneNumber = sender;
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
      convertView = LayoutInflater.from(context).inflate(R.layout.rowcanceled, parent, false);
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
      cancel = convertView.findViewById(R.id.assign);
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