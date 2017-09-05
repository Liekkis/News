package com.example.yangli.news.entity;

import android.graphics.Bitmap;

import com.example.yangli.news.R;

/**
 * Created by yangli on 17-8-29.
 */
//返回的数据
//channel 	string 	频道
//        num 	int 	数量
//        title 	string 	标题
//        time 	string 	时间
//        src 	string 	来源
//        category 	string 	分类
//        pic 	string 	图片
//        content 	string 	内容
//        url 	string 	原文手机网址
//        weburl 	string 	原文PC网址
public class News {
    public String Channel;
    public String Title;
    public String Time;
    public String Src;
    public String category;
    public Bitmap Pic;
    public String Content;
    public String Url;
    public String Weburl;
}
