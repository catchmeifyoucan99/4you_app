package com.example.musicapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.musicapp.Adapters.Utility;
import com.example.musicapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText edt_username, edt_Email, edt_password;
    Button btn_login;
    ProgressBar progressBar;
    TextView txt_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt_Email = findViewById(R.id.edt_Email);
        edt_password = findViewById(R.id.edt_password);
        btn_login = findViewById(R.id.btn_login);
        txt_register = findViewById(R.id.txt_register);
        progressBar = findViewById(R.id.progress_bar);

        btn_login.setOnClickListener(v -> loginUser());
        txt_register.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));
    }
    void loginUser(){
        String Email = edt_Email.getText().toString();
        String PassWord = edt_password.getText().toString();

        boolean validate = validateData(Email,PassWord);
        if (!validate)  //gia tri sai
        {
            return;
        }
        loginAccountInFirebase(Email,PassWord);
    }
    //Login
    void loginAccountInFirebase(String Email,String PassWord){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        changeInProgress(true);
        firebaseAuth.signInWithEmailAndPassword(Email,PassWord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                changeInProgress(false);
                if (task.isSuccessful())
                {
                    if (firebaseAuth.getCurrentUser().isEmailVerified()){
                        //go to Main
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));///////////////+++++/////////////
                        //se khong can dang nhap lan sau
                        finish();
                    }else {
                        Utility.showToask(LoginActivity.this,"Email not verified, Please verify your email.");
                    }
                }else {
                    Utility.showToask(LoginActivity.this,task.getException().getLocalizedMessage());
                }
            }
        });
    }
    //Hàm đợi đăng nhập vào
    void changeInProgress(boolean inProgress)
    {
        if(inProgress)
        {
            progressBar.setVisibility(View.VISIBLE);
            btn_login.setVisibility(View.VISIBLE);
        }
        else {
            progressBar.setVisibility(View.GONE);
            btn_login.setVisibility(View.VISIBLE);
        }
    }

    //hàm kiểm tra giá trị có khớp với giá trị trên firebafe không?
    boolean validateData(String Email,String PassWord){
        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            edt_Email.setError("Email khong hop le!");
            return false;
        }
        if (PassWord.length()<6){
            edt_password.setError("PassWord khong dung!");
            return false;
        }
        return  true;
    }
}