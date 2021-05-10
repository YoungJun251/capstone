package com.example.myapplication.MainFrag.NoticeFrag;


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

import com.example.myapplication.DAO.Commu;
import com.example.myapplication.DAO.Notice;
import com.example.myapplication.DAO.Subject;
import com.example.myapplication.MainFrag.home_holder;
import com.example.myapplication.MainFrag.home_model;
import com.example.myapplication.R;
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

class notice_adapter extends RecyclerView.Adapter<notice_adapter.ViewHolder>{
    String TAG = "notice_adapter";
    Context c;
    ArrayList<Notice> models;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String send = FirebaseAuth.getInstance().getUid();

    public interface OnItemClickListener {
        void onItemClick(View v, int position) ;
    }

    // 리스너 객체 참조를 저장하는 변수
    private notice_adapter.OnItemClickListener mListener = null ;

    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
    public void setOnItemClickListener(notice_adapter.OnItemClickListener listener) {
        this.mListener = listener ;
    }

    public notice_adapter(Context c, ArrayList<Notice> models)
    {
        this.c = c;
        this.models = models;
    }

    @NonNull
    @Override
    public notice_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notice_layout,parent,false);
        return new notice_adapter.ViewHolder(view);
    }

    @NonNull

    @Override
    public void onBindViewHolder(@NonNull notice_adapter.ViewHolder holder, int position) {

        holder.text.setText(models.get(position).getText());
        holder.date.setText(models.get(position).getDate());
        holder.cnt.setText(models.get(position).getCnt());


        FirebaseFirestore fStore = FirebaseFirestore.getInstance();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,"view click event !");
                mListener.onItemClick(v, position);

            }
        });
    }


    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView text,date,cnt;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.text = itemView.findViewById(R.id.text_n1);
            this.date = itemView.findViewById(R.id.text_n2);
            this.cnt = itemView.findViewById(R.id.text_n3);


        }

    }
}

