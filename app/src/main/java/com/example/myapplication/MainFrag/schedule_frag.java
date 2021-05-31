package com.example.myapplication.MainFrag;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class schedule_frag extends Fragment {
    private TextView monday[] = new TextView[14];
    private TextView tuesday[] = new TextView[14];
    private TextView wednesday[] = new TextView[14];
    private TextView thursday[] = new TextView[14];
    private TextView friday[] = new TextView[14];
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Context context;
    int [] colors;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    CollectionReference coRef = db.collection("users").document(FirebaseAuth.getInstance().getUid()).collection("database");
   // Resources res = getResources();

    private String TAG = "scheduler";
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        monday[0] = (TextView) getView().findViewById(R.id.monday0);
        monday[1] = (TextView) getView().findViewById(R.id.monday1);
        monday[2] = (TextView) getView().findViewById(R.id.monday2);
        monday[3] = (TextView) getView().findViewById(R.id.monday3);
        monday[4] = (TextView) getView().findViewById(R.id.monday4);
        monday[5] = (TextView) getView().findViewById(R.id.monday5);
        monday[6] = (TextView) getView().findViewById(R.id.monday6);
        monday[7] = (TextView) getView().findViewById(R.id.monday7);
        monday[8] = (TextView) getView().findViewById(R.id.monday8);
        monday[9] = (TextView) getView().findViewById(R.id.monday9);
        monday[10] = (TextView) getView().findViewById(R.id.monday10);
        monday[11] = (TextView) getView().findViewById(R.id.monday11);
        monday[12] = (TextView) getView().findViewById(R.id.monday12);
        monday[13] = (TextView) getView().findViewById(R.id.monday13);
        tuesday[0] = (TextView) getView().findViewById(R.id.tuesday0);
        tuesday[1] = (TextView) getView().findViewById(R.id.tuesday1);
        tuesday[2] = (TextView) getView().findViewById(R.id.tuesday2);
        tuesday[3] = (TextView) getView().findViewById(R.id.tuesday3);
        tuesday[4] = (TextView) getView().findViewById(R.id.tuesday4);
        tuesday[5] = (TextView) getView().findViewById(R.id.tuesday5);
        tuesday[6] = (TextView) getView().findViewById(R.id.tuesday6);
        tuesday[7] = (TextView) getView().findViewById(R.id.tuesday7);
        tuesday[8] = (TextView) getView().findViewById(R.id.tuesday8);
        tuesday[9] = (TextView) getView().findViewById(R.id.tuesday9);
        tuesday[10] = (TextView) getView().findViewById(R.id.tuesday10);
        tuesday[11] = (TextView) getView().findViewById(R.id.tuesday11);
        tuesday[12] = (TextView) getView().findViewById(R.id.tuesday12);
        tuesday[13] = (TextView) getView().findViewById(R.id.tuesday13);
        wednesday[0] = (TextView) getView().findViewById(R.id.wednesday0);
        wednesday[1] = (TextView) getView().findViewById(R.id.wednesday1);
        wednesday[2] = (TextView) getView().findViewById(R.id.wednesday2);
        wednesday[3] = (TextView) getView().findViewById(R.id.wednesday3);
        wednesday[4] = (TextView) getView().findViewById(R.id.wednesday4);
        wednesday[5] = (TextView) getView().findViewById(R.id.wednesday5);
        wednesday[6] = (TextView) getView().findViewById(R.id.wednesday6);
        wednesday[7] = (TextView) getView().findViewById(R.id.wednesday7);
        wednesday[8] = (TextView) getView().findViewById(R.id.wednesday8);
        wednesday[9] = (TextView) getView().findViewById(R.id.wednesday9);
        wednesday[10] = (TextView) getView().findViewById(R.id.wednesday10);
        wednesday[11] = (TextView) getView().findViewById(R.id.wednesday11);
        wednesday[12] = (TextView) getView().findViewById(R.id.wednesday12);
        wednesday[13] = (TextView) getView().findViewById(R.id.wednesday13);
        thursday[0] = (TextView) getView().findViewById(R.id.thursday0);
        thursday[1] = (TextView) getView().findViewById(R.id.thursday1);
        thursday[2] = (TextView) getView().findViewById(R.id.thursday2);
        thursday[3] = (TextView) getView().findViewById(R.id.thursday3);
        thursday[4] = (TextView) getView().findViewById(R.id.thursday4);
        thursday[5] = (TextView) getView().findViewById(R.id.thursday5);
        thursday[6] = (TextView) getView().findViewById(R.id.thursday6);
        thursday[7] = (TextView) getView().findViewById(R.id.thursday7);
        thursday[8] = (TextView) getView().findViewById(R.id.thursday8);
        thursday[9] = (TextView) getView().findViewById(R.id.thursday9);
        thursday[10] = (TextView) getView().findViewById(R.id.thursday10);
        thursday[11] = (TextView) getView().findViewById(R.id.thursday11);
        thursday[12] = (TextView) getView().findViewById(R.id.thursday12);
        thursday[13] = (TextView) getView().findViewById(R.id.thursday13);
        friday[0] = (TextView) getView().findViewById(R.id.friday0);
        friday[1] = (TextView) getView().findViewById(R.id.friday1);
        friday[2] = (TextView) getView().findViewById(R.id.friday2);
        friday[3] = (TextView) getView().findViewById(R.id.friday3);
        friday[4] = (TextView) getView().findViewById(R.id.friday4);
        friday[5] = (TextView) getView().findViewById(R.id.friday5);
        friday[6] = (TextView) getView().findViewById(R.id.friday6);
        friday[7] = (TextView) getView().findViewById(R.id.friday7);
        friday[8] = (TextView) getView().findViewById(R.id.friday8);
        friday[9] = (TextView) getView().findViewById(R.id.friday9);
        friday[10] = (TextView) getView().findViewById(R.id.friday10);
        friday[11] = (TextView) getView().findViewById(R.id.friday11);
        friday[12] = (TextView) getView().findViewById(R.id.friday12);
        friday[13] = (TextView) getView().findViewById(R.id.friday13);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Log.e("err","123");
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_schedule, container, false);
        Log.e("err", rootView.toString());


        getlist();
        colors = new int[]{ContextCompat.getColor(getActivity(), R.color.pastellcol1), ContextCompat.getColor(getActivity(), R.color.pastellcol2), ContextCompat.getColor(getActivity(), R.color.pastellcol3), ContextCompat.getColor(getActivity(), R.color.pastellcol4), ContextCompat.getColor(getActivity(), R.color.pastellcol5), ContextCompat.getColor(getActivity(), R.color.pastellcol6), ContextCompat.getColor(getActivity(), R.color.pastellcol7), ContextCompat.getColor(getActivity(), R.color.pastellcol8), ContextCompat.getColor(getActivity(), R.color.pastellcol9), ContextCompat.getColor(getActivity(), R.color.pastellcol10), ContextCompat.getColor(getActivity(), R.color.pastellcol11)};
        return rootView;

    }

    public void getlist()
    {
        coRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            int index =0;
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    for(QueryDocumentSnapshot document : task.getResult())
                    {
                        String date = document.get("date").toString();
                        String professor = document.get("professor").toString();
                        String subject = document.getId();
                        Log.e(TAG,date);
                        if(date.contains("/"))
                        {
                            String[] str = date.split("/");
                            for(String l : str)
                            {
                                Log.e(TAG,l + " 분리");
                                checkday(l,professor,subject,index);
                            }
                        }
                        else
                            checkday(date,professor,subject,index);
                        index++;
                    }
                }
            }
        });
    }

    public void checkday(String date,String professor,String subject,int index)
    {
        Log.e(TAG,date.charAt(0)+"a " + date.length());
        switch(date.charAt(0)){
            case '월' :
                for(int i=date.charAt(1)-'0';i<date.charAt(1)-'0'+date.length();i++)
                {
                    Log.e(TAG, String.format(date + " - > %d", i));
                    monday[i].setText(subject + "\n"+ professor);
                    monday[i].setBackgroundColor(colors[index]);
                }
                break;
            case '화' :
                for(int i=date.charAt(1)-'0';date.charAt(1)-'0'+i<date.length();i++)
                {
                    tuesday[i].setText(subject + "\n"+ professor);
                    tuesday[i].setBackgroundColor(colors[index]);
                }
                break;
            case '수' :
                for(int i=date.charAt(1)-'0';i<date.charAt(1)-'0'+date.length();i++)
                {
                    wednesday[i].setText(subject + "\n"+ professor);
                    wednesday[i].setBackgroundColor(colors[index]);
                }
                break;
            case '목' :
                for(int i=date.charAt(1)-'0';i<date.charAt(1)-'0'+date.length();i++)
                {
                    thursday[i].setText(subject + "\n"+ professor);
                    thursday[i].setBackgroundColor(colors[index]);
                }
                break;
            case '금' :
                for(int i=date.charAt(1)-'0';i<date.charAt(1)-'0'+date.length();i++)
                {
                    friday[i].setText(subject + "\n"+ professor);
                    friday[i].setBackgroundColor(colors[index]);
                }
                break;

        }
    }





}
