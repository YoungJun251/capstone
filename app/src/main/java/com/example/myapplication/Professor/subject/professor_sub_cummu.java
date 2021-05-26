package com.example.myapplication.Professor.subject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DAO.Commu;
import com.example.myapplication.DAO.Subject;
import com.example.myapplication.MainFrag.subject.bluetooth.bluetooth;

import com.example.myapplication.MainFrag.subject.sub_commu;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

public class professor_sub_cummu extends Activity {
    private static String TAG = "sub_commu";
    Button btn_search;
    EditText sub_name;
    RecyclerView recyclerView;
    pro_cummu_adapter myadapter;
    Intent intent;
    //ArrayList<Subject> arr;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference docRef = db.collection("Subject");
    DocumentReference doRef = db.collection("users").document(FirebaseAuth.getInstance().getUid());
    String subject_name;
    //.document("database");
    //ArrayList<>;

    //private sub_adapter.OnItemClickListener mListener = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.sub_communication);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerview3);
        intent = getIntent();
        subject_name = intent.getStringExtra("coRef");

        CollectionReference coref = doRef.collection("database").document(intent.getStringExtra("coRef")).collection("2021");


        coref.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    ArrayList<Commu> arr = new ArrayList<>();
                    int i = 1;
                    for(QueryDocumentSnapshot document: task.getResult())
                    {
                        String chk ="X";
                        Log.e(TAG,document.getId());
                        if(document.get("attendance").toString() == "true")
                        {
                            chk = "O";
                        }else chk = "X";
                        Log.e(TAG,document.get("attendance").toString());
                        arr.add(new Commu(Integer.toString(i),document.getId(),chk));
                        i++;
                    }
                    setadapter(arr);
                }
            }
        });


    }
    private void setadapter(ArrayList<Commu> arr) {
        myadapter = new pro_cummu_adapter(this,arr);
        recyclerView.setAdapter(myadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myadapter.setOnItemClickListener(new pro_cummu_adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                HashSet<String> attendUid = new HashSet<>();
                HashSet<String> unAttendUid = new HashSet<>();

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                CollectionReference collectionReference = db.collection("test");
                DocumentReference docRef = collectionReference.document(subject_name).collection("date").document("05.03");

                docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        Map<String, Object> map = value.getData();

                        for(String key : map.keySet()) {
                            if(((Boolean)map.get(key)).booleanValue() == Boolean.parseBoolean("true")) {
                                attendUid.add(key);
                                Log.e("TAG","commu java"+key);
                            } else {
                                unAttendUid.add(key);
                                Log.e("TAG","commu java"+key);
                            }
                        }

                        Intent intent = new Intent(professor_sub_cummu.this, attandance.class);
                        intent.putExtra("name",subject_name );
                        intent.putExtra("attendUid",attendUid);
                        intent.putExtra("unAttendUid",unAttendUid);
                        //intent.putExtra("date",) 날짜부분 받아오는거 해야함
                        startActivity(intent);
                    }
                });

                // 날짜 클릭하면 새로운 activity 이동 필요

            }
        });
        Log.e(TAG,Integer.toString(myadapter.getItemCount()));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()== MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

//    @Override
//    public void onBackPressed() {
//        //안드로이드 백버튼 막기
//        return;
//    }

}
