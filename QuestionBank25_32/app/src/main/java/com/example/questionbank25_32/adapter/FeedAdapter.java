package com.example.questionbank25_32.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.questionbank25_32.R;
import com.example.questionbank25_32.bean.Feed;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName FeedAdapter
 * @Author 史正龙
 * @date 2021.08.07 15:23
 */
public class FeedAdapter extends ArrayAdapter<Feed> {
    public FeedAdapter(@NonNull Context context, @NonNull List<Feed> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.feed_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Feed feed = getItem(position);
        holder.titleItem.setText("标题：" + feed.getTitle());
        holder.timeItem.setText("提交时间：" + feed.getTime());
        holder.backMsg.setText("回复内容：" + "---");
        holder.backTime.setText("回复时间：" + "---");
        return convertView;
    }

    static
    class ViewHolder {
        @BindView(R.id.title_item)
        TextView titleItem;
        @BindView(R.id.time_item)
        TextView timeItem;
        @BindView(R.id.back_msg)
        TextView backMsg;
        @BindView(R.id.back_time)
        TextView backTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
