package com.example.shareshipment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapImage {
    public static Bitmap createBitmapFromByteArray(byte[] data) {
        return BitmapFactory.decodeByteArray(data, 0, data.length);
    }
}