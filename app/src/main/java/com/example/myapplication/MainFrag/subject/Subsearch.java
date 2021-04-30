package com.example.myapplication.MainFrag.subject;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DAO.Subject;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Subsearch extends AppCompatActivity{
    private static String TAG = "Subsearch";
    Button btn_search;
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

        String subject = sub_name.getText().toString();
        btn_search.setOnClickListener(onClickListener); // click event

        getlist();
    }

    private void getlist() {
        docRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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
                        }

                    }
                    Log.e(TAG,Integer.toString(arr.size()));
                    setadapter(arr);
                }

            }
        });
    }

    private void setadapter(ArrayList<Subject> arr) {
        myadapter = new sub_adapter(this,arr);
        myadapter.setOnItemClickListener(new sub_adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(Subsearch.this, String.format("subject click :%d", position),Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(myadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Log.e(TAG,Integer.toString(myadapter.getItemCount()));
    }

    View.OnClickListener onClickListener = v -> {
        switch(v.getId()){
            case R.id.sub_search:
                Log.e(TAG,"click");
                getlist();
                break;
        }
    };


}
