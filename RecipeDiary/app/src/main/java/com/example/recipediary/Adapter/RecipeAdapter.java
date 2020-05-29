package com.example.recipediary.Adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.recipediary.Model.Recipes;
import com.example.recipediary.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecipeAdapter extends ArrayAdapter<Recipes> {
    ProgressBar progressBar;
    private Context context;
    private int resourceid;
    ArrayList<Recipes> arrayList;

    public RecipeAdapter(@NonNull Context context,int resource, @NonNull ArrayList<Recipes> R) {
        super(context, resource, R);

        this.context = context;
        this.resourceid = resource;
        this.arrayList = R;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Recipes recipe = getItem(position);

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.listeview_layout_home, parent, false);

        }

        progressBar = (ProgressBar)listItemView.findViewById(R.id.progressBar1);

        //displaying the images from the url obtained from the json result (from reading API)
        ImageView images = (ImageView) listItemView.findViewById(R.id.imageView_home);
        progressBar.setVisibility(View.VISIBLE);
        Picasso.get().load(recipe.getUrl()).resize(450, 250)
                .centerCrop().into(images, new Callback() {
            @Override
            public void onSuccess() {
                progressBar.setVisibility(View.GONE);
                //Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(context, "unsuccessful", Toast.LENGTH_SHORT).show();
                e.getMessage();
                e.printStackTrace();
            }
        });

        //displaying the titles of recipes from the json result obtained from reading the API
        TextView recipe_titles = (TextView) listItemView.findViewById(R.id.list_text);
        recipe_titles.setText(recipe.getTitle());


        return listItemView;
    }
}
