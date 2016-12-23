
package com.example.denizalp.thefirst;

        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

        import com.bounswe.group7.api.client.UserServiceClient;
        import com.bounswe.group7.model.Users;
        import com.bounswe.group7.model.security.Authority;
        import com.bounswe.group7.model.security.AuthorityName;

        import java.util.List;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);   //set view as activity_home_page.xml from resources
        SharedPreferences sharPref = getSharedPreferences("tokenInfo",MODE_PRIVATE);
        String token = sharPref.getString("currentToken","boşHocamBu");
        UserServiceClient userServiceClient = new UserServiceClient(token);
        try{
            Users curr = userServiceClient.getLoggedInUser();
            List<Authority> authorityList = curr.getAuthorities();
            Button newTopicButton = (Button) findViewById(R.id.button30);
            boolean isCreator = false;
            for(Authority a:authorityList){
                if(a.getName().getName().equals("ROLE_CREATOR")){
                    isCreator = true;
                    break;
                }
            }
            if(!isCreator) {
                System.out.println("You are not a creator!");
                newTopicButton.setVisibility(Button.GONE);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
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
        Intent intent = new Intent(this, TopicListPage.class);
        intent.putExtra("option",1);
        startActivity(intent);
    }

    public void showTopTopics(View view){
        Intent intent = new Intent(this, TopicListPage.class);
        intent.putExtra("option",2);
        startActivity(intent);
    }
    /*
        * GoCreateTopic method
        *  Add topic to Topic pages
        * */
    public void goToCreateTopic(View view){
        Intent intent = new Intent(this,TopicPage.class);
        startActivity(intent);
    }
    /*
     * goUserPage method
     * displays the user profile page
     * */
    public void goUserPage(View v){
        Intent intent = new Intent(this, UserPage.class);
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
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    /*
       * goFollowedTopics method
       * displays the topics that are followed by the user
       * */
    public void goFollowedTopics(View v){
        Intent intent = new Intent(this, TopicListPage.class);
        intent.putExtra("option",4);
        startActivity(intent);
    }

    public void showRecommendedTopics(View v){
        Intent intent = new Intent(this, TopicListPage.class);
        intent.putExtra("option",6);
        startActivity(intent);
    }

    public void searchTopic(View v){
        Intent intent = new Intent(this, SearchTopicPage.class);
        startActivity(intent);
    }
}