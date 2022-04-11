package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void addNeighboursWithSuccess() {
        // New neighbour's data
        Neighbour createdNeighbour = new Neighbour( (long) service.getNeighbours().size(), "Fake new neighbour", "https://i.pravatar.cc/150?u=a042581f4e29026704d", "fake address", "0123", "Hello, I'm a fake neighbour", false );
        // Call method to create neighbour in API Service
        service.createNeighbour(createdNeighbour);
        // Check if created neighbour is in the list
        assertTrue(service.getNeighbours().contains(createdNeighbour));
    }

    @Test
    public void addNeighbourToFavoritesWithSuccess() {
        // Take the first neighbour in the global list
        Neighbour neighbourToAddToFavorites = service.getNeighbours().get( 0 );
        // Call method to set neighbour in favorites list
        service.setFavorite(neighbourToAddToFavorites);
        // Check if neighbour is in the favorites list and stay in global list
        assertTrue(service.getFavoritesList().contains(neighbourToAddToFavorites));
        assertTrue(service.getNeighbours().contains(neighbourToAddToFavorites));
    }

    @Test
    public void deleteNeighbourFromFavoritesWithSuccess() {
        // Take the first neighbour in global list
        Neighbour neighbourToDeleteFromFavorites = service.getNeighbours().get( 0 );
        // Call method to set neighbour in favorites list
        service.setFavorite(neighbourToDeleteFromFavorites);
        // Call reverse method to delete neighbour from favorites list
        service.deleteFavoriteNeighbour(neighbourToDeleteFromFavorites);
        // Check if neighbour is removed from the favorites list BUT is still in global list
        assertFalse(service.getFavoritesList().contains(neighbourToDeleteFromFavorites));
        assertTrue(service.getNeighbours().contains(neighbourToDeleteFromFavorites));
    }

    @Test
    public void deleteFavoriteNeighbourFromApiWithSuccess() {
        // Take the first neighbour in global list
        Neighbour neighbourToDeleteFromApi = service.getNeighbours().get( 0 );
        // Call method to set neighbour in favorites list
        service.setFavorite(neighbourToDeleteFromApi);
        // Call reverse method to delete neighbour from favorites list
        service.deleteNeighbour(neighbourToDeleteFromApi);
        // Check if neighbour is removed from the favorites list AND from  global list
        assertFalse(service.getFavoritesList().contains(neighbourToDeleteFromApi));
        assertFalse(service.getNeighbours().contains(neighbourToDeleteFromApi));
    }
}
