package com.example.myapplication.MainFrag;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

public class notice_frag extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.notice_frag, container, false);

        return rootView;

    }


}
