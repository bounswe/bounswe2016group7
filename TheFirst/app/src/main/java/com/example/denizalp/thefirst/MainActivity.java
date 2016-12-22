package com.example.denizalp.thefirst;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ObbInfo;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.bounswe.group7.api.client.LoginServiceClient;
import com.bounswe.group7.api.client.TopicServiceClient;
import com.bounswe.group7.model.Users;
/*
* MainActivity class
* to start up the whole program,this is the first page to be seen by the users
* */
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  //set view as activity_main.xml from resources
        SharedPreferences sharedPref = getSharedPreferences("tokenInfo",MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.clear();
        prefEditor.apply();
        TopicServiceClient tsc = new TopicServiceClient();  //create new topic service client
        try {
            System.out.println(tsc.getRecentTopics().size());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
  /*
  * loginUser method
  * this method is for a valid user to enter the application in order to see the documents
  * */
    public void loginUser(View view){
        Intent intent = new Intent(this, UserPage.class);
        Bundle bundle = new Bundle();
        try {
            LoginServiceClient loginservice = new LoginServiceClient();
            EditText editText1 = (EditText) findViewById(R.id.username);  //to set username
            EditText editText2 = (EditText) findViewById(R.id.password);   //to set password
            String username = editText1.getText().toString();
            String password = editText2.getText().toString();

            Users user = new Users(username, password); //create new user

            Users token = new Users();
            try {
                token = loginservice.login(user);

                if(token != null) {
                    intent.putExtra("username", username); //get username
                    intent.putExtra("password", password); //get password
                    // System.out.println(token.getFirstname() + " " + token.getLastname());
                    startActivity(intent);
                }
                else {
                    System.out.println("Username or password is incorrect!!!");  //if username or password is not entered
                }
            } catch (Exception e) {
                //System.out.println("Username or password is incorrect!!!");
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("Error");  //set error message for not valid credentials
                alertDialog.setMessage("Username or password is incorrect!!!");   //?f the username or password not valid
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show(); //show the error
                e.printStackTrace();
            }
            //token.getToken();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        //startActivity(intent);
    }
//  getInfo method
//    this method is to obtain information
    public void getInfo(View view) {
        //move to obtain info page
        Intent intent = new Intent(this, ObtainInfo.class);
        startActivity(intent);
    }

}
