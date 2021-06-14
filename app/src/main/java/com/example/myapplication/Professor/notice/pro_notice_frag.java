package com.example.myapplication.Professor.notice;



import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myapplication.DAO.Notice;
import com.example.myapplication.MainActivity;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.core.OrderBy;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executor;

public class pro_notice_frag extends Fragment {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference coref2 = db.collection("test");
    DocumentReference doref[];
    ArrayList<String> arr;
    CollectionReference CoRef = db.collection("users").document(FirebaseAuth.getInstance().getUid()).collection("Log");
    RecyclerView recyclerView;
    String TAG = "pro_notice_frag", t;
    Context context;
    SwipeRefreshLayout swipeRefreshLayout;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    pro_notice_adapter myadapter;
    int length;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.notice_frag, container, false);


        CollectionReference pro_db = db.collection("users").document(FirebaseAuth.getInstance().getUid()).collection("database");
        arr = new ArrayList<>();
        length = 0;
        pro_db.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    for(DocumentSnapshot document : task.getResult())
                    {
                        arr.add(document.getId());
                    }
                }
            }
        });

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview_notice);
        context = container.getContext();
        getlist();


        length = arr.size();
        doref = new DocumentReference[length];
        for(int i=0; i<length;i++)
        {
            doref[i] = db.collection("test").document(arr.get(i));
        }

        CoRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshots,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "listen:error", e);
                    return;
                }

                for (DocumentChange dc : snapshots.getDocumentChanges()) {
                    if (dc.getType() == DocumentChange.Type.MODIFIED) {
                        Log.d(TAG, "database changed: " + dc.getDocument().getData());
                    }
                    if (dc.getType() == DocumentChange.Type.ADDED) {
                        Log.d(TAG, "database changed: " + dc.getDocument().getData());
                        getlist();
                    }

                }

            }
        });

        return rootView;

    }

    private void getlist() {
        CoRef.orderBy("time", Query.Direction.DESCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    int i=0;
                    ArrayList<Notice> arr = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Date date = document.getDate("time");
                        t = format.format(date);
                        arr.add(new Notice(document.get("subject").toString()+" 과목에 출석 되었습니다.",t,String.format("<%d>",i)));
                        i++;
                    }

                    setadapter(arr);
                }
            }
        });
    }

    private void setadapter(ArrayList<Notice> arr) {
        myadapter = new pro_notice_adapter(getContext(),arr);
        myadapter.setOnItemClickListener(new pro_notice_adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

            }
        });

        recyclerView.setAdapter(myadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }






}
