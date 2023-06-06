package com.example.questionbank25_32.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.questionbank25_32.R;
import com.example.questionbank25_32.bean.Subway;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName MetroAdapter
 * @Author 史正龙
 * @date 2021.08.07 16:01
 */
public class MetroAdapter extends BaseExpandableListAdapter {
    private List<Subway> subways;

    public MetroAdapter(List<Subway> subways) {
        this.subways = subways;
    }

    @Override
    public int getGroupCount() {
        return subways.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return subways.get(groupPosition).getSite().size();
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
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolderFu holderFu;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.metro_item_fu, parent, false);
            holderFu = new ViewHolderFu(convertView);
            convertView.setTag(holderFu);
        } else {
            holderFu = (ViewHolderFu) convertView.getTag();
        }
        Subway subway = subways.get(groupPosition);
        if (isExpanded) {
            holderFu.arrowIv.setImageResource(R.drawable.xiajiantou);
        } else {
            holderFu.arrowIv.setImageResource(R.drawable.youjiantou);
        }
        holderFu.msgTv.setText(subway.getName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderZi holderZi;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.metro_item_zi, parent, false);
            holderZi = new ViewHolderZi(convertView);
            convertView.setTag(holderZi);
        } else {
            holderZi = (ViewHolderZi) convertView.getTag();
        }
        String s = subways.get(groupPosition).getSite().get(childPosition);
        holderZi.contentTv.setText(s );
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    static
    class ViewHolderFu {
        @BindView(R.id.msg_tv)
        TextView msgTv;
        @BindView(R.id.arrow_iv)
        ImageView arrowIv;

        ViewHolderFu(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static
    class ViewHolderZi {
        @BindView(R.id.content_tv)
        TextView contentTv;

        ViewHolderZi(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
