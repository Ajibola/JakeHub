package com.jibola.app.jakehub.ui.repo;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.jibola.app.jakehub.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.jibola.app.jakehub.ui.repo.support.Matcher.childAtPosition;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.*;

/**
 * Created by hp on 8/14/2017.
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(
            MainActivity.class);


    @Test
    public void shouldLoadRecyclerView() {
        ViewInteraction recyclerView = onView(allOf(withId(R.id.repo_recycler_view), childAtPosition(
                allOf(withId(R.id.swipe_refresh_layout), childAtPosition(withId(android.R.id.content), 0)), 0),
                isDisplayed()));
        recyclerView.check(matches(isDisplayed()));
    }

    @Test
    public void shouldShowLoadingOnLaunch() {
        ViewInteraction swipeRefresh = onView(allOf(withId(R.id.swipe_refresh_layout), isDisplayed()));
    }

    @Test
    public void shouldDisplayItemsOnSuccessfulApiRequest() {

    }

    @Test
    public void shouldDisplayErrorOnFailedApiRequest() {

    }

    @Test
    public void shouldDisplayFooterOnLoadMore() {

    }

    @Test
    public void shouldRemoveFooterOnLoadMoreSuccessOrFailure() {

    }

}