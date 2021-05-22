package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth fAuth;
    EditText email,pw;
    String TAG = "LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseAuth.getInstance().signOut();

        email = findViewById(R.id.login);
        pw = findViewById(R.id.pw);
        fAuth = FirebaseAuth.getInstance();

        Button btn_login = findViewById(R.id.access);
        TextView textView = findViewById(R.id.register);


        textView.setOnClickListener(onClickListener);
        btn_login.setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = v ->
    {
        switch(v.getId()){
            //Intent intent;
            case R.id.access:
                String Email = email.getText().toString().trim();
                String PW = pw.getText().toString().trim();
            try {
                fAuth.signInWithEmailAndPassword(Email, PW).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "logged in", Toast.LENGTH_SHORT).show();
                            Log.e(TAG,fAuth.getUid());

                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else { //비밀번호가 틀리거나 잘못입력
                            Toast.makeText(LoginActivity.this, "error:" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            }
            catch (Exception e){
                Toast.makeText(LoginActivity.this,"아이디/패스워드를 입력해주세요 ",Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.register:
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
                break;

        }
    };

}