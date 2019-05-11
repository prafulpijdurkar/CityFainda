package com.pp.backbase.cityfinder.ui.fragment;

import android.content.Intent;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.ListView;

import com.pp.backbase.cityfinder.CountHelper;
import com.pp.backbase.cityfinder.R;
import com.pp.backbase.cityfinder.ui.activities.MainActivity;

import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

/**
 * Created by Praful Pijdurkar on 5/7/19.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest

public class MainActivityFragmentInstrumentedTest {

    private static final String TEXT_ITEM_30 = "item: 30";

    private static final String TEXT_ITEM_30_SELECTED = "30";

    private static final String TEXT_ITEM_60 = "item: 60";

    // Match the last item by matching its text.
    private static final String LAST_ITEM_ID = "item: 99";


    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Before
    public void init(){
        activityActivityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction();
    }


    /**
     * Test "Search Text not in the list".
     */
    @Test
    public void textNotMatch() {
        onView(ViewMatchers.withId(R.id.textBox)).perform(typeText("BackBase"));//.check(matches(ViewMatchers.withText("Hello")));
        pauseTestFor(500);
        assertThat( (CountHelper.getListCount (R.id.list)), is(0));
   }


    /**
     * Test "Search Text in Camel Case".
     */
    @Test
    public void caseSensativeCamelCase() {

        onView(withId(R.id.textBox)).perform(typeText("sAnd"));
        pauseTestFor(500);
        assertThat( (CountHelper.getListCount (R.id.list)), is(greaterThan(0)));
    }


    @Test
    public void caseSensativeAllSmall() {
         onView(withId(R.id.textBox)).perform(typeText("Sand"));
        pauseTestFor(500);
        assertThat( (CountHelper.getListCount (R.id.list)), is(greaterThan(0)));

    }
    @Test
    public void caseSensativeAllCAPS() {
         onView(withId(R.id.textBox)).perform(typeText("SAND"));
        pauseTestFor(500);
        assertThat( (CountHelper.getListCount (R.id.list)), is(greaterThan(0)));

    }
    @Test
    public void emptySearch() {
        onView(withId(R.id.textBox)).perform(typeText(""));
        pauseTestFor(500);
        assertThat( (CountHelper.getListCount (R.id.list)), is(greaterThan(0)));


    }
    private void pauseTestFor(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




//    @Testpublic void testScrollingAt()throws Exception
//    {
//        // Scrolls till the position 20
//
//        onData(hasEntry(equalTo(ListViewSample.ROW_TEXT),is("List item: 20")))
//
//                .check(matches(isCompletelyDisplayed()));
//        //scrolls till the end
//
//        onData(hasEntry(equalTo(ListViewSample.ROW_TEXT),is("List item: 49")))
//
//                .check(matches(isCompletelyDisplayed()));
//    }
}
