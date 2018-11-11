package com.example.benji.homerepairapp;

import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;
import org.junit.Rule;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class HomeRepairAppTest {

    @Rule
    public ActivityTestRule<AccountCreation> mActivityTestRule = new ActivityTestRule<AccountCreation>(AccountCreation.class);
    private AccountCreation mActivity = null;
    private TextView text;

    @Before
    public void setUp() throws Exception{
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    @UiThreadTest
    public void checkUsername() throws Exception{
        text = mActivity.findViewById(R.id.usernameInput);
        text.setText("superuser");
        String name = text.getText().toString();
        assertNotNull("Username", name);
    }

    @Test
    @UiThreadTest
    public void checkEmail() throws Exception{
        text = mActivity.findViewById(R.id.emailInput);
        text.setText("superuser@super.com");
        String email = text.getText().toString();
        boolean validEmail = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        assertTrue("Email", validEmail);
    }

    @Test
    @UiThreadTest
    public void checkPhoneNumber() throws Exception{
        text = mActivity.findViewById(R.id.phoneInput);
        text.setText("6136136133");
        String phone = text.getText().toString();
        boolean validPhone = android.util.Patterns.PHONE.matcher(phone).matches() && phone.length() == 10;
        assertTrue("Phone Number", validPhone);
    }


    @Test
    @UiThreadTest
    public void checkFirstName() throws Exception{
        text = mActivity.findViewById(R.id.firstNameInput);
        text.setText("guy");
        String fName = text.getText().toString();
        assertNotNull("First name", fName);

    }

    @Test
    @UiThreadTest
    public void checkPasswords() throws Exception{
        TextView text2;
        text = mActivity.findViewById(R.id.passwordInput);
        text2 = mActivity.findViewById(R.id.cPasswordInput);
        text.setText("super613316");
        text2.setText("super613316");
        assertEquals("Same password", text.getText().toString(), text2.getText().toString());

    }




}
