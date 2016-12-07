package com.example.denizalp.thefirst;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.bounswe.group7.api.client.CommentServiceClient;
import com.bounswe.group7.api.client.ReviewServiceClient;
import com.bounswe.group7.api.client.UserServiceClient;
import com.bounswe.group7.model.Comments;
import com.bounswe.group7.model.Reviews;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by denizalp on 06/12/16.
 */

public class ReviewAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<Reviews> mReviewList;
    private String currentToken;
    private Activity activity;

    public ReviewAdapter(Activity activity, List<Reviews> reviewsList, String currentToken){
        this.activity = activity;
        mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mReviewList = reviewsList;
        this.currentToken = currentToken;
    }

    @Override
    public int getCount() {
        return mReviewList.size();
    }

    @Override
    public Object getItem(int position) {
        return mReviewList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mReviewList.get(position).getReviewId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View reviewView;
        reviewView = mInflater.inflate(R.layout.activity_review_listview,null);
        TextView reviewText = (TextView) reviewView.findViewById(R.id.reviewText);
        Button deleteButton = (Button) reviewView.findViewById(R.id.deleteReview);

        Reviews review = mReviewList.get(position);
        String token = currentToken;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        UserServiceClient userServiceClient = new UserServiceClient(token);
        try{
            Long currentUserId = userServiceClient.getLoggedInUser().getId();
            String reviewDate = dateFormat.format(review.getDateCreated());
            String reviewer = userServiceClient.getUser(review.getReviewerId()).getUsername();
            String reviewsText = review.getText();
            String putReview = reviewer+"\t\t          "+reviewDate+"\n\n"+reviewsText;
            reviewText.setText(putReview);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v){
                    ReviewServiceClient reviewServiceClient = new ReviewServiceClient(token);
                    Long deletedReviewId = review.getReviewId();
                    try {
                        mReviewList.remove(review);
                        reviewServiceClient.deleteReview(deletedReviewId);
                        System.out.println("Review has been deleted successfully.");
                        activity.recreate();
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
            );

            if(currentUserId != review.getReviewerId()) {
                deleteButton.setOnClickListener(null);
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }

        return reviewView;
    }
}
