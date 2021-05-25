package com.example.myapplication.Professor.subject;


import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DAO.Subject;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

class professor_subfrag_adapter extends RecyclerView.Adapter<professor_subfrag_adapter.ViewHolder>{
    String TAG = "professor_subfrag_adapter";
    Context c;
    ArrayList<Subject> models;
    int [] colors ;//= {Color.BLUE,Color.RED,Color.CYAN,Color.GREEN,Color.DKGRAY,Color.YELLOW,Color.MAGENTA,Color.rgb(50,50,50),Color.rgb(255,94,0),Color.rgb(153,255,255),Color.rgb(255,153,0)};

    public interface OnItemClickListener {
        void onItemClick(View v, String name) ;
    }

    // 리스너 객체 참조를 저장하는 변수
    private professor_subfrag_adapter.OnItemClickListener mListener = null ;

    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
    public void setOnItemClickListener(professor_subfrag_adapter.OnItemClickListener listener) {
        this.mListener = listener ;
    }

    public professor_subfrag_adapter(Context c, ArrayList<Subject> models)
    {
        this.c = c;
        this.models = models;
        colors = new int[]{Color.parseColor("#95ACBF"),Color.parseColor("#BBCAD9"),Color.parseColor("#F2E5DA"),Color.parseColor("#D9C2A7"),Color.parseColor("#BFA380"),Color.parseColor("#FAF1D6"),Color.parseColor("#FAD4AE"),Color.parseColor("#FDAFAB"),Color.parseColor("#FADEE1"),Color.parseColor("#D9F1F1"),Color.parseColor("#B6E3E9"),Color.parseColor("#FBEDC9"),Color.parseColor("#B6DCB6")};
    }

    @NonNull
    @Override
    public professor_subfrag_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.asdf,parent,false);
        return new professor_subfrag_adapter.ViewHolder(view);
    }

    @NonNull

    @Override
    public void onBindViewHolder(@NonNull professor_subfrag_adapter.ViewHolder holder, int position) {
        holder.subject.setText(models.get(position).getName());
        holder.professor.setText(models.get(position).getProfessor());
        holder.date.setText(models.get(position).getDate());
        holder.bar.setBackgroundColor(colors[position]);


        FirebaseFirestore fStore = FirebaseFirestore.getInstance();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,"view click event !");
                mListener.onItemClick(v,models.get(position).getName());


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

