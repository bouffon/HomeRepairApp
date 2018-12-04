package com.example.benji.homerepairapp;

import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;
import org.junit.Rule;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ServiceProviderInformationTest {

    @Rule
    public ActivityTestRule<ServiceProviderInformation> mActivityTestRule2 = new ActivityTestRule<ServiceProviderInformation>(ServiceProviderInformation.class);

    private ServiceProviderInformation mActivity2 = null;
    private TextView text;

    @Before
    public void setUp() throws Exception{
        mActivity2 = mActivityTestRule2.getActivity();
    }

    @Test
    @UiThreadTest
    //test for company name input when completing service provider profile
    public void checkCompanyName() throws Exception{
        text = mActivity2.findViewById(R.id.companyName);
        text.setText("guy's company");
        String fName = text.getText().toString();
        assertNotNull("Company name", fName);
    }

    @Test
    @UiThreadTest
    //test for the brief description input when completing service provider profile
    public void checkDescription() throws Exception{
        text = mActivity2.findViewById(R.id.comment);
        text.setText("Guy is a legend");
        String fName = text.getText().toString();
        assertNotNull("Brief description", fName);
    }
}
