package com.example.myapplication.Professor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.MainFrag.RecyclerViewDecoration;
import com.example.myapplication.MainFrag.home_adpater;
import com.example.myapplication.MainFrag.home_model;
import com.example.myapplication.R;

import java.util.ArrayList;

public class professor_home_frag extends Fragment {

    RecyclerView recyclerView;
    professor_home_adapter myadapter ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.home_frag, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));// recyclerview 를 linear 하게 생성
        //recyclerView.setHasFixedSize(true);

        myadapter = new professor_home_adapter(getActivity(),getMyList());
        recyclerView.addItemDecoration(new RecyclerViewDecoration(60));
        recyclerView.setAdapter(myadapter);
        return rootView;
    }

    private ArrayList<home_model> getMyList() {

        ArrayList<home_model> models = new ArrayList<>();

        home_model m = new home_model();
        m.setTitle("수업 과목 조회");
        m.setImg(R.drawable.ic_subject);
        models.add(m);

        m = new home_model();
        m.setTitle("수업 과목 추가");
        m.setImg(R.drawable.ic_schedule);
        models.add(m);

        return models;
    }

}
