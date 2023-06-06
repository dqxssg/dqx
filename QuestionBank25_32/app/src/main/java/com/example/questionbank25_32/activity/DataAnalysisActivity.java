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
import com.example.questionbank25_32.bean.Peccancy;
import com.example.questionbank25_32.fragment.CFWZFragment;
import com.example.questionbank25_32.fragment.WZCSFragment;
import com.example.questionbank25_32.net.OkHttpLo;
import com.example.questionbank25_32.net.OkHttpTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DataAnalysisActivity extends AppCompatActivity {

    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.data_line)
    LinearLayout dataLine;
    private List<Peccancy> peccancies, yes, no;
    private Map<String, Integer> map;
    private int a, b, c;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_analysis);
        ButterKnife.bind(this);
        titleTv.setText("数据分析");
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        fragments = new ArrayList<>();
        getPeccancy();
    }


    private void getPeccancy() {
        if (peccancies == null) {
            peccancies = new ArrayList<>();
        }

        new OkHttpTo().setUrl("get_peccancy")
                .setJsonObject("UserName", "user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        peccancies = new Gson().fromJson(jsonArray.toString(), new TypeToken<List<Peccancy>>() {
                        }.getType());
                        setList();
                    }

                    @Override
                    public void onFailure(IOException e) {

                    }
                }).start();
    }


    private void setList() {
        if (peccancies != null) {
            yes = new ArrayList<>();
            no = new ArrayList<>();
            map = new HashMap<>();
        }

        for (int i = 0; i < peccancies.size(); i++) {
            Peccancy peccancy = peccancies.get(i);
            if (peccancy.getPaddr().length() == 0) {
                no.add(peccancy);
            } else {
                yes.add(peccancy);
            }
        }

        for (int i = 0; i < yes.size(); i++) {
            String carnumber = yes.get(i).getCarnumber();
            Integer count = map.get(carnumber);
            map.put(carnumber, (count == null) ? 1 : count + 1);
        }

        for (Integer count : map.values()) {
            if (count > 5) {
                a++;
            } else if (count <= 5 && count >= 3) {
                b++;
            } else {
                c++;
            }
        }
        initPager();
    }

    private void initPager() {
        fragments.add(new CFWZFragment(yes, map));
        fragments.add(new WZCSFragment(a, b, c));
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), fragments));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dataLine.getChildCount(); i++) {
                    ImageView imageView = (ImageView) dataLine.getChildAt(i);
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
            imageView.setLayoutParams(new ViewGroup.LayoutParams(50, 50));
            dataLine.addView(imageView);
        }
    }
}