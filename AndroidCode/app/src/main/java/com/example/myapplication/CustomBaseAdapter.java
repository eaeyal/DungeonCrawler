package com.example.myapplication;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.example.myapplication.Model.Score;

import java.util.List;

public class CustomBaseAdapter extends BaseAdapter {
    Context context;
    String[] leaderboardList = GameContext.getInstance().getLeaderboard().getScores();
    LayoutInflater inflater;
    public CustomBaseAdapter(Context ctx, String[] leaderboardList) {
        this.context = ctx;
        this.leaderboardList = leaderboardList;
        inflater = LayoutInflater.from(ctx);
    }
    @Override
    public int getCount() {
        return leaderboardList.length;
    }
    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        convertView = inflater.inflate(R.layout.activity_custom_list_view, null);
        TextView txtView = (TextView) convertView.findViewById(R.id.textView);

        txtView.setText(leaderboardList[position]);
        return convertView;

    }
}
