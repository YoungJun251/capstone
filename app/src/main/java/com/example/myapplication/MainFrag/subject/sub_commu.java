package com.example.myapplication.MainFrag.subject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DAO.Commu;
import com.example.myapplication.DAO.Subject;
import com.example.myapplication.MainFrag.subject.bluetooth.bluetooth;
import com.example.myapplication.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class sub_commu extends Activity {
    private static String TAG = "sub_commu";
    Button btn_search;
    EditText sub_name;
    RecyclerView recyclerView;
    sub_commu_adapter myadapter;
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
        myadapter = new sub_commu_adapter(this,arr);
        recyclerView.setAdapter(myadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myadapter.setOnItemClickListener(new sub_commu_adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(sub_commu.this, bluetooth.class);
                intent.putExtra("name",subject_name);
                startActivity(intent);
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
