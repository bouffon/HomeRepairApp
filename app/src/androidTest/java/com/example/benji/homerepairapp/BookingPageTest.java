package com.example.benji.homerepairapp;

import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class BookingPageTest {

    @Rule
    public ActivityTestRule<BookingPage> mActivityTestRule = new ActivityTestRule<BookingPage>(BookingPage.class);

    private BookingPage mActivity = null;
    private TextView time1, time2;

    @Before
    public void setUp() throws Exception{
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    @UiThreadTest
    //test for if the two entered times are unique
    public void checkUniqueTimes() throws Exception{

        time1 = mActivity.findViewById(R.id.day1Time);
        time2 = mActivity.findViewById(R.id.day2Time);
        time1.setText("10 : 00");
        time2.setText("11 : 00");
        assertNotEquals("Testing if times are different", time1.getText().toString(), time2.getText().toString());
    }

    @Test
    @UiThreadTest
    //test for if the second time is greater than the first time
    public void checkAcceptableTimes() throws Exception{

        time1 = mActivity.findViewById(R.id.day1Time);
        time2 = mActivity.findViewById(R.id.day2Time);
        time1.setText("10 : 00");
        time2.setText("11 : 00");
        boolean acceptableTime = timeConvert(time2.getText().toString()) > timeConvert(time1.getText().toString());
        assertTrue(acceptableTime);
    }

    @Test
    @UiThreadTest
    //test for a time was entered
    public void checkEnteredTime() throws Exception{
        time1 = mActivity.findViewById(R.id.day1Time);
        time1.setText("10 : 00");
        String time = time1.getText().toString();
        assertNotNull("Time was entered", time);
    }

    private int timeConvert(String time){

        return (1000*(time.charAt(0)-'0') + 100*(time.charAt(1)-'0') + 10*(time.charAt(5)-'0')+ time.charAt(6)-'0');
    }
}
