package com.example.artistdata;

import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withResourceName;

@RunWith(AndroidJUnit4.class)
public class ButtonNameAndEditTextTest {

    @Test
    public void enterArtistTest() {
        try (ActivityScenario<MainActivity> ignore = ActivityScenario.launch(MainActivity.class)) {
            onView(withId(R.id.EnterArtist)).check(matches(withResourceName("EnterArtist")));
        }
    }
}
