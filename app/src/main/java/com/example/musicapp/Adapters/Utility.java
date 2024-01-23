package com.example.musicapp.Adapters;

import android.content.Context;
import android.widget.Toast;

public class Utility {
    public static void showToask(Context context, String message) {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
}