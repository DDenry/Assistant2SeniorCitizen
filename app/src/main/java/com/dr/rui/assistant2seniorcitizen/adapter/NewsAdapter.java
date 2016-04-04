package com.dr.rui.assistant2seniorcitizen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dr.rui.assistant2seniorcitizen.R;
import com.dr.rui.assistant2seniorcitizen.entity.News;
import com.dr.rui.assistant2seniorcitizen.util.HttpUtil;

import java.util.List;

/**
 * Created by rui on 2016/4/4.
 */
public class NewsAdapter extends BaseAdapter {
    private Context context;
    private List<News> newsList;

    public NewsAdapter(Context context, List<News> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public News getItem(int position) {
        return newsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.news_item, null);
        }
        TextView textView_news_title = (TextView) convertView.findViewById(R.id.textView_news_title);
        TextView textView_news_description = (TextView) convertView.findViewById(R.id.textView_news_description);
        TextView textView_news_time = (TextView) convertView.findViewById(R.id.textView_news_time);
        ImageView imageView_news_pic = (ImageView) convertView.findViewById(R.id.imageView_news_pic);

        News news = newsList.get(position);
        textView_news_title.setText(news.getTitle());
        textView_news_description.setText(news.getDesc());
        textView_news_time.setText(news.getTitle());
        //
        String pic_url = news.getPic_url();
        HttpUtil.setPicBitmap(imageView_news_pic, pic_url);
        return convertView;
    }
}
