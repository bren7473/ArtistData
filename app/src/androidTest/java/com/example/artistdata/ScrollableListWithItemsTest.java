package com.example.artistdata;

import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.artistdata.Utils.atPosition;

@RunWith(AndroidJUnit4.class)
public class ScrollableListWithItemsTest {

    @Test
    public void spinnerTest() {
        try (ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class)) {
            //set up an api call and execute it
            onView(withId(R.id.editTextArtist)).perform(typeText("The Beatles"), closeSoftKeyboard());
            onView(withId(R.id.editTextArtist)).check(matches(withText("The Beatles")));
            onView(withId(R.id.EnterArtist)).perform(click());
            //check that the recycler view is removed during loading
            onView(withId(R.id.idTrackRecyclerView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
            //provide time for data retrieval
            Thread.sleep(5000);
            //check that the recycler view has come back after the data is retrieved
            onView(withId(R.id.idTrackRecyclerView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

            //Here we check that each item exists in the recycler views card
            onView(withId(R.id.idTrackRecyclerView)).check(matches(atPosition(0, hasDescendant(withId(R.id.idArtistName)))));
            onView(withId(R.id.idTrackRecyclerView)).check(matches(atPosition(0, hasDescendant(withId(R.id.idTrackName)))));
            onView(withId(R.id.idTrackRecyclerView)).check(matches(atPosition(0, hasDescendant(withId(R.id.idTrackPrice)))));
            onView(withId(R.id.idTrackRecyclerView)).check(matches(atPosition(0, hasDescendant(withId(R.id.idReleaseDate)))));
            onView(withId(R.id.idTrackRecyclerView)).check(matches(atPosition(0, hasDescendant(withId(R.id.idPrimaryGenreName)))));





        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
