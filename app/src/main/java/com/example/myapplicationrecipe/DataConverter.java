package com.example.myapplicationrecipe;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

public class DataConverter {
    public static byte[] convert_image2ByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0, stream);
        return stream.toByteArray();
    }
    public static Bitmap converByteArray2Inage (byte [] array){
        return BitmapFactory.decodeByteArray(array,0,array.length);
        //return BitmapFactory.decodeByteArray(array,  8, array.length);
        }






        }
