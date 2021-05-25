package com.example.myapplication.Professor.search;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DAO.Subject;
import com.example.myapplication.MainFrag.subject.sub_adapter;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class professor_search extends AppCompatActivity {
    private static String TAG = "Subsearch";
    TextView btn_search,sum_result;
    EditText sub_name;
    RecyclerView recyclerView;
    sub_adapter myadapter;
    //ArrayList<Subject> arr;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference docRef = db.collection("Subject");
    //.document("database");
    //ArrayList<>;

    //private sub_adapter.OnItemClickListener mListener = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subject);

        //arr = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview2);
        btn_search = findViewById(R.id.sub_search);
        sub_name = findViewById(R.id.subname);
        sum_result = findViewById(R.id.result);

        String subject = sub_name.getText().toString();
        btn_search.setOnClickListener(onClickListener); // click event
        getlist();
        sub_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                getlist();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void getlist() {
        docRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            int num =0;
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    ArrayList<Subject> arr = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getString("date"));
                        if(document.getId().contains(sub_name.getText()))
                        {
                            Log.e(TAG, String.valueOf(sub_name.getText()));
                            arr.add(new Subject(document.getId(),document.getString("date"),document.getString("professor")));
                            num++;
                        }

                    }
                    Log.e(TAG,Integer.toString(arr.size()));
                    setadapter(arr,num);
                }

            }
        });
    }

    private void setadapter(ArrayList<Subject> arr, int num) {
        myadapter = new sub_adapter(this,arr);
        myadapter.setOnItemClickListener(new sub_adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(getApplicationContext(), String.format("subject click :%d", position),Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(myadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Log.e(TAG,Integer.toString(myadapter.getItemCount()));
        sum_result.setText(num + " Results ");
    }

    View.OnClickListener onClickListener = v -> {
        switch(v.getId()){
            case R.id.sub_search:
                sub_name.setText("");
                break;
        }
    };


}
