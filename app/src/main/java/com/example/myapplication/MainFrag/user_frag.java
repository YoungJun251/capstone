package com.example.myapplication.MainFrag;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class user_frag extends Fragment implements MainActivity.OnBackPressedListener {

    Button logout;
    TextView name;
    DocumentReference doRef;
    FirebaseFirestore fb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Log.e("err","123");
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.mypage, container, false);
        Log.e("err", rootView.toString());

        logout = (Button)rootView.findViewById(R.id.id_logout);
        name = (TextView)rootView.findViewById(R.id.id_name);
        fb = FirebaseFirestore.getInstance();
        doRef = fb.collection("users").document(FirebaseAuth.getInstance().getUid());
        doRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful())
                {
                    DocumentSnapshot docu = task.getResult();
                    name.setText(docu.getString("uName"));
                }
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                onBackPressed();
            }
        });

        return rootView;

    }


    @Override
    public void onBackPressed() {
    }
}
