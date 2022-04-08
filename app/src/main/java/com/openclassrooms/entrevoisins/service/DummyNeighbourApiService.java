package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private List<Neighbour> favoritesList = new ArrayList<>();

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
        for (Neighbour neighbour : getNeighbours())
            if (neighbour.isFavorite()) {
                favoritesList.add(neighbour);
            }
        return favoritesList;
    }

    /**
     * Add a neighbour to favorites List
     * {@param neighbour}
     */
    @Override
    public void setFavorite(Neighbour neighbour) {
        favoritesList.add(neighbour);
    }

    /**
     * Delete a neighbour from favorites List
     * {@param neighbour}
     */
    @Override
    public void deleteFavoriteNeighbour(Neighbour neighbour) {
        favoritesList.remove(neighbour);
    }
}
