package com.example.myapplication.Professor.subject;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myapplication.DAO.Subject;
import com.example.myapplication.MainFrag.subject.Subsearch;
import com.example.myapplication.MainFrag.subject.sub_commu;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class professor_subject_frag extends Fragment {

    String TAG = "professor_subject_frag";
    RecyclerView recyclerView;
    //FloatingActionButton btn;
    professor_subfrag_adapter myadapter;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference doRef = db.collection("users").document(FirebaseAuth.getInstance().getUid());

    SwipeRefreshLayout swipeRefreshLayout;
    TextView back;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.professor_subfrag, container, false);

        //back=(TextView) rootView.findViewById(R.id.back);
        swipeRefreshLayout = (SwipeRefreshLayout)rootView.findViewById(R.id.refresh_layout);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.subject_recyclerview);

       // btn = (FloatingActionButton) rootView.findViewById(R.id.fab);
        //btn.setOnClickListener(onClickListener);

        getlist();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getlist();
                //새로고침 완료
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        return rootView;
    }

    private void getlist() {
        CollectionReference coRef= doRef.collection("database");
        coRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    ArrayList<Subject> arr = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getString("date"));
                        arr.add(new Subject(document.getId(),document.getString("date"),document.getString("professor")));
                    }

                    setadapter(arr);
                }
            }
        });
    }

    private void setadapter(ArrayList<Subject> arr) {
        myadapter = new professor_subfrag_adapter(getContext(),arr);
        myadapter.setOnItemClickListener(new professor_subfrag_adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, String name) {
                Intent intent = new Intent(getActivity(), professor_sub_cummu.class);
                intent.putExtra("coRef",name);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(myadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.e(TAG,Integer.toString(myadapter.getItemCount()));
    }



    View.OnClickListener onClickListener = v -> {
        switch(v.getId()){
            case R.id.fab:
                Intent intent = new Intent(getActivity(), Subsearch.class);
                startActivity(intent);
                break;
        }
    };

}
