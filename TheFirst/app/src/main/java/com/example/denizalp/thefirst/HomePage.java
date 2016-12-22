package com.example.denizalp.thefirst;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
/*
* HomePage class
* this is the page that a verified user can see
* in user page there is a home button which directs the user to this page
* */
public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);  //set view as activity_home_page.xml from resources
        SharedPreferences sharPref = getSharedPreferences("tokenInfo",MODE_PRIVATE);
        String token = sharPref.getString("currentToken","boşHocamBu");
        //System.out.println("tokenım burada: "+token);
    }
    /*
    * There are buttons on home page
    * Which are Recent topics, Top topics , Followed topics
    *  Create Topic button and Profile button and Logout button
    * */

    /*
    * showRecentTopics method
    * displays the recently added topics to the web page
    * */
    public void showRecentTopics(View view){
        Intent intent = new Intent(this, TopicListPage.class);  //go topicListPage class
        intent.putExtra("option",1);  //get the recent topics
        startActivity(intent);  //recent topics are seen
    }

    /*
      * showTopTopics method
      * displays the popular topics on the web page
      * */
    public void showTopTopics(View view){
        Intent intent = new Intent(this, TopicListPage.class); //go topicListPage class
        intent.putExtra("option",2); //get the top topics
        startActivity(intent); //top topics are seen
    }
    /*
    * GoCreateTopic method
    *  Add topic to Topic pages
    * */
    public void goToCreateTopic(View view){
        Intent intent = new Intent(this,TopicPage.class); //go TopicPage class
        startActivity(intent);
    }
  /*
  * goUserPage method
  * displays the user profile page
  * */
    public void goUserPage(View v){
        Intent intent = new Intent(this, UserPage.class);  //go UserPage class
        startActivity(intent);
    }
    /*
    * logout method
    * Log out the currently logged in user
    * */
    public void logouT(View v){
        SharedPreferences sharedPref = getSharedPreferences("tokenInfo",MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.clear();
        prefEditor.apply();
        Intent intent = new Intent(this,MainActivity.class);  //go MainActivity class
        startActivity(intent);
    }
   /*
   * goFollowedTopics method
   * displays the topics that are followed by the user
   * */
    public void goFollowedTopics(View v){
        Intent intent = new Intent(this, TopicListPage.class);  //go TopicListpage class
        intent.putExtra("option",4);  //get the followed topics
        startActivity(intent);  //followed topics are seem
    }
}
