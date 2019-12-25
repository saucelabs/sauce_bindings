package com.saucelabs.simplesauce;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class SauceEnvironmentVariableNotSetExceptionTest {

    private SauceSession fakeSauceSession;

    @Before
    public void setUp() {
        EnvironmentManager fakeEnvironmentManager = mock(EnvironmentManager.class);
        fakeSauceSession = new SauceSession(fakeEnvironmentManager);
    }

    @Test(expected = SauceEnvironmentVariablesNotSetException.class)
    public void getUserName_usernameNotSetInEnvironmentVariable_throwsException() {
        fakeSauceSession.getUserName();
    }

    @Test(expected = SauceEnvironmentVariablesNotSetException.class)
    public void getAccessKey_keyNotSetInEnvironmentVariable_throwsException()  {
        fakeSauceSession.getAccessKey();
    }
}
