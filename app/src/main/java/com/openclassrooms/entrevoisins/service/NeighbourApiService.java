package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * Create a neighbour
     * @param neighbour
     */
    void createNeighbour(Neighbour neighbour);

    /**
     * Get a specific neighbour's data
     * @param id
     */
    Neighbour getNeighbourProfile(long id);

    /**
     * Get all my Favorites Neighbours
     * @return {@link List}
     */
    List<Neighbour> getFavoritesList();

    /**
     * Set / unset neighbour in / from favorites list
     * @param neighbour
     */
    void toggleFavorite(Neighbour neighbour);

}
