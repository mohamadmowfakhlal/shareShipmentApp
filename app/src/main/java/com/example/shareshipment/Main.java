package com.example.shareshipment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Main {
    public static Bitmap createBitmapFromByteArray(byte[] data) {
        return BitmapFactory.decodeByteArray(data, 0, data.length);
    }//  w w  w .jav a 2s. c o m
}