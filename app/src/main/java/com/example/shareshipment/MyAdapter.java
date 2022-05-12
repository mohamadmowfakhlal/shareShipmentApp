package com.example.shareshipment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
public class MyAdapter extends BaseAdapter {
   private Context context;
   private ArrayList<JSONObject> arrayList;
   private TextView serialNum, name, contactNum;
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
      serialNum = convertView.findViewById(R.id.serailNumber);
      name = convertView.findViewById(R.id.studentName);
      contactNum = convertView.findViewById(R.id.mobileNum);
      try {
      serialNum.setText(" " + arrayList.get(position).getInt("fee"));
      name.setText(arrayList.get(position).getString("shipmentType"));
      contactNum.setText(arrayList.get(position).getString("shipmentType"));

      } catch (JSONException e) {
         e.printStackTrace();
      }
      return convertView;
   }
}