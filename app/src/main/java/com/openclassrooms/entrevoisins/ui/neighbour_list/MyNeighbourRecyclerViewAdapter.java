package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteFavoriteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyNeighbourRecyclerViewAdapter.ViewHolder> {

    private final List<Neighbour> mNeighbours;
    private final String actualPage;

    public MyNeighbourRecyclerViewAdapter(List<Neighbour> items, String actualPage) {
        mNeighbours = items;
        this.actualPage = actualPage; // necessary to switch actions between Neighbours / Favorites Page
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_neighbour, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Neighbour neighbour = mNeighbours.get(position);
        holder.mNeighbourName.setText(neighbour.getName());
        Glide.with(holder.mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatar);

        // Modify Delete button's action according to Neighbours Page / Favorites Page
        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Delete neighbour from global List & favorites too
                if (actualPage.equals(NeighbourFragment.class.getName())) {
                    EventBus.getDefault().post(new DeleteFavoriteNeighbourEvent(neighbour));
                    EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour));
                }
                // Delete neighbour from favorites List only
                else if(actualPage.equals(FavoritesFragment.class.getName())) {
                    EventBus.getDefault().post(new DeleteFavoriteNeighbourEvent(neighbour));
                }
                else {
                    // Don't do anything, see for others pages in the future
                }
            }
        });

         // Launch Profile Activity according to the Neighbour's Id
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View neighbourItem) {
                Intent profileActivityIntent = new Intent(neighbourItem.getContext(), ProfileActivity.class);
                profileActivityIntent.putExtra("NEIGHBOUR_ID", neighbour.getId());
                neighbourItem.getContext().startActivity(profileActivityIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNeighbours.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_avatar)
        public ImageView mNeighbourAvatar;
        @BindView(R.id.item_list_name)
        public TextView mNeighbourName;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
