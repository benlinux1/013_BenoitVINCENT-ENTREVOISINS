package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

public class ProfileActivity extends AppCompatActivity {

    private ImageButton mBackButton;
    private FloatingActionButton mFavoriteButton;
    private ImageView mNeighbourAvatar;
    private TextView mNeighbourNameTitle;
    private TextView mNeighbourName;
    private TextView mNeighbourLocation;
    private TextView mNeighbourPhone;
    private TextView mNeighbourSocialAccount;
    private TextView mNeighbourDescription;
    private NeighbourApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mApiService = DI.getNeighbourApiService();

        /**
         * Get Neighbour's informations from previous activity
         */
        Intent getProfileIntent = getIntent();
        long id = getProfileIntent.getLongExtra("NEIGHBOUR_ID",-1);
        Neighbour neighbour = mApiService.getNeighbourProfile(id);

        /**
         * Set all neighbour's profile informations in the right fields
         */
        mBackButton = findViewById(R.id.profile_back_button);
        mFavoriteButton = findViewById(R.id.profile_favorite_button);
        mNeighbourAvatar = findViewById(R.id.profile_avatar);
        mNeighbourNameTitle = findViewById(R.id.profile_title_name);
        mNeighbourName = findViewById(R.id.profile_neighbour_name);
        mNeighbourLocation = findViewById(R.id.profile_location_text);
        mNeighbourPhone = findViewById(R.id.profile_phone_text);
        mNeighbourSocialAccount = findViewById(R.id.profile_social_account_text);
        mNeighbourDescription = findViewById(R.id.profile_about_me_text);

        Glide.with(mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .centerCrop()
                .into(mNeighbourAvatar);

        mNeighbourNameTitle.setText(neighbour.getName());
        mNeighbourName.setText(neighbour.getName());
        mNeighbourLocation.setText(neighbour.getAddress());
        mNeighbourPhone.setText(neighbour.getPhoneNumber());
        mNeighbourSocialAccount.setText("www.facebook.com/" + neighbour.getName());
        mNeighbourDescription.setText(neighbour.getAboutMe());

        // Set Favorite Button color according to the situation
        mFavoriteButton.setImageResource(neighbour.isFavorite()?R.drawable.ic_star_yellow_24 : R.drawable.ic_favorite_empty);

        /**
         * Listener on Back Button to close profile's page
         */
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        /**
         * Listener on Favorite's Button to add/remove neighbour in/from Favorites List
         * Modify the favorite's button design (empty / full) according to the situation too
         */
        mFavoriteButton.setOnClickListener(new View.OnClickListener() {

            /**
             * Display an alert dialog box for best user XP ;)
             */
            public void createCustomDialogBox(String message) {
                // Build an alert dialogBox
                AlertDialog.Builder builder
                        = new AlertDialog
                        .Builder(ProfileActivity.this);
                builder.setCancelable(true);
                builder.setMessage(message);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                // Create the Alert dialog
                AlertDialog alertDialog = builder.create();

                // Show the Alert Dialog box
                alertDialog.show();

                // Center DialogBox Button
                Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                LinearLayout parent = (LinearLayout) positiveButton.getParent();
                parent.setGravity(Gravity.CENTER_HORIZONTAL);
                View leftSpacer = parent.getChildAt(1);
                leftSpacer.setVisibility(View.GONE);

                // Center DialogBox Message
                TextView messageText = (TextView) alertDialog.findViewById(android.R.id.message);
                messageText.setGravity(Gravity.CENTER);
            }

            /**
             * Toggle neighbour's favorite attribute
             * Toggle the favorite's button design (empty / full) according to the situation
             * Displays alertDialog Box according to the situation
             * */
            @Override
            public void onClick(View view) {
                mApiService.toggleFavorite(neighbour);
                mFavoriteButton.setImageResource(neighbour.isFavorite()?R.drawable.ic_star_yellow_24 : R.drawable.ic_favorite_empty);
                createCustomDialogBox(neighbour.isFavorite()?neighbour.getName() + " a été ajouté(e) à vos favoris" : neighbour.getName() + " a été supprimé(e) de vos favoris");
            }
       });
    }

}