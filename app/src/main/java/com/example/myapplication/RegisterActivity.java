package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Professor.professor_main;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class
RegisterActivity extends AppCompatActivity {

    EditText email,name,pw;
    Button btn_register,btn_create;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    CheckBox prochk,stuchk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        name = findViewById(R.id.login_name);
        pw = findViewById(R.id.login_pw);
        email = findViewById(R.id.login_email);
        btn_register = findViewById(R.id.btn_regi);
        btn_create = findViewById(R.id.btn_create);
        prochk = findViewById(R.id.chk_pro);
        stuchk = findViewById(R.id.chk_stu);

        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser() != null) {
            Toast.makeText(RegisterActivity.this,"user created",Toast.LENGTH_SHORT).show();
            finish();
        }
        //버튼 클릭시 수행행

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), createBluetooth.class));
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //입력된 현재 값들
                String userEmail = email.getText().toString().trim();
                String userPass = pw.getText().toString().trim();
                String userName = name.getText().toString();

                if((prochk.isChecked() && stuchk.isChecked()) || (!prochk.isChecked() && !stuchk.isChecked()) )
                {
                    Toast.makeText(RegisterActivity.this,"한가지만 선택해주세요 ",Toast.LENGTH_SHORT).show();
                    return;
                }

                fAuth.createUserWithEmailAndPassword(userEmail,userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this,"user created",Toast.LENGTH_SHORT).show();
                            userId = fAuth.getCurrentUser().getUid();

                            DocumentReference documentReference;
                            Map<String, Object> user = new HashMap<>();

                            if(stuchk.isChecked()) {
                                documentReference = fStore.collection("users").document(userId);
                                user.put("uName", userName);
                                user.put("pw", userPass);
                                user.put("email", userEmail);
                                user.put("isProfessor",Boolean.valueOf("false"));
                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d("TAG","user profile is created!" + userId);
                                    }
                                });
                            }
                            else{
                                documentReference = fStore.collection("users").document(userId);
                                user.put("uName", userName);
                                user.put("pw", userPass);
                                user.put("email", userEmail);
                                user.put("isProfessor",Boolean.valueOf("true"));

                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d("TAG","user profile is created!" + userId);
                                    }
                                });
                            }

                            Log.e("asdf",user.get("uName").toString());

                            Log.e("TAG",fAuth.getCurrentUser().toString());
                            FirebaseAuth.getInstance().signOut();
                            onBackPressed();
                            //startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        } else {
                            Toast.makeText(RegisterActivity.this,"error:" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}
