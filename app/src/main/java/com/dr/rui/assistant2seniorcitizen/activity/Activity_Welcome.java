package com.dr.rui.assistant2seniorcitizen.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.dr.rui.assistant2seniorcitizen.R;
import com.dr.rui.assistant2seniorcitizen.util.ImageUtil;

import java.util.ArrayList;

public class Activity_Welcome extends Activity {

    /***
     * ViewPager
     */
    private ViewPager viewPager;

    /***
     * ArrayList
     */
    private ArrayList<View> pagerView;

    //
    private ImageView imageView_pic_1;
    private ImageView imageView_pic_2;
    private ImageView imageView_pic_3;

    private ImageView imageView_dot_1;
    private ImageView imageView_dot_2;
    private ImageView imageView_dot_3;

    private ImageButton imageButton_join;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity__welcome);

        //判断程序是否是第一次启动
        SharedPreferences sharedPreferences = this.getSharedPreferences("share", MODE_PRIVATE);
        boolean isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean("isFirstRun", true);
        editor.commit();

        //第一次启动
        if (isFirstRun) {
            Log.d("debug", "第一次运行");
            //加载欢迎界面
            load();
            //
            //editor.putBoolean("isFirstRun", false);
            //editor.commit();
        } else {
            //
            Log.d("debug", "不是第一次运行");
        }

    }

    private void load() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        //
        LayoutInflater inflater = getLayoutInflater();
        View view_1 = inflater.inflate(R.layout.fragment_welcome_pic_1, null);
        View view_2 = inflater.inflate(R.layout.fragment_welcome_pic_2, null);
        View view_3 = inflater.inflate(R.layout.fragment_welcome_pic_3, null);

        //
        imageView_pic_1 = (ImageView) view_1.findViewById(R.id.imageView_pic_1);
        imageView_pic_2 = (ImageView) view_2.findViewById(R.id.imageView_pic_2);
        imageView_pic_3 = (ImageView) view_3.findViewById(R.id.imageView_pic_3);

        imageView_dot_1 = (ImageView) view_1.findViewById(R.id.imageView_dot_1);
        imageView_dot_2 = (ImageView) view_2.findViewById(R.id.imageView_dot_2);
        imageView_dot_3 = (ImageView) view_3.findViewById(R.id.imageView_dot_3);

        imageView_pic_1.setImageBitmap(ImageUtil.readBitMap(Activity_Welcome.this, R.drawable.taiji_panda));
        imageView_pic_2.setImageBitmap(ImageUtil.readBitMap(Activity_Welcome.this, R.drawable.taiji_panda));
        imageView_pic_3.setImageBitmap(ImageUtil.readBitMap(Activity_Welcome.this, R.drawable.taiji_panda));

        imageButton_join = (ImageButton) view_3.findViewById(R.id.imageButton_join);
        //跳转
        imageButton_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Welcome.this, Activity_Main.class);
                startActivity(intent);
                Activity_Welcome.this.finish();
            }
        });
        //
        pagerView = new ArrayList<View>();
        pagerView.add(view_1);
        pagerView.add(view_2);
        pagerView.add(view_3);

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
    }
}
