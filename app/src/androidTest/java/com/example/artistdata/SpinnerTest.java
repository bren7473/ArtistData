package com.example.artistdata;

import android.app.Application;

import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withResourceName;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.artistdata.Utils.atPosition;

@RunWith(AndroidJUnit4.class)
public class SpinnerTest {

    @Test
    public void spinnerTest() {
        try (ActivityScenario<MainActivity> ignore = ActivityScenario.launch(MainActivity.class)) {
            onView(withId(R.id.editTextArtist)).perform(typeText("The Beatles"), closeSoftKeyboard());
            onView(withId(R.id.editTextArtist)).check(matches(withText("The Beatles")));
            onView(withId(R.id.indeterminateBar)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
            onView(withId(R.id.EnterArtist)).perform(click());
            onView(withId(R.id.idTrackRecyclerView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
            onView(withId(R.id.indeterminateBar)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
            Thread.sleep(5000);
            onView(withId(R.id.indeterminateBar)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
            onView(withId(R.id.idTrackRecyclerView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

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
