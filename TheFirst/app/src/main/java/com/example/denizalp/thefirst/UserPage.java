package com.example.denizalp.thefirst;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bounswe.group7.api.client.LoginServiceClient;
import com.bounswe.group7.model.Users;
//import com.example.denizalp.UtopicApplication;

public class UserPage extends AppCompatActivity {

    Users token = new Users();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");
        LoginServiceClient loginServiceClient = new LoginServiceClient();

        try {
            Users user = new Users(username, password);
            token = loginServiceClient.login(user);
            SharedPreferences sharedPref = getSharedPreferences("tokenInfo",MODE_PRIVATE);
            SharedPreferences.Editor prefEditor = sharedPref.edit();
            prefEditor.putString("currentToken",token.getToken());
            prefEditor.commit();
            System.out.println(token.getToken());
            //System.out.println(firstname+" "+lastname);
            setContentView(R.layout.activity_user_page);
            TextView textView = (TextView) findViewById(R.id.textView4);
            System.out.println(token.getUsername());
            textView.setText(token.getFirstname() + " " + token.getLastname());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
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

                TextView tw3 = (TextView) findViewById(R.id.textView9);
                String bio = data.getStringExtra("bio");
                tw3.setText(bio);
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
}
