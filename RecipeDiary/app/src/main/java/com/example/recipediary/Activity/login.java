package com.example.recipediary.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.recipediary.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    //declaring variables
    TextView link_signup;
    EditText EditText_email, EditText_password;
    FirebaseAuth mFirebaseAuth;
    Button login;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);


        //initializing variables with values
        link_signup = (TextView)findViewById(R.id.link_signup);
        login = (Button)findViewById(R.id.btn_login);
        EditText_email = (EditText) findViewById(R.id.input_email);
        EditText_password = (EditText) findViewById(R.id.input_password);
        mFirebaseAuth = FirebaseAuth.getInstance();


        //When the link to "make new account" is clicked
        link_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                link_signup();
            }
        });


        //When the login button is clicked it checks the fields and if accurate then only logins

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (EditText_email.getText().toString().isEmpty()){
                    EditText_email.setError("Please Enter email Id");
                    EditText_email.requestFocus();
                }

                else if (EditText_password.getText().toString().isEmpty()){
                    EditText_password.setError("Please Enter a password");
                    EditText_password.requestFocus();
                }

                else if (EditText_email.getText().toString().isEmpty() && EditText_password.getText().toString().isEmpty()){
                    Toast.makeText(login.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                }

                else if (!(EditText_email.getText().toString().isEmpty() && EditText_password.getText().toString().isEmpty())){
                    mFirebaseAuth.signInWithEmailAndPassword(EditText_email.getText().toString(),EditText_password.getText().toString()).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()){
                                Toast.makeText(login.this, "Login Error, please try again", Toast.LENGTH_SHORT).show();
                            }

                            else{
                                Intent intent = new Intent(login.this, home.class);
                                startActivity(intent);
                                Toast.makeText(login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

                else {
                    Toast.makeText(login.this, "Error Occurred, Can't login", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //method that opens the signup page
    public void link_signup(){

        Intent intent1 = new Intent(login.this, signup.class);
        startActivity(intent1);
    }
}
