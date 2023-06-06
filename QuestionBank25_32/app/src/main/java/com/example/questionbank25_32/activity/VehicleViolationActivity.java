package com.example.questionbank25_32.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.questionbank25_32.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VehicleViolationActivity extends AppCompatActivity {

    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.iv_image1)
    ImageView ivImage1;
    @BindView(R.id.iv_tv1)
    TextView ivTv1;
    @BindView(R.id.iv_image2)
    ImageView ivImage2;
    @BindView(R.id.iv_tv2)
    TextView ivTv2;
    @BindView(R.id.iv_image3)
    ImageView ivImage3;
    @BindView(R.id.iv_tv3)
    TextView ivTv3;
    @BindView(R.id.iv_image4)
    ImageView ivImage4;
    @BindView(R.id.iv_tv4)
    TextView ivTv4;
    @BindView(R.id.iv_image5)
    ImageView ivImage5;
    @BindView(R.id.iv_tv5)
    TextView ivTv5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_violation);
        ButterKnife.bind(this);
        titleTv.setText("车辆违章");
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Glide.with(this).load(R.raw.car1).into(ivImage1);
        Glide.with(this).load(R.raw.car2).into(ivImage2);
        Glide.with(this).load(R.raw.car3).into(ivImage3);
        Glide.with(this).load(R.raw.car4).into(ivImage4);
    }

    @OnClick({R.id.iv_image1, R.id.iv_image2, R.id.iv_image3, R.id.iv_image4})
    public void onClick(View view) {
        Intent intent = new Intent(this, PlayActivity.class);
        switch (view.getId()) {
            case R.id.iv_image1:
                intent.putExtra("Bh", 0);
                break;
            case R.id.iv_image2:
                intent.putExtra("Bh", 1);
                break;
            case R.id.iv_image3:
                intent.putExtra("Bh", 2);
                break;
            case R.id.iv_image4:
                intent.putExtra("Bh", 3);
                break;
        }
        startActivity(intent);
    }
}