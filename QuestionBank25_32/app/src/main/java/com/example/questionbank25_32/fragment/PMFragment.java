package com.example.questionbank25_32.fragment;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import com.example.questionbank25_32.R;
import com.example.questionbank25_32.bean.Sense;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.jetbrains.annotations.NotNull;

import java.nio.channels.Channel;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @ClassName PMFragment
 * @Author 史正龙
 * @date 2021.08.07 10:49
 */
public class PMFragment extends Fragment {
    @BindView(R.id.pm_title)
    TextView pmTitle;
    @BindView(R.id.pm_chart)
    LineChart pmChart;
    private Unbinder unbinder;
    private List<Sense> senses;
    private List<Entry> entries;
    private boolean isLoop = true;
    private List<Integer> colors;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            setData();
            return false;
        }
    });

    public PMFragment(List<Sense> senses) {
        this.senses = senses;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.pm_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pmTitle.setText("PM2.5指数");
        new Thread(new Runnable() {
            @Override
            public void run() {
                do {
                    handler.sendEmptyMessage(0);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (isLoop);
            }
        }).start();
        int importance = NotificationManager.IMPORTANCE_HIGH;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "pm";
            String channelName = "PM设置";
            createNotifi(channelId, channelName, importance);
        }
    }

    private void setData() {
        if (entries == null) {
            entries = new ArrayList<>();
            colors = new ArrayList<>();
        } else {
            entries.clear();
            colors.clear();
        }

        for (int i = 0; i < senses.size(); i++) {
            Sense sense = senses.get(i);
            entries.add(new Entry((i + 1) * 3, sense.getPm25()));
            if (sense.getPm25() > 200) {
                colors.add(Color.RED);
                sendNotifi(0, "当前PM2.5：" + sense.getPm25());
            } else {
                colors.add(Color.GREEN);
            }
        }
        LineDataSet dataSet = new LineDataSet(entries, "");
        dataSet.setDrawCircleHole(false);
        dataSet.setCircleColors(colors);
        dataSet.setLineWidth(2f);
        LineData data = new LineData(dataSet);
        setXY();
        pmChart.setData(data);
        pmChart.getDescription().setText("(S)");
        pmChart.getDescription().setTextSize(15);
        pmChart.getLegend().setEnabled(false);
        pmChart.setTouchEnabled(false);
        pmChart.invalidate();
    }

    private void setXY() {
        XAxis xAxis = pmChart.getXAxis();
        xAxis.setTextSize(15);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(5);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(15);


        pmChart.getAxisRight().setEnabled(false);
        YAxis left = pmChart.getAxisLeft();
        left.setDrawGridLines(false);
        left.setAxisMaximum(300);
        left.setAxisMinimum(0);
        left.setTextSize(15);
    }


    private void sendNotifi(int id, String msg) {
        NotificationManager manager = (NotificationManager) requireContext().getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(), "pm");
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("PM2.5")
                .setContentText(msg)
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis());
        manager.notify(id, builder.build());

    }

    private void createNotifi(String channelId, String msg, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, msg, importance);
        NotificationManager manager = (NotificationManager) requireContext().getSystemService(Context.NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isLoop = false;
        unbinder.unbind();
    }
}
