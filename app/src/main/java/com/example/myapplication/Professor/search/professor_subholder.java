package com.example.myapplication.Professor.search;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class professor_subholder extends RecyclerView.ViewHolder{

    TextView subject,professor,date;


    public professor_subholder(@NonNull View itemView) {
        super(itemView);

        this.subject = itemView.findViewById(R.id.id_sub);
        this.professor = itemView.findViewById(R.id.id_professor);
        this.date = itemView.findViewById(R.id.id_date);


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    // 데이터 리스트로부터 아이템 데이터 참조.

                    // TODO : use item.
                }
            }
        });
    }
}
