package com.example.denizalp.thefirst;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bounswe.group7.api.client.ReviewServiceClient;
import com.bounswe.group7.api.client.UserServiceClient;
import com.bounswe.group7.model.Reviews;
import com.bounswe.group7.model.Users;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReviewPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_page);
        try
        {
            Intent intent = getIntent();
            Long userID = intent.getLongExtra("reviewedID", 0);
            SharedPreferences sharedPref = getSharedPreferences("tokenInfo", MODE_PRIVATE);
            String currentToken = sharedPref.getString("currentToken", "");
            UserServiceClient userServiceClient = new UserServiceClient(currentToken);
            Users current = userServiceClient.getLoggedInUser();
            ReviewServiceClient reviewServiceClient = new ReviewServiceClient(currentToken);
            ArrayList<Reviews> reviewsList= (ArrayList<Reviews>) reviewServiceClient.getUserReviews(userID);
           // ArrayList<String> reviewStringList = new ArrayList<String>();
           // DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
          /*  for(Reviews r: reviewsList){
                Users reviewer = userServiceClient.getUser(r.getReviewerId());
                String reviewDate = dateFormat.format(r.getDateCreated());
                reviewStringList.add(reviewer.getUsername()+"\t\t                   "+reviewDate+"\n\n"+r.getText());
            }*/

            //ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.activity_review_listview,reviewStringList);
            if(!reviewsList.isEmpty()) {
                ReviewAdapter adapter = new ReviewAdapter(this, reviewsList, currentToken);
                ListView listView = (ListView) findViewById(R.id.reviewList);
                listView.setAdapter(adapter);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void makeReview(View v) throws Exception{
        Intent getReviewedID = getIntent();
        Long reviewedId = getReviewedID.getLongExtra("reviewedID", 0);
        SharedPreferences sharedPref = getSharedPreferences("tokenInfo", MODE_PRIVATE);
        String currentToken = sharedPref.getString("currentToken", "");
        UserServiceClient userServiceClient = new UserServiceClient(currentToken);
        Users current = userServiceClient.getLoggedInUser();
        Long reviewerId = current.getId();
        Date date = new Date();

        Intent intent = new Intent(this, MakeReviewPage.class);
        intent.putExtra("token",currentToken);
        intent.putExtra("reviewerId",reviewerId);
        intent.putExtra("reviewedId",reviewedId);
        intent.putExtra("reviewDate", date);
        startActivity(intent);

    }

    public void goProfile(View v){
        Intent intent = new Intent(this, UserPage.class);
        startActivity(intent);
    }

    public void backToProfile(View v){
        Intent getReviewedID = getIntent();
        Long reviewedId = getReviewedID.getLongExtra("reviewedID", 0);
        Intent intent = new Intent(this, UserPage.class);
        intent.putExtra("creatorID",reviewedId);
        startActivity(intent);
    }
}
