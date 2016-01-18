package com.udacity.gradle.builditbigger.joke.show;

import android.app.Application;
import android.test.ApplicationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void testJoker() {
        new JokeFetchTestTask(new JokeFetchTestTask.OnJokeFetchedListener() {
            @Override
            public void onJokeFetched(String joke) {
                assertNotNull(joke);
                assertTrue(joke.length() > 0);
            }
        }).execute(getContext().getString(R.string.app_name), getContext().getString(R.string.root_url));
    }
}