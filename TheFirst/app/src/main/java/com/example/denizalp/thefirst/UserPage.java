package com.example.denizalp.thefirst;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.SearchView;

import com.bounswe.group7.api.client.LoginServiceClient;
import com.bounswe.group7.api.client.SearchServiceClient;
import com.bounswe.group7.api.client.UserServiceClient;
import com.bounswe.group7.model.Users;
import com.bounswe.group7.model.security.Authority;

import org.glassfish.hk2.api.messaging.Topic;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
//import com.example.denizalp.UtopicApplication;

public class UserPage extends AppCompatActivity {

    Users token = new Users();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPref = getSharedPreferences("tokenInfo",MODE_PRIVATE);
        String currentToken = sharedPref.getString("currentToken","");
        String username = "";
        String password = "";
        Activity activity = this;
        if(currentToken.equals(""))
        {
            Intent intent = getIntent();
            username = intent.getStringExtra("username");
            password = intent.getStringExtra("password");
        }
        LoginServiceClient loginServiceClient = new LoginServiceClient();
        UserServiceClient userServiceClient = null;
        boolean isOther = false;
        try {
            if(currentToken.equals(""))
            {
                Users user = new Users(username, password);
                token = loginServiceClient.login(user);
                // SharedPreferences sharedPref = getSharedPreferences("tokenInfo",MODE_PRIVATE);
                SharedPreferences.Editor prefEditor = sharedPref.edit();
                prefEditor.putString("currentToken", token.getToken());
                prefEditor.commit();
            }
            else {
                userServiceClient = new UserServiceClient(currentToken);
                Intent intent = getIntent();
                Long creatorId = intent.getLongExtra("creatorID",-1);
                System.out.println(creatorId);
                if(creatorId != -1){
                    isOther = true;
                    token = userServiceClient.getUser(creatorId);
                }
                else token = userServiceClient.getLoggedInUser();
            }
            System.out.println(currentToken);
            //System.out.println(firstname+" "+lastname);
            setContentView(R.layout.activity_user_page);
            if(isOther) {
                Button button = (Button) findViewById(R.id.button6);
                button.setVisibility(Button.GONE);
            }
            TextView textView = (TextView) findViewById(R.id.textView4);
            TextView authority = (TextView) findViewById(R.id.textView6);
            String author = "";
            List<Authority> userAuthorities = token.getAuthorities();
            List<String> authorityNames = new ArrayList<String>();
            for(Authority a:userAuthorities){
                authorityNames.add(a.getName().getName());
            }
            if(!authorityNames.contains("ROLE_CREATOR")) {
                author = "Explorer";
            }
            else{
                author = "Creator";
            }
            System.out.println(token.getUsername());
            System.out.println(token.toString());
            textView.setText(token.getFirstname() + " " + token.getLastname());
            authority.setText(author);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        //SearchServiceClient searchServiceClient = new SearchServiceClient(currentToken);
       /* SearchView searchView = (SearchView) findViewById(R.id.searchBar);
        if(searchView==null) System.out.println("bo?um abi");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();     // Close keyboard on pressing IME_ACTION_SEARCH option
                //Log.d(TAG, "QueryTextSubmit : "+ query);
                //load search query
                //System.out.println("You have looked for "+query);
                Intent intent = new Intent(activity,TopicListPage.class);
                if(token.getToken() != null) intent.putExtra("token",token.getToken());
                else intent.putExtra("token",currentToken);
                intent.putExtra("option",5);
                intent.putExtra("keyword",query);
                startActivity(intent);
                return true;
            }

            public boolean onQueryTextChange(String newText) {
                //Log.d(TAG, "QueryTextChange: "+ newText);
                //System.out.println("You have looked now for "+newText);
                return false;
            }
        });*/
    }

    public void getUserInfo(View view) {
        Intent intent = new Intent(this, EditUserInformationPage.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("myUser", token);
        intent.putExtras(bundle);
        startActivityForResult(intent, 1);
    }

    public void onActivityResult(int req, int res, Intent data){
        super.onActivityResult(req,res,data);
        if (req==1) {
            if(res == 2) {
                Users currentUser = (Users) data.getExtras().getSerializable("backUser");
                TextView tw1 = (TextView) findViewById(R.id.textView4);
                String firstname = data.getStringExtra("fn");
                currentUser.setFirstname(firstname);
                String middlename = data.getStringExtra("mn");
                currentUser.setMiddlename(middlename);
                String surname = data.getStringExtra("sn");
                currentUser.setLastname(surname);
                tw1.setText(firstname+ " "+middlename+" "+surname);

                TextView tw2 = (TextView) findViewById(R.id.textView3);
                String companyname = data.getStringExtra("cn");
                tw2.setText(companyname);

               /* TextView tw3 = (TextView) findViewById(R.id.textView9);
                String bio = data.getStringExtra("bio");
                tw3.setText(bio);*/
            }
        }
    }
    public void goHome(View v) {
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }

    public void logout(View v){
        SharedPreferences sharedPref = getSharedPreferences("tokenInfo",MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.clear();
        prefEditor.apply();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void goLoggedInUserPage(View v){
        Intent intent = new Intent(this,UserPage.class);
        startActivity(intent);
    }

    public void listTopics(View v){
        Intent intent = new Intent(this, TopicListPage.class);
        intent.putExtra("option",3);
        intent.putExtra("creatorID",token.getId());
        startActivity(intent);
    }

    public void goToReviews(View v){
        Intent intent = new Intent(this, ReviewPage.class);
        intent.putExtra("reviewedID",token.getId());
        startActivity(intent);
    }

    public void showQuizProgress(View v){
        Intent intent = new Intent(this, QuizProgressPage.class);
        startActivity(intent);
    }

}
