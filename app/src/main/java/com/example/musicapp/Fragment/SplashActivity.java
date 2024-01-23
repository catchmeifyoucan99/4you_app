package com.example.musicapp.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.musicapp.Activity.LoginActivity;
import com.example.musicapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
//            if (currentUser == null)
//            {
//                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
//
//            }
//            else {
//                startActivity(new Intent(SplashActivity.this, AdminActivity.class));
//            }
//            finish();
        }, 3000);
    }
}