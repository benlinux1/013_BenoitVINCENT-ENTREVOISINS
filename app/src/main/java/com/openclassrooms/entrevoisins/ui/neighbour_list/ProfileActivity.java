package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;

public class ProfileActivity extends AppCompatActivity {

    ImageButton mBackButton;
    Button mFavoriteButton;
    Boolean isFavorite;
    ImageView mNeighbourAvatar;
    TextView mNeighbourName;
    TextView mNeighbourLocation;
    TextView mNeighbourPhone;
    TextView mNeighbourSocialAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mBackButton = findViewById(R.id.profile_back_button);
        mFavoriteButton = findViewById(R.id.profile_favorite_button);
        // mAvatar = findViewById(R.id.profile_avatar);
        mNeighbourName = findViewById(R.id.profile_neighbour_name);


        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent NeighbourActivityIntent = new Intent(ProfileActivity.this, ListNeighbourActivity.class);
                startActivity(NeighbourActivityIntent);
            }
        });

        mFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO : create addToFavoriteList() method
            }
        });
    }

}