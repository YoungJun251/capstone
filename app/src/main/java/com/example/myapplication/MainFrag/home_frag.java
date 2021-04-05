package com.example.myapplication.MainFrag;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.LoginActivity;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import java.util.ArrayList;

public class home_frag extends Fragment {

    RecyclerView recyclerView;
    home_adpater myadapter ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.home_frag, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));// recyclerview 를 linear 하게 생성
        //recyclerView.setHasFixedSize(true);

        myadapter = new home_adpater(getActivity(),getMyList());
        recyclerView.addItemDecoration(new RecyclerViewDecoration(60));
        recyclerView.setAdapter(myadapter);
        return rootView;
    }

    private ArrayList<home_model> getMyList() {

        ArrayList<home_model> models = new ArrayList<>();

        home_model m = new home_model();
        m.setTitle("수강과목정보");
        m.setImg(R.drawable.ic_subject);
        models.add(m);

        m = new home_model();
        m.setTitle("시간표");
        m.setImg(R.drawable.ic_schedule);
        models.add(m);

        m = new home_model();
        m.setTitle("Frag3");
        m.setImg(R.drawable.ic_notice);
        models.add(m);

        return models;
    }

}
