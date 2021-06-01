package com.example.myapplication.Professor.subject;




import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DAO.Subject;
import com.example.myapplication.DAO.attend;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

class attedance_adapter extends RecyclerView.Adapter<attedance_adapter.ViewHolder>{
    String TAG = "attendance_adapter";
    Context c;
    ArrayList<attend> models;


    public interface OnItemClickListener {
        void onItemClick(View v, String name) ;
    }

    // 리스너 객체 참조를 저장하는 변수
    private professor_subfrag_adapter.OnItemClickListener mListener = null ;

    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
    public void setOnItemClickListener(professor_subfrag_adapter.OnItemClickListener listener) {
        this.mListener = listener ;
    }

    public attedance_adapter(Context c, ArrayList<attend> models)
    {
        this.c = c;
        this.models = models;
    }

    @NonNull
    @Override
    public attedance_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.attend,parent,false);
        return new attedance_adapter.ViewHolder(view);
    }

    @NonNull

    @Override
    public void onBindViewHolder(@NonNull attedance_adapter.ViewHolder holder, int position) {
        holder.num.setText(models.get(position).getNum());
        holder.name.setText(models.get(position).getName());
        holder.attend.setText(models.get(position).getChk());


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

        TextView num,name,attend,chk;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.num = itemView.findViewById(R.id.id_num);
            this.name = itemView.findViewById(R.id.id_sub);
            this.attend = itemView.findViewById(R.id.id_attend);
            this.chk = itemView.findViewById(R.id.id_chk);

        }

    }
}

