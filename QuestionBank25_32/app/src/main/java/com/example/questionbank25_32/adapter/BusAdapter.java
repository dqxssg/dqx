package com.example.questionbank25_32.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.questionbank25_32.R;
import com.example.questionbank25_32.bean.Bus;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName BusAdapter
 * @Author 史正龙
 * @date 2021.08.06 17:40
 */
public class BusAdapter extends BaseExpandableListAdapter {
    private String[] arr = {"一号站台", "二号站台"};
    private Map<String, List<Bus>> map;

    public BusAdapter(Map<String, List<Bus>> map) {
        this.map = map;
    }

    @Override
    public int getGroupCount() {
        return map.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return map.get(arr[groupPosition]).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolderFu holderFu;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.bus_item_fu, parent, false);
            holderFu = new ViewHolderFu(convertView);
            convertView.setTag(holderFu);
        } else {
            holderFu = (ViewHolderFu) convertView.getTag();
        }
        if (isExpanded) {
            holderFu.arrowIv.setImageResource(R.drawable.jiantou2);
        } else {
            holderFu.arrowIv.setImageResource(R.drawable.jiantou1);
        }
        holderFu.stationTv.setText(arr[groupPosition]);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderZi holderZi;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.bus_item_zi, parent, false);
            holderZi = new ViewHolderZi(convertView);
            convertView.setTag(holderZi);
        } else {
            holderZi = (ViewHolderZi) convertView.getTag();
        }
        Bus bus = map.get(arr[groupPosition]).get(childPosition);
        holderZi.busId.setText(bus.getBus() == 1 ? "一号公交车" : "二号公交车");
        holderZi.distanceTv.setText(bus.getDistance() + "米");
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    static
    class ViewHolderFu {
        @BindView(R.id.arrow_iv)
        ImageView arrowIv;
        @BindView(R.id.station_tv)
        TextView stationTv;

        ViewHolderFu(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static
    class ViewHolderZi {
        @BindView(R.id.bus_id)
        TextView busId;
        @BindView(R.id.distance_tv)
        TextView distanceTv;

        ViewHolderZi(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
