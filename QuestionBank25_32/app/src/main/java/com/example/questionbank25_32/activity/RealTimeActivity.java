package com.example.questionbank25_32.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.questionbank25_32.R;
import com.example.questionbank25_32.adapter.PagerAdapter;
import com.example.questionbank25_32.bean.Sense;
import com.example.questionbank25_32.fragment.GZFragment;
import com.example.questionbank25_32.fragment.PMFragment;
import com.example.questionbank25_32.fragment.WDFragment;
import com.example.questionbank25_32.net.OkHttpLo;
import com.example.questionbank25_32.net.OkHttpTo;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RealTimeActivity extends AppCompatActivity {

    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.real_line)
    LinearLayout realLine;
    private List<Fragment> fragments;
    private List<Sense> senses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_time);
        ButterKnife.bind(this);
        titleTv.setText("实时环境");
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSense();
        initPager();
    }

    private void getSense() {
        if (senses == null) {
            senses = new ArrayList<>();
        }
        new OkHttpTo().setUrl("get_all_sense")
                .setJsonObject("UserName", "user1")
                .setTime(3000)
                .setLoop(true)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        senses.add(new Gson().fromJson(jsonObject.toString(), Sense.class));
                    }

                    @Override
                    public void onFailure(IOException e) {

                    }
                }).start();
    }

    private void initPager() {
        fragments = new ArrayList<>();
        fragments.add(new PMFragment(senses));
        fragments.add(new WDFragment(senses));
        fragments.add(new GZFragment(senses));
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), fragments));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < realLine.getChildCount(); i++) {
                    ImageView imageView = (ImageView) realLine.getChildAt(i);
                    if (i == position) {
                        imageView.setImageResource(R.drawable.bg_shape);
                    } else {
                        imageView.setImageResource(R.drawable.bg_shape1);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        for (int i = 0; i < fragments.size(); i++) {
            ImageView imageView = new ImageView(this);
            if (i == 0) {
                imageView.setImageResource(R.drawable.bg_shape);
            } else {
                imageView.setImageResource(R.drawable.bg_shape1);
            }
            imageView.setLayoutParams(new ViewGroup.LayoutParams(20, 20));
            realLine.addView(imageView);
        }
    }
}