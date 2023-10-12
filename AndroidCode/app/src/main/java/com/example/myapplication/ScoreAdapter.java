package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.Model.Score;

import java.util.List;

public class ScoreAdapter extends BaseAdapter {

    private Context context;
    private List<Score> scores;

    public ScoreAdapter(Context context, List<Score> scores) {
        this.context = context;
        this.scores = scores;
    }

    @Override
    public int getCount() {
        return scores.size();
    }

    @Override
    public Object getItem(int position) {
        return scores.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_item, parent,
                    false);
        }

        Score score = scores.get(position);

        TextView playerName = convertView.findViewById(R.id.playerName);
        TextView scoreValue = convertView.findViewById(R.id.scoreValue);
        TextView timeValue = convertView.findViewById(R.id.timeValue);

        playerName.setText(score.getPlayer());
        scoreValue.setText(String.valueOf(score.getScore()));
        timeValue.setText(score.getTime());

        return convertView;
    }
}
