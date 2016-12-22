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

import com.bounswe.group7.model.QuizProgress;
import com.bounswe.group7.model.TopicPacks;

import java.util.List;

/**
 * Created by denizalp on 21/12/16.
 */

public class QuizProgressAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<QuizProgress> mList;
    private String currentToken;
    private Activity activity;

    public QuizProgressAdapter(Activity activity, List<QuizProgress> mList, String currentToken){
        this.activity = activity;
        this.mList = mList;
        this.currentToken = currentToken;
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
        return mList.get(position).getTopicPack().getTopicPackId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View progressView = mInflater.inflate(R.layout.activity_single_quiz_progress, null);
        TextView textView = (TextView) progressView.findViewById(R.id.progressTopicPackName);
        TextView textView1 = (TextView) progressView.findViewById(R.id.theProgress);
        Button button = (Button) progressView.findViewById(R.id.showProgressDetail);
        QuizProgress quizProgress = mList.get(position);
        TopicPacks topicPack = quizProgress.getTopicPack();
        Double progress = quizProgress.getTotalProgress();
        String topicPackName = topicPack.getName();
        String totalProgress = String.format("Total progress: %.2f%%",progress);

        textView.setText(topicPackName);
        System.out.println(totalProgress);
        textView1.setText(totalProgress);

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(activity, QuizProgressDetailedPage.class);
                intent.putExtra("position",position);
                intent.putExtra("token",currentToken);
                activity.startActivity(intent);
            }
        }
        );

        return progressView;
    }
}
