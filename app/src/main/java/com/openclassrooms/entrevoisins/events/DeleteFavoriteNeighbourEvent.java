package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Event fired when a user deletes a Favorite Neighbour
 */
public class DeleteFavoriteNeighbourEvent {

    /**
     * Neighbour to delete from Favorites List
     */
    public Neighbour neighbour;

    /**
     * Constructor.
     * @param neighbour
     */
    public DeleteFavoriteNeighbourEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }

}