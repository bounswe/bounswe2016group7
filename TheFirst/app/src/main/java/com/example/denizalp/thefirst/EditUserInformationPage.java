package com.example.denizalp.thefirst;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.bounswe.group7.model.Users;


/*
* EditUserInformationPage class
* This class is for users to edit their personal information
* */
public class EditUserInformationPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_information_page); //set layout as activity activity_edit_user_information_page.xml


    }
   /*
   * sendInfo method
   * get the new versions of the changed info, and change the user info accordingly
   * */
    public void sendInfo(View view) {
        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        Users currentUser = (Users) intent.getExtras().getSerializable("myUser");
        bundle.putSerializable("backUser", currentUser);
        EditText firstname = (EditText) findViewById(R.id.editText3);
        String fname = firstname.getText().toString();  //string fname is the new firstname
        EditText middlename = (EditText) findViewById(R.id.editText4);
        String mname = firstname.getText().toString();  //string mname is the new middlename
        EditText surname = (EditText) findViewById(R.id.editText6);
        String sname = firstname.getText().toString();   // string sname is the new surname
        EditText companyname = (EditText) findViewById(R.id.editText12);
        String cname = firstname.getText().toString();  //string cname is the new company name
        EditText userBio = (EditText) findViewById(R.id.editText13);
        String bio = userBio.getText().toString();
        intent.putExtra("fn",fname);
        intent.putExtra("mn",mname);
        intent.putExtra("sn",sname);
        intent.putExtra("cn",cname);
        intent.putExtra("bio",bio);
        intent.putExtras(bundle);
        setResult(2,intent);
        finish();
    }


}
