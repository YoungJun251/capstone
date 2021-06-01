package com.example.myapplication;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.myapplication.MainFrag.home_frag;
import com.example.myapplication.MainFrag.NoticeFrag.notice_frag;
import com.example.myapplication.MainFrag.user_frag;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Menu menu;
    Bundle bundle;
    Fragment home,user,notice;
    OnBackPressedListener mlistener;

    public interface OnBackPressedListener { void onBackPressed(); }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        bottomNavigationView = findViewById(R.id.navigation2);
        menu = getBottomNavigationView().getMenu();

        //fragement 객체 생성
        home = new home_frag();
        user = new user_frag();
        notice = new notice_frag();
        bottomNavigationView.setOnNavigationItemSelectedListener(naviListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, home).commitNow();
        bottomNavigationView.setSelectedItemId(R.id.action_home);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener naviListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.action_home:
                            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, home).commitNow();
                            //selectedFragment = new home_frag();
                            break;

                        case R.id.action_notice:
                            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, notice).commit();
                            //selectedFragment = new notice_frag();
                            break;

                        case R.id.action_user:
                            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, user).commit();
                            //selectedFragment = new user_frag();
                            break;
                    }
                    return true;
                }

            };


    private BottomNavigationView getBottomNavigationView() {
        return bottomNavigationView;
    }
    public void setOnBackPressedListener(OnBackPressedListener listener){
        this.mlistener = listener; }
        @Override public void onBackPressed() {
        if(mlistener!=null){ mlistener.onBackPressed(); }
        else{ super.onBackPressed(); } }

}
