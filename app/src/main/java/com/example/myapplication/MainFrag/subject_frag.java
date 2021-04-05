package com.example.myapplication.MainFrag;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class subject_frag extends Fragment {

    RecyclerView recyclerView;
    FloatingActionButton btn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.subject_frag, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.subject_recyclerview);
        btn = (FloatingActionButton) rootView.findViewById(R.id.fab);
        btn.setOnClickListener(onClickListener);
        return rootView;
    }

    View.OnClickListener onClickListener = v -> {
        switch(v.getId()){
            case R.id.fab:
                break;
        }
    };

}
