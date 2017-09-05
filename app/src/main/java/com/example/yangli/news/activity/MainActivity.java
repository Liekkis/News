package com.example.yangli.news.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.example.yangli.news.R;
import com.example.yangli.news.fragment.HomeFragment;
import com.example.yangli.news.fragment.NewsFragment;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public Handler handler;
    private Context mContext;
    private List<Map<String, Object>> mData;
    public HomeFragment homeFragment ;
    public NewsFragment newsFragment;
    FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.Home:
                    Log.d("CESHI", "HOME");
                        homeFragment=new HomeFragment();

                    fragmentManager.beginTransaction().replace(R.id.content,homeFragment).commit();

                    return true;
                case R.id.News:
                    newsFragment=new NewsFragment().newInstance("头条");

                    fragmentManager.beginTransaction().replace(R.id.content,newsFragment).commit();
                    Log.d("CESHI", "News");
                    return true;
                case R.id.Live:
                    Log.d("CESHI", "Live");
                    return true;
                case R.id.Video:
                    Log.d("CESHI", "Video");
                    return true;
                case R.id.Me:
                    Log.d("CESHI", "Me");
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }




}

