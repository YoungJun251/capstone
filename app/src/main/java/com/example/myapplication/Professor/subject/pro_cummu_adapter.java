package com.example.myapplication.Professor.subject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DAO.Commu;
import com.example.myapplication.DAO.Commu2;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

class pro_cummu_adapter extends RecyclerView.Adapter<pro_cummu_adapter.ViewHolder>{
    String TAG = "sub_commu_adapter";
    Context c;
    ArrayList<Commu2> models;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String send = FirebaseAuth.getInstance().getUid();

    public interface OnItemClickListener {
        void onItemClick(View v, String name);
    }

    // 리스너 객체 참조를 저장하는 변수
    private pro_cummu_adapter.OnItemClickListener mListener = null ;

    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
    public void setOnItemClickListener(pro_cummu_adapter.OnItemClickListener listener) {
        this.mListener = listener ;
    }

    public pro_cummu_adapter(Context c, ArrayList<Commu2> models)
    {
        this.c = c;
        this.models = models;
    }

    @NonNull
    @Override
    public pro_cummu_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_commu_layout,parent,false);
        return new pro_cummu_adapter.ViewHolder(view);
    }

    @NonNull

    @Override
    public void onBindViewHolder(@NonNull pro_cummu_adapter.ViewHolder holder, int position) {

        holder.cnt.setText(models.get(position).getCnt());
        holder.subdate.setText(models.get(position).getDate());
        holder.chk.setText(models.get(position).getNum());


        FirebaseFirestore fStore = FirebaseFirestore.getInstance();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,"view click event !");
                mListener.onItemClick(v, models.get(position).getDate());

            }
        });
    }


    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView cnt,subdate,chk;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.cnt = itemView.findViewById(R.id.id_cnt);
            this.subdate = itemView.findViewById(R.id.id_subdate);
            this.chk = itemView.findViewById(R.id.id_chk);


        }

    }
}

