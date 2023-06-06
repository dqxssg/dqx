package com.example.questionbank25_32.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.questionbank25_32.R;
import com.example.questionbank25_32.bean.Sense;
import com.example.questionbank25_32.net.OkHttpLo;
import com.example.questionbank25_32.net.OkHttpTo;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LifeAssistantActivity extends AppCompatActivity {

    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.pm_tv)
    TextView pmTv;
    @BindView(R.id.wd_tv)
    TextView wdTv;
    @BindView(R.id.sd_tv)
    TextView sdTv;
    @BindView(R.id.sun_status)
    TextView sunStatus;
    @BindView(R.id.sun_msg)
    TextView sunMsg;
    @BindView(R.id.run_status)
    TextView runStatus;
    @BindView(R.id.run_msg)
    TextView runMsg;
    private List<Sense> senses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_assistant);
        ButterKnife.bind(this);
        titleTv.setText("生活助手");
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSense();
    }

    private void getSense() {
        if (senses == null) {
            senses = new ArrayList<>();
        }
        new OkHttpTo().setUrl("get_all_sense")
                .setLoop(true)
                .setTime(3000)
                .setJsonObject("UserName", "user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        pmTv.setText(jsonObject.optString("pm25"));
                        wdTv.setText(jsonObject.optString("temperature"));
                        sdTv.setText(jsonObject.optString("humidity"));
                        senses.add(new Gson().fromJson(jsonObject.toString(), Sense.class));
                        setMsg();
                    }

                    @Override
                    public void onFailure(IOException e) {

                    }
                }).start();
    }

    private void setMsg() {
        for (int i = 0; i < senses.size(); i++) {
            Sense sense = senses.get(i);
            int pm25 = sense.getPm25();
            if (pm25 >= 0 && pm25 < 100) {
                runStatus.setText("良好");
                runMsg.setText("气象条件会对晨练影响不大");
            } else if (pm25 >= 100 && pm25 < 200) {
                runStatus.setText("轻度");
                runMsg.setText("受天气影响，较不宜晨练");
            } else if (pm25 >= 200 && pm25 < 300) {
                runStatus.setText("重度");
                runStatus.setTextColor(Color.RED);
                runMsg.setText("减少外出，出行注意戴口罩");
            } else {
                runStatus.setText("爆表");
                runStatus.setTextColor(Color.RED);
                runMsg.setText("停止一切外出活动");
            }

            int gz = sense.getIllumination();
            if (gz > 0 && gz < 1500) {
                sunStatus.setText("非常弱");
                sunMsg.setText("您无需担心紫外线");
            } else if (pm25 >= 1500 && pm25 <= 3000) {
                sunStatus.setText("弱");
                sunMsg.setText("外出适当涂抹低倍数防晒霜");
            } else {
                sunStatus.setText("强");
                sunStatus.setTextColor(Color.RED);
                sunMsg.setText("外出需要涂抹中倍数防晒霜");
            }
        }
    }

}