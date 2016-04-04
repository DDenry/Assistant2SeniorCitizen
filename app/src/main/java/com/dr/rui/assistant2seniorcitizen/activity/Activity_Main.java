package com.dr.rui.assistant2seniorcitizen.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.dr.rui.assistant2seniorcitizen.R;
import com.dr.rui.assistant2seniorcitizen.adapter.NewsAdapter;
import com.dr.rui.assistant2seniorcitizen.entity.News;
import com.dr.rui.assistant2seniorcitizen.util.ImageUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Activity_Main extends Activity {

    private ViewPager viewPager;
    private ArrayList<View> pagerView;

    private ImageView imageView_news;
    private ImageView imageView_alarm;
    private ImageView imageView_community;
    private ImageView imageView_profile;

    private ListView listView_news;
    private NewsAdapter newsAdapter;
    private List<News> newsList;
    private static final String GET_NEWS_URL = "";

    //
    private Handler getNewsHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //super.handleMessage(msg);
            String jsonData = (String) msg.obj;
            System.out.println(jsonData);
            //
            try {
                JSONArray jsonArray = new JSONArray(jsonData);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    String title = object.getString("title");
//                    newsList.add(new News(title,desc,time,content_url,pic_url));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity__main);
        //
        load();
        //
        load_news();
    }

    private void load() {
        //
        viewPager = (ViewPager) findViewById(R.id.viewPager_main);
        //为ViewPager绑定view
        LayoutInflater layoutInflater = getLayoutInflater();
        View view_news = layoutInflater.inflate(R.layout.tab_news, null);
        View view_alarm = layoutInflater.inflate(R.layout.tab_alarm, null);
        View view_community = layoutInflater.inflate(R.layout.tab_community, null);
        View view_profile = layoutInflater.inflate(R.layout.tab_profile, null);

        //tab_news
        imageView_news = (ImageView) view_news.findViewById(R.id.imageView_news);
        listView_news = (ListView) view_news.findViewById(R.id.listView_news);

        //tab_alarm
        imageView_alarm = (ImageView) view_alarm.findViewById(R.id.imageView_alarm);
        //tab_community
        imageView_community = (ImageView) view_community.findViewById(R.id.imageView_community);
        //tab_profile
        imageView_profile = (ImageView) view_profile.findViewById(R.id.imageView_profile);

        pagerView = new ArrayList<>();
        pagerView.add(view_news);

        //imageView绑定监听事件
        imageView_news.setOnClickListener(new imageView_OnClickListener());
        imageView_alarm.setOnClickListener(new imageView_OnClickListener());
        imageView_community.setOnClickListener(new imageView_OnClickListener());
        imageView_profile.setOnClickListener(new imageView_OnClickListener());

        //
        PagerAdapter mPagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return pagerView.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                ((ViewPager) container).removeView(pagerView.get(position));
                //super.destroyItem(container, position, object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ((ViewPager) container).addView(pagerView.get(position));
                return pagerView.get(position);
            }
        };

        //绑定适配器
        viewPager.setAdapter(mPagerAdapter);
        //
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            //OnPageSelected
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    //
                    case 0:
                        init();
                        imageView_news.setImageBitmap(ImageUtil.readBitMap(Activity_Main.this, R.drawable.dot_selected));
                        break;
                    //
                    case 1:
                        init();
                        imageView_alarm.setImageBitmap(ImageUtil.readBitMap(Activity_Main.this, R.drawable.dot_selected));
                        break;
                    //
                    case 2:
                        init();
                        imageView_community.setImageBitmap(ImageUtil.readBitMap(Activity_Main.this, R.drawable.dot_selected));
                        break;
                    //
                    case 3:
                        init();
                        imageView_profile.setImageBitmap(ImageUtil.readBitMap(Activity_Main.this, R.drawable.dot_selected));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //init()
    private void init() {
        imageView_news.setImageBitmap(ImageUtil.readBitMap(Activity_Main.this, R.drawable.dot_blue));
        imageView_alarm.setImageBitmap(ImageUtil.readBitMap(Activity_Main.this, R.drawable.dot_blue));
        imageView_community.setImageBitmap(ImageUtil.readBitMap(Activity_Main.this, R.drawable.dot_blue));
        imageView_profile.setImageBitmap(ImageUtil.readBitMap(Activity_Main.this, R.drawable.dot_blue));

    }

    //OnClickListener
    private class imageView_OnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                //
                case R.id.imageView_news:
                    init();
                    imageView_news.setImageBitmap(ImageUtil.readBitMap(Activity_Main.this, R.drawable.dot_selected));
                    viewPager.setCurrentItem(0);
                    break;
                //
                case R.id.imageView_alarm:
                    init();
                    imageView_alarm.setImageBitmap(ImageUtil.readBitMap(Activity_Main.this, R.drawable.dot_selected));
                    viewPager.setCurrentItem(1);
                    break;
                //
                case R.id.imageView_community:
                    init();
                    imageView_community.setImageBitmap(ImageUtil.readBitMap(Activity_Main.this, R.drawable.dot_selected));
                    viewPager.setCurrentItem(2);
                    break;
                //
                case R.id.imageView_profile:
                    init();
                    imageView_profile.setImageBitmap(ImageUtil.readBitMap(Activity_Main.this, R.drawable.dot_selected));
                    viewPager.setCurrentItem(3);
                    break;
            }
        }
    }

    private void load_news() {
        newsList = new ArrayList<News>();
        newsAdapter = new NewsAdapter(this, newsList);
        listView_news.setAdapter(newsAdapter);
    }
}
