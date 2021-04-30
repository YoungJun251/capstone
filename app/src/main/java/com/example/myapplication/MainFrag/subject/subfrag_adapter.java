package com.example.myapplication.MainFrag.subject;

import android.content.Context;
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

import java.util.ArrayList;

class subfrag_adatper extends RecyclerView.Adapter<subfrag_adatper.ViewHolder>{
    Context c;
    ArrayList<Subject> models;

    public interface OnItemClickListener {
        void onItemClick(View v, int position) ;
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

    }


    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView subject,professor,date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.subject = itemView.findViewById(R.id.id_sub);
            this.professor = itemView.findViewById(R.id.id_professor);
            this.date = itemView.findViewById(R.id.id_date);


        }
    }
}

