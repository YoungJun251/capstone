package com.example.myapplication.Professor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.MainFrag.home_holder;
import com.example.myapplication.MainFrag.home_model;
import com.example.myapplication.MainFrag.schedule_frag;
import com.example.myapplication.MainFrag.subject.Subsearch;
import com.example.myapplication.MainFrag.subject.subject_frag;
import com.example.myapplication.Professor.search.professor_search;
import com.example.myapplication.Professor.subject.professor_subject_frag;
import com.example.myapplication.R;

import java.util.ArrayList;

public class professor_home_adapter extends RecyclerView.Adapter<professor_home_holder> {


    Context c;
    ArrayList<home_model> models;

    public professor_home_adapter(Context c, ArrayList<home_model> models)
    {
        this.c = c;
        this.models = models;
    }

    @NonNull
    @Override
    public professor_home_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlayout,null);
        return new professor_home_holder(view); // holderclass 에 만들 view return
    }

    @Override
    public void onBindViewHolder(@NonNull professor_home_holder holder, int position) {

        holder.sTitle.setText(models.get(position).getTitle());
        holder.imageView.setImageResource(models.get(position).getImg());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity  = (AppCompatActivity)v.getContext();
                professor_subject_frag sub = new professor_subject_frag();
                Subsearch search = new Subsearch();
                if(position == 0 ) activity.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,sub).addToBackStack(null).commit();
                if(position == 1) activity.startActivity(new Intent(c, professor_search.class));
            }

        });


    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}