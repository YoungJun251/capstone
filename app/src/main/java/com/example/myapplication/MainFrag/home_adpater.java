package com.example.myapplication.MainFrag;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.MainFrag.subject.subject_frag;
import com.example.myapplication.R;

import java.util.ArrayList;

public class home_adpater extends RecyclerView.Adapter<home_holder> {


    Context c;
    ArrayList<home_model> models;

    public home_adpater(Context c, ArrayList<home_model> models)
    {
        this.c = c;
        this.models = models;
    }

    @NonNull
    @Override
    public home_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlayout,null);
        return new home_holder(view); // holderclass 에 만들 view return
    }

    @Override
    public void onBindViewHolder(@NonNull home_holder holder, int position) {

        holder.sTitle.setText(models.get(position).getTitle());
        holder.imageView.setImageResource(models.get(position).getImg());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity  = (AppCompatActivity)v.getContext();
                subject_frag sub = new subject_frag();
                schedule_frag schedule = new schedule_frag();
                if(position == 0 ) activity.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,sub).addToBackStack(null).commit();
                if(position == 1) activity.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,schedule).addToBackStack(null).commit();
            }
            
        });


    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
