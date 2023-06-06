package com.example.questionbank25_32.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.questionbank25_32.MainActivity;
import com.example.questionbank25_32.R;
import com.example.questionbank25_32.adapter.MetroAdapter;
import com.example.questionbank25_32.bean.Subway;
import com.example.questionbank25_32.net.OkHttpLo;
import com.example.questionbank25_32.net.OkHttpTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MetroQueryActivity extends AppCompatActivity {

    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.feed_tv)
    TextView feedTv;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.metro_expand)
    ExpandableListView metroExpand;
    private List<Subway> subways;
    MetroAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metro_query);
        ButterKnife.bind(this);
        feedTv.setText("地铁规划");
        titleTv.setText("地铁查询");
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getMetro();
    }


    private void getMetro() {
        if (subways == null) {
            subways = new ArrayList<>();
        } else {
            subways.clear();
        }
        new OkHttpTo().setUrl("get_metro")
                .setJsonObject("UserName", "user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        subways = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<Subway>>() {
                        }.getType());

                        if (adapter == null) {
                            adapter = new MetroAdapter(subways);
                            metroExpand.setAdapter(adapter);
                        } else {
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(IOException e) {

                    }
                }).start();
    }

    @OnClick(R.id.feed_tv)
    public void onClick() {
        startActivity(new Intent(this, MetroMapActivity.class));
    }
}