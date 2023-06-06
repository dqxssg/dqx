package com.example.questionbank25_32.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.questionbank25_32.R;
import com.example.questionbank25_32.adapter.FeedAdapter;
import com.example.questionbank25_32.bean.Feed;

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFeedActivity extends AppCompatActivity {

    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.feed_list)
    ListView feedList;
    FeedAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_feed);
        ButterKnife.bind(this);
        titleTv.setText("我的意见");
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        List<Feed> feeds = LitePal.findAll(Feed.class);
        adapter = new FeedAdapter(this, feeds);
        feedList.setAdapter(adapter);

    }
}