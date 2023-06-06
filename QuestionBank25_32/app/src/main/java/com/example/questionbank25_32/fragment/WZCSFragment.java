package com.example.questionbank25_32.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.questionbank25_32.R;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @ClassName CFWZFragment
 * @Author 史正龙
 * @date 2021.08.06 14:54
 */
public class WZCSFragment extends Fragment {
    @BindView(R.id.chart_title)
    TextView chartTitle;
    @BindView(R.id.wzcs_chart)
    HorizontalBarChart wzcsChart;
    private Unbinder unbinder;
    private int a, b, c;
    private float total;
    private List<BarEntry> barEntries;
    private List<Integer> colors;

    public WZCSFragment(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.wzcs_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        chartTitle.setText("违章车辆的违章次数占比分布图统计");
        setData();
    }

    private void setData() {
        if (barEntries == null) {
            barEntries = new ArrayList<>();
            colors = new ArrayList<>();
        }

        total = (float) (a + b + c);
        barEntries.add(new BarEntry(0, (float) c / total));
        barEntries.add(new BarEntry(1, (float) b / total));
        barEntries.add(new BarEntry(2, (float) a / total));
        colors.add(Color.parseColor("#aa4643"));
        colors.add(Color.parseColor("#4572a7"));
        colors.add(Color.GREEN);
        BarDataSet dataSet = new BarDataSet(barEntries, "");
        dataSet.setColors(colors);
        BarData data = new BarData(dataSet);
        data.setBarWidth(0.6f);
        data.setValueTextColor(Color.RED);
        data.setValueTextSize(25);
        data.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return new DecimalFormat("0.00").format(value * 100) + "%";
            }
        });
        setXY();
        wzcsChart.setData(data);
        wzcsChart.getDescription().setEnabled(false);
        wzcsChart.getLegend().setEnabled(false);
        wzcsChart.setTouchEnabled(false);
        wzcsChart.invalidate();
    }

    private void setXY() {
        XAxis xAxis = wzcsChart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(15);
        xAxis.setLabelCount(3);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"1-2条违章", "3-5条违章", "5条以上违章"}));

        wzcsChart.getAxisLeft().setEnabled(false);
        YAxis right = wzcsChart.getAxisRight();
        right.setLabelCount(8);
        right.setAxisMinimum(0f);
        right.setTextSize(15);
        right.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return new DecimalFormat("0.00").format(value * 100) + "%";
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
