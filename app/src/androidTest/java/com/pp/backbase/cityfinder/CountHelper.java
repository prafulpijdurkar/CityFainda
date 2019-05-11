package com.pp.backbase.cityfinder;

import android.support.annotation.IdRes;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.widget.ListView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Praful Pijdurkar on 5/8/19.
 */
public class CountHelper {

    private static int count;

    public static int getListCount(@IdRes int listViewId) {
        count = 0;

        Matcher matcher = new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                count = ((ListView) item).getCount();
                return true;
            }

            @Override
            public void describeTo(Description description) {
            }
        };

        onView(withId(listViewId)).check(matches(matcher));

        int result = count;
        count = 0;
        return result;
    }

//    public static int getCountFromListUsingBoundedMatcher(@IdRes int listViewId) {
//        count = 0;
//
//        Matcher<Object> matcher = new BoundedMatcher<View>(String.class) {
//             public boolean matchesSafely(String item) {
//                count += 1;
//                return true;
//            }
//
//            @Override
//            public void describeTo(Description description) {
//            }
//        };
//
//        try {
//            // do a nonsense operation with no impact
//            // because ViewMatchers would only start matching when action is performed on DataInteraction
//            onData(matcher).inAdapterView(withId(listViewId)).perform(typeText(""));
//        } catch (Exception e) {
//        }
//
//        int result = count;
//        count = 0;
//        return result;
//    }


    public static Matcher<View> withListSize (final int size) {
        return new TypeSafeMatcher<View> () {
            @Override public boolean matchesSafely (final View view) {
                return ((ListView) view).getCount () == size;
            }

            @Override public void describeTo (final Description description) {
                description.appendText ("ListView should have " + size + " items");
            }
        };
    }
}
