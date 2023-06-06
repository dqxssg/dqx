package com.example.questionbank25_32.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.questionbank25_32.R;
import com.example.questionbank25_32.bean.Feed;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedbackActivity extends AppCompatActivity {

    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.feed_tv)
    TextView feedTv;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.title_et)
    EditText titleEt;
    @BindView(R.id.msg_et)
    EditText msgEt;
    @BindView(R.id.phone_et)
    EditText phoneEt;
    @BindView(R.id.feed_line)
    LinearLayout feedLine;
    @BindView(R.id.submit_btn)
    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);
        titleTv.setText("意见反馈");
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    @OnClick({R.id.feed_tv, R.id.submit_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.feed_tv:
                startActivity(new Intent(this, MyFeedActivity.class));
                break;
            case R.id.submit_btn:
                if (TextUtils.isEmpty(titleEt.getText())) {
                    Toast.makeText(this, "标题不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(msgEt.getText())) {
                    Toast.makeText(this, "标题不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(phoneEt.getText())) {
                    Toast.makeText(this, "标题不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                Feed feed = new Feed();
                feed.setMsg(String.valueOf(msgEt.getText()));
                feed.setPhone(String.valueOf(phoneEt.getText()));
                feed.setTitle(String.valueOf(titleEt.getText()));
                feed.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                if (feed.save()) {
                    Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "提交失败", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}