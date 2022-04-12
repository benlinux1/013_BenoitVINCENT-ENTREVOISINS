
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withResourceName;
import static android.support.test.espresso.matcher.ViewMatchers.withTagKey;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;



/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;
    private static int FAVORITES_ITEMS_COUNT = 0;

    private ListNeighbourActivity mActivity;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteNeighbourAction_shouldRemoveItem() {
        // Check neighbours list count (12)
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : check if item was deleted in the list (11)
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT-1));
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT=11));
    }

    /**
     * When we add a neighbour, the item is in the neighbours list
     */
    @Test
    public void myNeighboursList_addingNewNeighbourAction_shouldAddItem() {
        // Check neighbours list count (12)
        onView(withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        // Click on the creation button to add a new neighbour
        onView(withId(R.id.add_neighbour))
                .perform(click());
        // Click on the name field
        onView(withId(R.id.name))
                .perform(click());
        // Enter neighbour's name
        onView(withId(R.id.name))
                .perform(typeText("New_Neighbour"), closeSoftKeyboard());
        // Click on the creation button to add this new neighbour
        onView(withId(R.id.create))
                .perform(click());
        // Then : check if item was added to the list (13)
        onView(withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT+1));
        onView(withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT=13));
    }

    /**
     * When we click on a neighbour in the list, the neighbour's profile is opened
     */
    @Test
    public void myNeighboursList_clickOnClickNeighbourAction_shouldOpenNeighbourProfile() {
        // Click on the third item in the neighbour list
        onView(withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        // Check if neighbour's profile is displayed
        onView(withId(R.id.profile_page)).check(matches(isDisplayed()));
    }


    /**
     * When we add a neighbour to favorites with dedicated button, the item is in the favorites list
     */
    @Test
    public void myNeighboursProfile_addingToFavoritesAction_shouldAddItemToFavoritesList() {
        // Check favorites list count (0)
        onView(withId(R.id.list_favorites)).check(withItemCount(FAVORITES_ITEMS_COUNT));
        // Click on the first neighbour in the list
        onView(withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        // Click on the adding to favorites button to add this neighbour to favorites
        onView(withId(R.id.profile_favorite_button))
                .perform(click());
        // Close AlertDialog Window
        onView(isRoot()).perform( ViewActions.pressBack());
        // Go back on neighbours list view and to favorites list view
        onView(withId(R.id.profile_back_button))
                .perform(click());
        // Then : the number of favorites is 1
        onView(withId(R.id.list_favorites)).check(withItemCount(FAVORITES_ITEMS_COUNT+1));
    }

    /**
     * When we delete a neighbour which was in favorites tab, neighbour is deleted from both lists (global & favorite)
     */
    @Test
    public void myNeighboursList_deleteNeighbourSetInFavorites_shouldDeleteNeighbourFromAllLists() {

    }
}