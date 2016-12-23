package com.example.denizalp.thefirst;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.bounswe.group7.model.Topics;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by denizalp on 18/12/16.
 */

public class TopicAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<Topics> mTopicList;
    private String currentToken;
    private Activity activity;

    public TopicAdapter(Activity activity, List<Topics> topicList, String currentToken){
        this.activity = activity;
        mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mTopicList = topicList;
        this.currentToken = currentToken;
    }
    @Override
    public int getCount() {
        return mTopicList.size();
    }

    @Override
    public Object getItem(int position) {
        return mTopicList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mTopicList.get(position).getTopicId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View singleTopicView = mInflater.inflate(R.layout.activity_single_topic, null);
        Topics topic = mTopicList.get(position);
        TextView textView = (TextView) singleTopicView.findViewById(R.id.textView8);
        Button button = (Button) singleTopicView.findViewById(R.id.button38);
        Intent toTopic = new Intent(activity, ShowTopicPage.class);

        textView.setText(topic.getHeader());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toTopic.putExtra("topicId",topic.getTopicId());
                activity.startActivity(toTopic);
            }
        });
        return singleTopicView;
    }
}
