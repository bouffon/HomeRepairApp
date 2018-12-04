package com.example.benji.homerepairapp;

import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SetProviderRatingTest {
    @Rule
    public ActivityTestRule<SetProviderRating> mActivityTestRule = new ActivityTestRule<SetProviderRating>(SetProviderRating.class);

    private SetProviderRating mActivity = null;
    private TextView time1, time2;

    @Before
    public void setUp() throws Exception{
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    @UiThreadTest
    //test for if rating comment is retrievable
    public void checkRetrieveComment() throws Exception{

        time1 = mActivity.findViewById(R.id.comment);
        time1.setText("Guy is wonderful");
        assertEquals("Is rating comment retrievable", "Guy is wonderful", time1.getText().toString());
    }
}
