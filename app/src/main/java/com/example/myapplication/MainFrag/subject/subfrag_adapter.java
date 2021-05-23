package com.example.myapplication.MainFrag.subject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DAO.Subject;
import com.example.myapplication.MainFrag.home_holder;
import com.example.myapplication.MainFrag.home_model;
import com.example.myapplication.R;
import com.example.myapplication.RegisterActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

class subfrag_adatper extends RecyclerView.Adapter<subfrag_adatper.ViewHolder>{
    String TAG = "subfrag_adapter";
    Context c;
    ArrayList<Subject> models;
    int [] colors = {Color.BLUE,Color.RED,Color.CYAN,Color.GREEN,Color.DKGRAY,Color.YELLOW,Color.MAGENTA,Color.rgb(50,50,50),Color.rgb(255,94,0),Color.rgb(153,255,255),Color.rgb(255,153,0)};

    public interface OnItemClickListener {
        void onItemClick(View v, String name) ;
    }

    // 리스너 객체 참조를 저장하는 변수
    private OnItemClickListener mListener = null ;

    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener ;
    }

    public subfrag_adatper(Context c, ArrayList<Subject> models)
    {
        this.c = c;
        this.models = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.asdf,parent,false);
        return new ViewHolder(view);
    }

    @NonNull

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.subject.setText(models.get(position).getName());
        holder.professor.setText(models.get(position).getProfessor());
        holder.date.setText(models.get(position).getDate());
        holder.bar.setBackgroundColor(colors[position]);
        Log.d(TAG,Integer.toString(position) + " 번쨰 색");

        FirebaseFirestore fStore = FirebaseFirestore.getInstance();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,"view click event !");
                SimpleDateFormat format = new SimpleDateFormat("MM.dd");
                Date time = new Date();
                String date = format.format(time);

                mListener.onItemClick(v,models.get(position).getName());


                DocumentReference doRef = fStore.collection("users").document(FirebaseAuth.getInstance().getUid()).collection("database").document(models.get(position).getName()).collection("2021").document(date);
                Map<String, Object> docData = new HashMap<>();
                //docData.put("attendance", true);
                //docData.put("time", new Timestamp(new Date()));
//                doRef.set(docData).addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Log.e(TAG, "update succeess");
//                    }
//                });


            }
        });
    }


    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView subject,professor,date,bar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.subject = itemView.findViewById(R.id.id_sub);
            this.professor = itemView.findViewById(R.id.id_professor);
            this.date = itemView.findViewById(R.id.id_date);
            this.bar = itemView.findViewById(R.id.id_bar);


        }

    }
}

