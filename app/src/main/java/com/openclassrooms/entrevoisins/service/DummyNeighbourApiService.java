package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    /**
     * loop on each neighbour in List
     * return neighbour's data with @param id
     */
    @Override
    public Neighbour getNeighbourProfile(long id) {
        Neighbour neighbour = null;
        for(Neighbour i : neighbours) {
            if(i.getId() == id){
                neighbour = i;
                break;
            }
        }
        return neighbour;
    }

    /**
     * Get the favorites List
     */
    @Override
    public List<Neighbour> getFavoritesList() {
        List<Neighbour> favoritesList = new ArrayList<>();
        for (Neighbour neighbour : getNeighbours())
            if (neighbour.isFavorite()) {
                favoritesList.add(neighbour);
            }
        return favoritesList;
    }

    /**
     * Set / unset neighbour in / from favorites list
     * {@param neighbour}
     */
    @Override
    public void toggleFavorite(Neighbour neighbour) {
        if (neighbour.isFavorite()) {
            neighbour.setFavorite(false);
        } else {
            neighbour.setFavorite(true);
        }
    }
}
