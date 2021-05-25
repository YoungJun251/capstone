package com.example.myapplication.Professor.search;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DAO.Subject;
import com.example.myapplication.MainFrag.subject.sub_holder;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class professor_subadapter extends RecyclerView.Adapter<professor_subholder>{

    String TAG = "professor_sub_adapter";
    Context c;
    ArrayList<Subject> models;
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    DocumentReference doRef = fStore.collection("users").document(FirebaseAuth.getInstance().getUid());


    public interface OnItemClickListener {
        void onItemClick(View v, int position) ;
    }

    // 리스너 객체 참조를 저장하는 변수
    private com.example.myapplication.MainFrag.subject.sub_adapter.OnItemClickListener mListener = null ;

    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
    public void setOnItemClickListener(com.example.myapplication.MainFrag.subject.sub_adapter.OnItemClickListener listener) {
        this.mListener = listener ;
    }

    public professor_subadapter (Context c, ArrayList<Subject> models)
    {
        this.c = c;
        this.models = models;
    }

    @NonNull
    @Override
    public professor_subholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sublayout,parent,false);
        return new professor_subholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull professor_subholder holder, int position) {

        holder.subject.setText(models.get(position).getName());
        holder.professor.setText(models.get(position).getProfessor());
        holder.date.setText(models.get(position).getDate());
        //holder.bar.setBackgroundColor(colors[position]);
        fAuth = FirebaseAuth.getInstance();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "view click event !");
                CollectionReference CoRef = fStore.collection("users").document(FirebaseAuth.getInstance().getUid()).collection("database");
                Map<String, Object> subject = new HashMap<>();

                Map<String, Boolean> student_uid = new HashMap<>();

                CollectionReference coref = fStore.collection("Subject").document(models.get(position).getName()).collection("2021");

                //교수가 출석정보를 볼 때 참조할 곳
                CollectionReference coref2 = fStore.collection("test").document(models.get(position).getName()).collection("date");

                String userId = fAuth.getCurrentUser().getUid(); //현재 사용자의 uid

                subject.put("professor", models.get(position).getProfessor());
                subject.put("date", models.get(position).getDate());

                student_uid.put(userId, false);


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

                        coref2.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                Log.e("TAG","교수용 출석 확인 디비 갱신 완료");
                                if(task.isSuccessful()) {
                                    for(QueryDocumentSnapshot document : task.getResult()) {
                                        coref2.document(document.getId()).set(student_uid, SetOptions.merge());
                                        Log.e(TAG,document.getId() + "에 uid 입력됨");
                                    }
                                }
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

