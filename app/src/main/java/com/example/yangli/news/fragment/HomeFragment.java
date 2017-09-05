package com.example.yangli.news.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yangli.news.R;

import java.util.ArrayList;
import java.util.List;



/**
 * des:新闻首页首页
 * Created by xsf
 * on 2016.09.16:45
 */
public class HomeFragment extends Fragment {
    private View mView;
    private ViewPager mVp;
    private FragmentPagerAdapter mViewpageAdapter;
    private List<Fragment> mListfragment;
    public  Context mContext;
    private List<String> mListtitle;
    private TabLayout tabLayout;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.home_fragment,container,false);

         tabLayout=mView.findViewById(R.id.tablayout);
        mVp = (ViewPager) mView.findViewById(R.id.viewpage);

//添加tab
        tabLayout.addTab(tabLayout.newTab().setText("头条"), false);//添加 Tab,默认选中
        tabLayout.addTab(tabLayout.newTab().setText("财经"),false);//添加 Tab,默认不选中
        tabLayout.addTab(tabLayout.newTab().setText("体育"),false);//添加 Tab,默认不选中
        tabLayout.addTab(tabLayout.newTab().setText("科技"),false);//添加 Tab,默认不选中
        tabLayout.addTab(tabLayout.newTab().setText("娱乐"),false);//添加 Tab,默认不选中


        mListtitle = new ArrayList<>();
        mListtitle.add("头条");
        mListtitle.add("财经");
        mListtitle.add("体育");
        mListtitle.add("科技");
        mListtitle.add("娱乐");

        Fragment f1 = new NewsFragment().newInstance("头条");
        Fragment f2 = new NewsFragment().newInstance("财经");
        Fragment f3 = new NewsFragment().newInstance("体育");
        Fragment f4 = new NewsFragment().newInstance("科技");
        Fragment f5 = new NewsFragment().newInstance("娱乐");

        mListfragment = new ArrayList<>();
        mListfragment.add(f1);
        mListfragment.add(f2);
        mListfragment.add(f3);
        mListfragment.add(f4);
        mListfragment.add(f5);

    //设置viewpage的adapter
        mViewpageAdapter = new NewsAdapter(getFragmentManager(),mListfragment,mListtitle);
        mVp.setAdapter(mViewpageAdapter);
        //tablayout和viewpage进行绑定
        tabLayout.setupWithViewPager(mVp);

        return mView;
    }


//viewpage的adapeter
    public class NewsAdapter extends FragmentPagerAdapter {

        private List<Fragment> aListfragment;                         //fragment列表
        private List<String> aListTitle;                              //tab名的列表

        public NewsAdapter(FragmentManager fm, List<Fragment> list_fragment, List<String> list_title) {
            super(fm);
            this.aListfragment = mListfragment;
            this.aListTitle = mListtitle;

        }

        @Override
        public Fragment getItem(int position) {

            return aListfragment.get(position);
        }

        @Override
        public int getCount() {

            return aListfragment.size();
        }

        //此方法用来显示tab上的名字
        @Override
        public CharSequence getPageTitle(int position) {

            return aListTitle.get(position % aListTitle.size());
        }
    }
}



