package com.example.recipediary.Activity;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.example.recipediary.Adapter.RecipeAdapter;
import com.example.recipediary.Model.Recipes;
import com.example.recipediary.R;
import com.google.android.material.navigation.NavigationView;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;



public class home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //variables for the nav drawer
    private DrawerLayout mDrawLayout;
    private ActionBarDrawerToggle mToggle;
    EditText text;
    Button search_button;
    ProgressBar progressBar;
    ListView listView;
    ArrayList<Recipes> recipe_names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homeactivity);

        text = (EditText) findViewById(R.id.textbox);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        listView = (ListView) findViewById(R.id.listview_home);


        setNavigationViewListener();


        mDrawLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawLayout, R.string.open, R.string.close);
        mDrawLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //when search button is clicked
        search_button = (Button) findViewById(R.id.search_button);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //the object of the class is made so, that an api can be read
                //execute() method executes the AsyncTask that is being extended
                recipe_names = new ArrayList<Recipes>();
                new APIReading().execute();
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    //when a menu on the nav drawer is clicked
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // Handle navigation view item clicks here.
        switch (menuItem.getItemId()) {
            case R.id.favourites: {
                Intent intent = new Intent(this, favourites.class);
                startActivity(intent);
                break;
            }

            case R.id.about: {
                Intent intent = new Intent(this, about.class);
                startActivity(intent);
                break;
            }

            case R.id.contact: {
                Intent intent = new Intent(this, contact.class);
                startActivity(intent);
                break;
            }

            case R.id.logout: {
                Intent intent = new Intent(this, login.class);
                startActivity(intent);
                Toast.makeText(this, "Log Out successful", Toast.LENGTH_LONG).show();
                break;
            }
        }
        //close navigation drawer
        mDrawLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    //setting a listener on the menus of the nav drawer
    private void setNavigationViewListener() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    //class to read from the API
    class APIReading extends AsyncTask<Void, Void, String> {

        static final String API_URL = "https://forkify-api.herokuapp.com/api/search?";
        String title = text.getText().toString();


        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
//            responseView.setText("");
        }

        protected String doInBackground(Void... params) {

            try {
                URL url = new URL(API_URL + "q=" + title);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if (response == null) {
                response = "THERE WAS AN ERROR";
            }
            progressBar.setVisibility(View.GONE);
            Log.i("INFO", response);
            AfterExecution(response);
        }
    }


    //Parsing the json response and updating the screen
    public void AfterExecution(String response) {

        if (response != null) {

            try {

                JSONObject object = new JSONObject(response);

                //getting json array node
                JSONArray json_array = object.getJSONArray("recipes");


//                looping through all recipes
//                and getting the name sof the titles of the recipes and showing on the screen
                for (int i = 0; i < json_array.length(); i++) {

                    JSONObject obj = json_array.getJSONObject(i);
                    String recipe_name = obj.getString("title");
                    String image_urls = obj.getString("image_url");

                    recipe_names.add(new Recipes(recipe_name, image_urls));
                }

            } catch (Exception e) {
                Log.e("Error at line 132:", e.getMessage(), e);
            }

            RecipeAdapter adapter = new RecipeAdapter(getApplicationContext(), R.layout.listeview_layout_home, recipe_names);

            listView.setAdapter(adapter);
        }
    }




}