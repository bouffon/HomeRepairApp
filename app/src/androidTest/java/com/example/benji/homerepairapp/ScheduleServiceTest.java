package com.example.benji.homerepairapp;

import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ScheduleServiceTest {
    @Rule
    public ActivityTestRule<ScheduleService> mActivityTestRule = new ActivityTestRule<ScheduleService>(ScheduleService.class);

    private ScheduleService mActivity = null;
    private TextView time1, time2;

    @Before
    public void setUp() throws Exception{
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    @UiThreadTest
    //test for if convert time to int works
    public void checkTimeConvertToInt() throws Exception{

        String time = "10 : 00";
        assertEquals("Convert time time to String", 1000,mActivity.timeConvert(time));
    }
}
