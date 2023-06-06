package com.example.questionbank25_32.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @ClassName GZFragment
 * @Author 史正龙
 * @date 2021.08.07 10:49
 */
public class GZFragment extends Fragment {
    @BindView(R.id.gz_title)
    TextView gzTitle;
    @BindView(R.id.gz_chart)
    LineChart gzChart;
    private Unbinder unbinder;
    private List<Sense> senses;
    private List<Entry> entries;
    private boolean isLoop = true;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            setData();
            return false;
        }
    });

    public GZFragment(List<Sense> senses) {
        this.senses = senses;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.gz_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        gzTitle.setText("光照指数");
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
    }

    private void setData() {
        if (entries == null) {
            entries = new ArrayList<>();
        } else {
            entries.clear();
        }

        for (int i = 0; i < senses.size(); i++) {
            Sense sense = senses.get(i);
            entries.add(new Entry((i + 1) * 3, sense.getIllumination()));
        }
        LineDataSet dataSet = new LineDataSet(entries, "");
        dataSet.setDrawCircleHole(false);
        dataSet.setLineWidth(2f);
        LineData data = new LineData(dataSet);
        setXY();
        gzChart.setData(data);
        gzChart.getDescription().setText("(S)");
        gzChart.getDescription().setTextSize(15);
        gzChart.getLegend().setEnabled(false);
        gzChart.setTouchEnabled(false);
        gzChart.invalidate();
    }


    private void setXY() {
        XAxis xAxis = gzChart.getXAxis();
        xAxis.setTextSize(15);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(5);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(15);


        gzChart.getAxisRight().setEnabled(false);
        YAxis left = gzChart.getAxisLeft();
        left.setDrawGridLines(false);
        left.setAxisMaximum(6000);
        left.setAxisMinimum(0);
        left.setTextSize(15);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isLoop = false;
        unbinder.unbind();
    }
}
