package com.example.recipediary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signupactivity);

        //variables declared
        TextView link_login = (TextView)findViewById(R.id.link_login);


        //When the link to "already have account" is clicked
        link_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                link_login();
            }
        });


    }

    //method that opens the signup page
    public void link_login(){

        Intent intent1 = new Intent(signup.this, login.class);
        startActivity(intent1);
    }
}
