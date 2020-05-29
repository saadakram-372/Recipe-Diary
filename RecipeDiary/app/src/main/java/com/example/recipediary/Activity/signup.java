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

public class signup extends AppCompatActivity {

    //declaring variables
    EditText EditText_email, EditText_password;
    FirebaseAuth mFirebaseAuth;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signupactivity);


        //Initializing variables
        TextView link_login = (TextView) findViewById(R.id.link_login);
        EditText_email = (EditText) findViewById(R.id.input_email);
        EditText_password = (EditText) findViewById(R.id.input_password);
        signup = (Button) findViewById(R.id.btn_signup);


        //When the link to "already have account" is clicked
        link_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                link_login();
            }
        });

        //When signup button is clicked, registration process occurs
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mFirebaseAuth = FirebaseAuth.getInstance();

                if (EditText_email.getText().toString().isEmpty()) {
                    EditText_email.setError("Please Enter email Id");
                    EditText_email.requestFocus();
                }

                else if (EditText_password.getText().toString().isEmpty()){
                    EditText_password.setError("Please Enter a password");
                    EditText_password.requestFocus();
                }

                else if (EditText_email.getText().toString().isEmpty() && EditText_password.getText().toString().isEmpty()) {
                    Toast.makeText(signup.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                }

                else if (!(EditText_email.getText().toString().isEmpty() && EditText_password.getText().toString().isEmpty())) {

                    mFirebaseAuth.createUserWithEmailAndPassword(EditText_email.getText().toString(), EditText_password.getText().toString()).addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(signup.this, "Signup unsuccessful, Please try again", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(signup.this, "Registered successfully", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }

                else {
                    Toast.makeText(signup.this, "Error occurred, sign up unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //method that opens the signup page
    public void link_login(){
        Intent intent1 = new Intent(signup.this, login.class);
        startActivity(intent1);
    }
}
