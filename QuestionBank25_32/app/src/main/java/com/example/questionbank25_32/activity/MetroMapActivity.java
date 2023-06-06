package com.example.questionbank25_32.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.questionbank25_32.R;
import com.example.questionbank25_32.util.ImageListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MetroMapActivity extends AppCompatActivity {

    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.map_iv)
    ImageView mapIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metro_map);
        ButterKnife.bind(this);
        titleTv.setText("地铁规划");
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mapIv.setOnTouchListener(new ImageListener(mapIv));
    }
}