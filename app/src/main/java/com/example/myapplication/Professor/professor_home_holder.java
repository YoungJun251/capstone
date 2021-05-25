package com.example.myapplication.Professor;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.MainFrag.home_holder;
import com.example.myapplication.R;

public class professor_home_holder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView sTitle;
    String TAG = "home_holder";
    // 리스너 객체 참조를 저장하는 변수
    private com.example.myapplication.MainFrag.home_holder.OnItemClickListener mListener = null ;

    public interface OnItemClickListener{
        void onItemClick(View v, int pos);
    }

//    private OnItemClickListener mLinstener = null;
//    public void setOnItemClickListener(OnItemClickListener listener) {
//        this.mListener = listener ;
//    }

    public professor_home_holder(@NonNull View itemView) {
        super(itemView);
        this.imageView = itemView.findViewById(R.id.cd_image);
        this.sTitle = itemView.findViewById(R.id.cd_text);

//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int pos = getAdapterPosition();
//                if(pos != RecyclerView.NO_POSITION){
//                    Log.e(TAG,Integer.toString(pos));
//                    mLinstener.onItemClick(v,pos);
//
//                }
//            }
//        });
    }


}