package com.example.myapplication.MainFrag.subject;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DAO.Subject;
import com.example.myapplication.MainFrag.home_holder;
import com.example.myapplication.MainFrag.home_model;
import com.example.myapplication.R;
import com.example.myapplication.RegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class sub_adapter extends RecyclerView.Adapter<sub_holder>{

    String TAG = "sub_adapter";
    Context c;
    ArrayList<Subject> models;
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    DocumentReference doRef = fStore.collection("users").document(FirebaseAuth.getInstance().getUid());


    public interface OnItemClickListener {
        void onItemClick(View v, int position) ;
    }

    // 리스너 객체 참조를 저장하는 변수
    private OnItemClickListener mListener = null ;

    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener ;
    }

    public sub_adapter(Context c, ArrayList<Subject> models)
    {
        this.c = c;
        this.models = models;
    }

    @NonNull
    @Override
    public sub_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sublayout,parent,false);
        return new sub_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull sub_holder holder, int position) {

        holder.subject.setText(models.get(position).getName());
        holder.professor.setText(models.get(position).getProfessor());
        holder.date.setText(models.get(position).getDate());
        //holder.bar.setBackgroundColor(colors[position]);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "view click event !");
                CollectionReference CoRef = fStore.collection("users").document(FirebaseAuth.getInstance().getUid()).collection("database");
                Map<String, Object> subject = new HashMap<>();

                CollectionReference coref = fStore.collection("Subject").document(models.get(position).getName()).collection("2021");

                subject.put("professor", models.get(position).getProfessor());
                subject.put("date", models.get(position).getDate());


                CoRef.document(models.get(position).getName()).set(subject).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        mListener.onItemClick(v, position);

                        coref.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                Log.e("TAG","complete");
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        HashMap<String, Object> map2 = new HashMap<>();
                                        map2.put("attendance", document.get("attendance"));
                                        map2.put("time", document.get("time"));
                                        Log.e(TAG,"lee"+document.getId());
                                        CoRef.document(models.get(position).getName()).collection("2021").document(document.getId()).set(map2);
                                    }

                                }
                                else Log.e(TAG,"fail");
                            }
                        });
                    }
                });
            }
        });
    }


    @Override
    public int getItemCount() {
        return models.size();
    }
}

