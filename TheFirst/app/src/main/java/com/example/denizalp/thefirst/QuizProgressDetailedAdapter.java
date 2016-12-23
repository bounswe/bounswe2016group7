package com.example.denizalp.thefirst;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bounswe.group7.model.Topics;

import java.util.List;

/**
 * Created by denizalp on 22/12/16.
 */

public class QuizProgressDetailedAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<Topics> mList;
    private String token;
    private Activity activity;

    public QuizProgressDetailedAdapter(Activity activity, List<Topics> list, String token){
        this.activity = activity;
        mList = list;
        this.token = token;
        mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mList.get(position).getTopicId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View detailedView = mInflater.inflate(R.layout.activity_single_quiz_progress_detailed, null);
        TextView textView = (TextView) detailedView.findViewById(R.id.textView10);
        TextView textView1 = (TextView) detailedView.findViewById(R.id.textView11);
        Topics topic = mList.get(position);
        String topicName = topic.getHeader();
        String topicScore = String.format("Score: %.2f",topic.getScore());
        textView.setText(topicName);
        textView1.setText(topicScore);
        return detailedView;
    }
}
