package com.example.recipediary;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toolbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class Intro extends AppCompatActivity {

    private  int[] sampleImages = {R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5, R.drawable.image6, R.drawable.image7};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introactivity);

        //declaring carouselView variable
        CarouselView carouselView = (CarouselView)findViewById(R.id.carouselView);
        Button login_btn = (Button)findViewById(R.id.btn_login);
        Button signup_btn = (Button)findViewById(R.id.btn_signup);


        //it sets the number of pages according to the number of images
        carouselView.setPageCount(sampleImages.length);

        //calling the method to change images
        carouselView.setImageListener(imageListener);


        //when login button is clicked
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPage();
            }
        });

        //when sign up button is clicked
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupPage();
            }
        });

    }

    //changes images after every interval
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

    //Go to login page when login button is pressed
    public void loginPage(){

        Intent intent1 = new Intent(Intro.this, login.class);
        startActivity(intent1);
    }

    //Go to signup page when signuup button is clicked
    public void signupPage(){
        Intent intent2 = new Intent(Intro.this, signup.class);
        startActivity(intent2);
    }

}
