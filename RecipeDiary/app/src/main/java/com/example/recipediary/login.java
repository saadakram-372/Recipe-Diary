package com.example.recipediary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);

        //Variables declared
        TextView link_signup = (TextView)findViewById(R.id.link_signup);

        //When the link to "make new account" is clicked
        link_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                link_signup();
            }
        });

    }

    //method that opens the signup page
    public void link_signup(){

        Intent intent1 = new Intent(login.this, signup.class);
        startActivity(intent1);
    }
}
