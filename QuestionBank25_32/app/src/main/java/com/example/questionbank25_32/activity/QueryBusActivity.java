package com.example.questionbank25_32.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.questionbank25_32.R;
import com.example.questionbank25_32.adapter.BusAdapter;
import com.example.questionbank25_32.bean.Bus;
import com.example.questionbank25_32.net.OkHttpLo;
import com.example.questionbank25_32.net.OkHttpTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QueryBusActivity extends AppCompatActivity {

    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.bus_expand)
    ExpandableListView busExpand;
    private List<Bus> buses1, buses2;
    private BusAdapter adapter;
    private Map<String, List<Bus>> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_bus);
        ButterKnife.bind(this);
        titleTv.setText("公交查询");
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getDistance();
    }


    private void getDistance() {
        if (buses1 == null) {
            buses1 = new ArrayList<>();
            buses2 = new ArrayList<>();
            map = new HashMap<>();
        } else {
            buses1.clear();
            buses2.clear();
            map.clear();
        }
        new OkHttpTo().setUrl("get_bus_stop_distance")
                .setJsonObject("UserName", "user1")
                .setLoop(true)
                .setTime(3000)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        buses1 = new Gson().fromJson(jsonObject.optJSONArray("中医院站").toString(), new TypeToken<List<Bus>>() {
                        }.getType());

                        buses1.sort(new Comparator<Bus>() {
                            @Override
                            public int compare(Bus o1, Bus o2) {
                                return o1.getDistance() - o2.getDistance();
                            }
                        });
                        map.put("一号站台", buses1);

                        buses2 = new Gson().fromJson(jsonObject.optJSONArray("联想大厦站").toString(), new TypeToken<List<Bus>>() {
                        }.getType());

                        buses2.sort(new Comparator<Bus>() {
                            @Override
                            public int compare(Bus o1, Bus o2) {
                                return o1.getDistance() - o2.getDistance();
                            }
                        });

                        map.put("二号站台", buses2);

                        if (adapter == null) {
                            adapter = new BusAdapter(map);
                            busExpand.setAdapter(adapter);
                        } else {
                            adapter.notifyDataSetChanged();
                        }

                    }

                    @Override
                    public void onFailure(IOException e) {

                    }
                }).start();
    }
}