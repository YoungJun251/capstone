package com.example.myapplication.MainFrag;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

public class user_frag extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Log.e("err","123");
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.user_frag, container, false);
        Log.e("err", rootView.toString());
        return rootView;

    }


}
