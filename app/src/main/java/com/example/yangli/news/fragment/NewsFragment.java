package com.example.yangli.news.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.AdapterView;

import com.example.yangli.news.R;
import com.example.yangli.news.activity.NewsDetailActivity;
import com.example.yangli.news.adapter.MyAdapter;
import com.example.yangli.news.tool.Get;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yangli on 17-8-29.
 */

public class NewsFragment extends Fragment {

    private Context mContext;
 //   private MyAdapter myAdapter;
    private ListView mListView;
    private List<Map<String, Object>> mData;
    private String mType;
    private View mView;
    private Get newsget;
    private String mUrl;
    private MyAdapter myAdapter;
    public static NewsFragment newInstance(String type) {
        NewsFragment newFragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        newFragment.setArguments(bundle);
        return newFragment;

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        mType = args.getString("type");
        new Thread(networkTask).start();

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.newlist, null);
        mListView = mView.findViewById(R.id.listview);
       mListView.setAdapter(myAdapter);
        mListView.setOnItemClickListener(new ItemClickEvent());
        return mView;
    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            ArrayList list = data.getParcelableArrayList("list");
            List<Map<String, Object>> list2= (List<Map<String, Object>>) list.get(0);//强转成你自己定义的list，这样list2就是你传过来的那个list了。
             myAdapter=new MyAdapter(mContext) ;
            myAdapter.updateData(list2);
           mListView.setAdapter(myAdapter);

            Log.d("CESHI", "设置适配器结束");
        }
    };
    /**
     * 网络请求数据，存入Bunlde中通过message发送给UI线程
     */
    Runnable networkTask = new Runnable() {
        @Override
        public void run() {
            // TODO
            // 在这里进行 http request.网络请求相关操作
            Message msg = new Message();
             newsget = new Get(mType);
            try {
                 mData = newsget.NewGet();
                Log.d("CESHI", "新线程获取list");
            } catch (Exception e) {
                e.printStackTrace();
            }
            Bundle bundle = new Bundle();
            ArrayList list = new ArrayList(); //这个list用于在budnle中传递 需要传递的ArrayList<Object>
            list.add(mData);
            bundle.putParcelableArrayList("list", list);
            msg.setData(bundle);
            handler.sendMessage(msg);
        }
    };
//为listview绑定监听器




    private final class ItemClickEvent implements OnItemClickListener{
        @Override
        // parent相当于listview  Y适配器的一个指针，可以通过它来获得Y里装着的一切东西，再通俗点就是说告诉你，你点的是Y，不是X - -、
        //view是你点b item的view的句柄，就是你可以用这个view，来获得b里的控件的id后操作控件
        // position是b在Y适配器里的位置（生成listview时，适配器一个一个的做item，然后把他们按顺序排好队
        // ，在放到listview里，意思就是这个b是第position号做好的）
        // id是b在listview Y里的第几行的位置（很明显是第2行），大部分时候position和id的值是一样的，
        // 如果需要的话，你可以自己加个log把position和id都弄出来在logcat里瞅瞅，看了之后心里才踏实
        public void onItemClick(AdapterView arg0, View arg1, int arg2,
                                long arg3) {
            Log.d("listview","："+arg2);
            mUrl=(String) mData.get(arg2).get("url");
            Log.d("url",mUrl);
            Bundle bundle=new Bundle();
            bundle.putString("url",mUrl);
            Intent intent=new Intent(getActivity(),NewsDetailActivity.class);;
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }


}
