package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

public class ProfileActivity extends AppCompatActivity {

    ImageButton mBackButton;
    Button mFavoriteButton;
    Boolean isFavorite;
    ImageView mNeighbourAvatar;
    TextView mNeighbourName;
    TextView mNeighbourLocation;
    TextView mNeighbourPhone;
    TextView mNeighbourSocialAccount;
    TextView mNeighbourDescription;

    private NeighbourApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mApiService = DI.getNeighbourApiService();

        Intent getProfileIntent = getIntent();
        long id = getProfileIntent.getLongExtra("NEIGHBOUR_ID",-1);
        Neighbour neighbour = mApiService.getNeighbour(id);

        mBackButton = findViewById(R.id.profile_back_button);
        // mFavoriteButton = findViewById(R.id.profile_favorite_button);

        mNeighbourAvatar = findViewById(R.id.profile_avatar);
        mNeighbourName = findViewById(R.id.profile_neighbour_name);
        mNeighbourLocation = findViewById(R.id.profile_location_text);
        mNeighbourPhone = findViewById(R.id.profile_phone_text);
        mNeighbourSocialAccount = findViewById(R.id.profile_social_account_text);
        mNeighbourDescription = findViewById(R.id.profile_about_me_text);

        Glide.with(mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .centerCrop()
                .into(mNeighbourAvatar);

        mNeighbourName.setText(neighbour.getName());
        mNeighbourLocation.setText(neighbour.getAddress());
        mNeighbourPhone.setText(neighbour.getPhoneNumber());
        mNeighbourSocialAccount.setText("www.facebook.com/" + neighbour.getName());
        mNeighbourDescription.setText(neighbour.getAboutMe());


        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // mFavoriteButton.setOnClickListener(new View.OnClickListener() {
            // @Override
            // public void onClick(View view) {
                // TODO : create addToFavoriteList() method
            //}
       // });
    }

}