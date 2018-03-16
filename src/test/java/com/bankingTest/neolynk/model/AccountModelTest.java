package com.bankingTest.neolynk.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zen on 16/03/18.
 */
public class AccountModelTest extends AbstractIntegrationTest {

    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(UserModelTest.class);

    AccountModel accountInitModel;

    @Before
    public void init() {
        accountInitModel=  createFakeAccount();
    }

    @Test
    public void testInitAccount() throws Exception {
        LOG.debug(accountInitModel.toString());
        Assert.assertNotNull(accountInitModel);
    }


    @Test
    public void createNewFakeAccount() throws Exception {
        AccountModel newAccountModel = createFakeAccount();
        LOG.debug(newAccountModel.toString());
        Assert.assertNotNull(newAccountModel);
    }
}
