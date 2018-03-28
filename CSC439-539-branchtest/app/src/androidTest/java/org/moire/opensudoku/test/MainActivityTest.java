package org.moire.opensudoku.test;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.moire.opensudoku.gui.SudokuPlayActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by User on 2/28/2018.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest

public class MainActivityTest {

        @Rule
        public ActivityTestRule<SudokuPlayActivity> mActivityRule =
                new ActivityTestRule(SudokuPlayActivity.class);

        @Test
        public void listGoesOverTheFold() {
            onView(withText("Hello world!")).check(matches(isDisplayed()));
        }
}