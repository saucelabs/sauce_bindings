package com.saucelabs.simplesauce.unit;

import com.saucelabs.simplesauce.SauceEnvironmentVariablesNotSetException;
import com.saucelabs.simplesauce.SauceSession;
import com.saucelabs.simplesauce.interfaces.EnvironmentManager;
import com.saucelabs.simplesauce.interfaces.RemoteDriverInterface;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class SauceEnvironmentVariableNotSetExceptionTest {

    private SauceSession fakeSauceSession;

    @Before
    public void setUp() {
        RemoteDriverInterface fakeRemoteDriver = mock(RemoteDriverInterface.class);
        EnvironmentManager fakeEnvironmentManager = mock(EnvironmentManager.class);
        fakeSauceSession = new SauceSession(fakeRemoteDriver, fakeEnvironmentManager);
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
