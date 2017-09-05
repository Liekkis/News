package com.example.yangli.news.tool;


import android.graphics.Bitmap;
import android.util.Log;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.yangli.news.R;
import com.example.yangli.news.entity.News;
import com.example.yangli.news.tool.HttpUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import static com.example.yangli.news.tool.getHttpBitmap.getHttpBitmap;

public class  Get  {

    public static final String APPKEY = "cc53bb5590f9dac9";// 你的appkey
    public static final String URL = "http://api.jisuapi.com/news/get";
    public static List<Map<String, Object>> Newslist;
  //  public static List<Map<String, Object>> Newslist = new ArrayList<Map<String, Object>>();
    public static final int num = 5;// 数量 默认10，最大40
    public static News news=new News();
    public static String channel;// utf8  新闻频道(头条,财经,体育,娱乐,军事,教育,科技,NBA,股票,星座,女性,健康,育儿)
    public  Get(String channel){
        this.channel=channel;
    }

    public   List<Map<String, Object>>  NewGet() throws Exception {
        Newslist=new ArrayList<>();
        String result = null;
        String url = URL + "?channel=" + URLEncoder.encode(channel, "utf-8") + "&num=" + num + "&appkey=" + APPKEY;

        try {
            result = HttpUtil.sendGet(url, "utf-8");
            JSONObject json = JSONObject.fromObject(result);
            if (json.getInt("status") != 0) {
                System.out.println(json.getString("msg"));
            } else {
                JSONObject resultarr = (JSONObject) json.opt("result");
                String channel = resultarr.getString("channel");
                String num = resultarr.getString("num");
                System.out.println(channel + " " + num);
                JSONArray list = resultarr.optJSONArray("list");
                for (int i = 0; i < list.size(); i++) {
                    JSONObject obj = (JSONObject) list.opt(i);
                    String title = obj.getString("title");
                    String time = obj.getString("time");
                    String src = obj.getString("src");
                    String category = obj.getString("category");
                    String pic = obj.getString("pic");
                    String content = obj.getString("content");
                    String url1 = obj.getString("url");
                    String weburl = obj.getString("weburl");
//                    System.out.println(title + " " + time + " " + src + " " + category + " " + pic + " " + content + " "
//                            + url1 + " " + weburl);
                     news.Title = obj.getString("title");
                  //  Log.d("CESHI",news.Title);
                    news.Time = obj.getString("time");
                    news.Src = obj.getString("src");
                    news.category = obj.getString("category");
                    if(obj.getString("pic")==null){
                        news.Pic = getBitmap(obj.getString(null));
                    }else {
                        news.Pic = getBitmap(obj.getString("pic"));
                    }
                    news.Content = obj.getString("content");
                    news.Url = obj.getString("url");
                   news.Weburl = obj.getString("weburl");
                   Newslist.add(getData(news));

                    Log.d("CESHI",Integer.toString(Newslist.size()));
                 //   Log.d("CESHI",obj.getString("pic"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Newslist;

    }

//    public void close(){
//        Newslist=null;
//    }
    public void close(){
        Newslist.clear();
    }

    public   Map<String, Object> getData(News news) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", news.Title);
        map.put("img", R.drawable.video);
        map.put("time",news.Time);
        map.put("src",news.Src);
        map.put("category",news.category);
        map.put("pic",news.Pic);
        map.put("Content",news.Content);
        map.put("url",news.Url);
        map.put("weburl",news.Weburl);
        return map;
    }
    public Bitmap getBitmap(String url){


        if(null==url){

           // Log.d("url","null");
        }else {
            //得到可用的图片
            Bitmap bitmap = getHttpBitmap(url);
            return bitmap;
        }//显示图片
        return null;
    }
}