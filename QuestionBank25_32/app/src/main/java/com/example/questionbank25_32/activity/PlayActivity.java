package com.example.questionbank25_32.activity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.questionbank25_32.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayActivity extends AppCompatActivity {

    @BindView(R.id.videoView)
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        ButterKnife.bind(this);
        int bh = getIntent().getIntExtra("Bh", 0);
        switch (bh) {
            case 0:
                videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.car1));
                break;
            case 1:
                videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.car2));
                break;
            case 2:
                videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.car3));
                break;
            case 3:
                videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.car4));
                break;
        }
        videoView.start();
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                finish();
            }
        });
    }
}