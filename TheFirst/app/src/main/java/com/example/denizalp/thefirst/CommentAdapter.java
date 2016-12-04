package com.example.denizalp.thefirst;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.bounswe.group7.api.client.CommentServiceClient;
import com.bounswe.group7.api.client.UserServiceClient;
import com.bounswe.group7.model.Comments;
import com.bounswe.group7.model.VotedComments;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by denizalp on 04/12/16.
 */

public class CommentAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<Comments> mCommentList;
    private String currentToken;

    public CommentAdapter(Activity activity, List<Comments> commentList, String currentToken){
        mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mCommentList = commentList;
        this.currentToken = currentToken;
    }

    @Override
    public int getCount() {
        return mCommentList.size();
    }

    @Override
    public Object getItem(int position) {
        return mCommentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mCommentList.get(position).getCommentId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View commentView;
        commentView = mInflater.inflate(R.layout.activity_single_comment_view, null);
        TextView usernameText = (TextView) commentView.findViewById(R.id.commentor);
        TextView dateText = (TextView) commentView.findViewById(R.id.dateComment);
        TextView commentText = (TextView) commentView.findViewById(R.id.commentText);
        TextView ratingText = (TextView) commentView.findViewById(R.id.commentRating);
        Button plusButton = (Button) commentView.findViewById(R.id.plusButton);
        Button minusButton = (Button) commentView.findViewById(R.id.minusButton);


        Comments comment = mCommentList.get(position);
        //SharedPreferences sharPref = getSharedPreferences("tokenInfo",MODE_PRIVATE);
        String token = currentToken;
        UserServiceClient userServiceClient = new UserServiceClient(token);
        try {
            String commentorUsername = userServiceClient.getUser(comment.getUserId()).getUsername();
            usernameText.setText(commentorUsername);
            dateText.setText(comment.getDateCreated().toString());
            commentText.setText(comment.getText());
            String rating = ""+comment.getRate();
            ratingText.setText(rating);

            //plus button listener make rate = 1 and call client
            plusButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v){
                    CommentServiceClient commentServiceClient = new CommentServiceClient(token);
                    VotedComments toBeVoted = new VotedComments();
                    toBeVoted.setUserId(comment.getUserId());
                    toBeVoted.setCommentId(comment.getCommentId());
                    toBeVoted.setRate(1);
                    try {
                       boolean commented = commentServiceClient.voteComment(toBeVoted);
                        if(commented) System.out.println("You have successfully voted the comment.");
                        List<Comments> afterVoted = commentServiceClient.getTopicComments(comment.getTopicId());
                        Comments votedComment = afterVoted.get(position);
                        String rating = ""+votedComment.getRate();
                        ratingText.setText(rating);
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
            );

            minusButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v){
                    CommentServiceClient commentServiceClient = new CommentServiceClient(token);
                    VotedComments toBeVoted = new VotedComments();
                    toBeVoted.setUserId(comment.getUserId());
                    toBeVoted.setCommentId(comment.getCommentId());
                    toBeVoted.setRate(-1);
                    try {
                        boolean commented = commentServiceClient.voteComment(toBeVoted);
                        if(commented) System.out.println("You have successfully voted the comment.");
                        List<Comments> afterVoted = commentServiceClient.getTopicComments(comment.getTopicId());
                        Comments votedComment = afterVoted.get(position);
                        String rating = ""+votedComment.getRate();
                        ratingText.setText(rating);
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
            );
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return commentView;
    }
}
