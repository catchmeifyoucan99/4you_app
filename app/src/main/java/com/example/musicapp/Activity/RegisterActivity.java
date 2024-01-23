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

public class RegisterActivity extends AppCompatActivity {
    EditText edt_username, edt_Email, edt_password, edt_confirmPass;
    Button btn_createAcc;
    ProgressBar progressBar;
    TextView txt_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edt_username = findViewById(R.id.edt_username);
        edt_Email = findViewById(R.id.edt_Email);
        edt_password = findViewById(R.id.edt_password);
        edt_confirmPass = findViewById(R.id.edt_confirmPass);
        btn_createAcc = findViewById(R.id.btn_CreateAcc);
        txt_login = findViewById(R.id.txt_login);
        progressBar = findViewById(R.id.progress_bar);

        btn_createAcc.setOnClickListener(v -> createAccount());
        txt_login.setOnClickListener(v -> startActivity(new Intent(RegisterActivity.this, LoginActivity.class)));
    }
    void createAccount(){
        String UserName = edt_username.getText().toString();
        String Email = edt_Email.getText().toString();
        String PassWord = edt_password.getText().toString();
        String ConfirmPassWord = edt_confirmPass.getText().toString();

        boolean validate = validateData(UserName,Email,PassWord,ConfirmPassWord);
        if (!validate)  //gia tri sai
        {
            return;
        }
        creatAccountInFirebase(Email,PassWord);
    }

    //
    void  creatAccountInFirebase(String Email, String PassWord)
    {
        changeInProgress(true);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(Email,PassWord).addOnCompleteListener(RegisterActivity.this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        changeInProgress(false);
                        if (task.isSuccessful())
                        {
                            //creating acc is done
                            //Lop Utility da duoc dinh nghia
                            Utility.showToask(RegisterActivity.this,"Successfully,Check email to verify");
                            firebaseAuth.getCurrentUser().sendEmailVerification();
                            firebaseAuth.signOut();
                            finish();
                        }else {
                            //fail
                            Utility.showToask(RegisterActivity.this,task.getException().getLocalizedMessage());
                        }
                    }
                });
    }
    //Hàm đợi tạo tài khoản
    void changeInProgress(boolean inProgress)
    {
        if(inProgress)
        {
            progressBar.setVisibility(View.VISIBLE);
            btn_createAcc.setVisibility(View.VISIBLE);
        }
        else {
            progressBar.setVisibility(View.GONE);
            btn_createAcc.setVisibility(View.VISIBLE);
        }
    }

    //hàm kiểm tra giá trị
    boolean validateData(String UserName,String Email,String PassWord,String ConfirmPassWord ){
        if (UserName.length()<4){
            edt_username.setError("UserName khong hop le!");
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            edt_Email.setError("Email khong hop le!");
            return false;
        }
        if (PassWord.length()<6){
            edt_password.setError("PassWord khong du dai!");
            return false;
        }
        if (!PassWord.equals(ConfirmPassWord)){
            edt_confirmPass.setError("PassWord khong khop!");
            return false;
        }
        return  true;
    }
}