package com.example.denizalp.thefirst;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.bounswe.group7.model.Users;

public class EditUserInformationPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_information_page);


    }

    public void sendInfo(View view) {
        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        Users currentUser = (Users) intent.getExtras().getSerializable("myUser");
        bundle.putSerializable("backUser", currentUser);
        EditText firstname = (EditText) findViewById(R.id.editText3);
        String fname = firstname.getText().toString();
        EditText middlename = (EditText) findViewById(R.id.editText4);
        String mname = firstname.getText().toString();
        EditText surname = (EditText) findViewById(R.id.editText6);
        String sname = firstname.getText().toString();
        EditText companyname = (EditText) findViewById(R.id.editText12);
        String cname = firstname.getText().toString();
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
