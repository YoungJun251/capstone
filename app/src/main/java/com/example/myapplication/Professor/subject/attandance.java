package com.example.myapplication.Professor.subject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.DAO.Subject;
import com.example.myapplication.DAO.attend;
import com.example.myapplication.MainFrag.subject.sub_adapter;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class attandance extends AppCompatActivity {
    private String course,date;
    private EditText et_id;
    private HashSet<String> attendUid,unAttendUid; //중복 uid 제거
    RecyclerView recyclerView;
    attedance_adapter myadapter;
    Intent intent;
    ArrayList<attend> arr = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attandance);

        Log.e("TAG","start activity" );
        intent = getIntent();
        course = intent.getStringExtra("name");
        date = intent.getStringExtra("date");
        attendUid = (HashSet<String>) intent.getSerializableExtra("attendUid");
        unAttendUid = (HashSet<String>) intent.getSerializableExtra("unAttendUid");
        //et_id = findViewById(R.id.et_id);
        recyclerView = findViewById(R.id.recyclerview4);
        ArrayList<String> attand = new ArrayList<>();
        ArrayList<String> unAttand = new ArrayList<>();


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference userId = db.collection("users");
        CollectionReference coref = db.collection(course).document("date").collection(date);


        userId.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    List<DocumentSnapshot> doc = task.getResult().getDocuments();

                    for(DocumentSnapshot tmp : doc) {
                        String UID = tmp.getId();
                        String userName = tmp.get("uName").toString();


                        if(attendUid.contains(UID) ) { //출석한 사람
                            attand.add(userName);
                        }
                        if(unAttendUid.contains(UID)&& tmp.getBoolean("isProfessor")==false){
                            unAttand.add(userName);

                        }
                    }

                    int number = 1;
                    Log.e("TAG","출석 안한 사람 모임");
                    for(String n : unAttand ) {
                        String name = unAttand.get(number-1);
                        arr.add(new attend(Integer.toString(number),name," X "));
                        number++;
                        Log.e("TAG", n);
                    }
                    int index = 0;
                    Log.e("TAG","출석 한 사람 모임");
                    for(String n : attand ) {
                        arr.add(new attend(Integer.toString(number),attand.get(index)," O "));
                        number++;
                        index++;
                        Log.e("TAG", n);
                    }

                /* 여기다가 코드 추가 하면 됨.
                * 출석 한 인원은 attend배열에 이름있음
                * 출석 안 한 인원은 unAttend배열에 있음
                * */

                setadapter(arr);
                }
            }
        });


    }
    private void setadapter(ArrayList<attend> arr) {
        myadapter = new attedance_adapter(this,arr);
        recyclerView.setAdapter(myadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }



}