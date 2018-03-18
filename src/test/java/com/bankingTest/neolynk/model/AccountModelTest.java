package com.bankingTest.neolynk.model;

import org.junit.After;
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

    @Test
    public void createAndSaveAccountWithoutUser() throws Exception {
        AccountModel newAccountModel = createFakeAccount();
        LOG.debug(newAccountModel.toString());
        Assert.assertNotNull(newAccountModel);
        accountCore.saveAccountEntity(newAccountModel, newAccountModel.getIdAccount());
        Assert.assertNotNull(accountCore.getSpecificAccount(newAccountModel.getIdAccount()));
    }

    @Test
    public void createAndSaveAccountWithUser() throws Exception {
        //---- create user
        UserModel newFakeUser = createFakeUser();
        LOG.debug(newFakeUser.toString());
        core.saveUserEntity(newFakeUser, newFakeUser.getIdUser());
        Assert.assertNotNull(core.getSpecificUser(newFakeUser.getIdUser()));

        //--- create account
        AccountModel newAccountModel = createFakeAccount();
        LOG.debug(newAccountModel.toString());
        Assert.assertNotNull(newAccountModel);

        //--- link account with a user
        accountCore.saveAccountEntityWithUser(newAccountModel, newAccountModel.getIdAccount(), newFakeUser.getIdUser());
        AccountModel tmpAccount =  accountCore.getSpecificAccount(newAccountModel.getIdAccount());
        Assert.assertNotNull(accountCore.getSpecificAccount(newAccountModel.getIdAccount()));
        Assert.assertEquals(tmpAccount.getUserId(), newFakeUser.getIdUser());
    }

    @Test
    public void createSaveAndDeleteAccountWithoutUser() throws Exception {
        //--- create account
        AccountModel newAccountModel = createFakeAccount();
        LOG.debug(newAccountModel.toString());
        Assert.assertNotNull(newAccountModel);

        //--- save account
        accountCore.saveAccountEntity(newAccountModel, newAccountModel.getIdAccount());
        Assert.assertNotNull(accountCore.getSpecificAccount(newAccountModel.getIdAccount()));

        //--- delete account
        accountCore.deleteSpecificAccountById(newAccountModel.getIdAccount());
        Assert.assertNull(accountCore.getSpecificAccount(newAccountModel.getIdAccount()));
    }

    @Test
    public void createSaveAndDeleteAccountWithUser() throws Exception {
        //---- create user
        UserModel newFakeUser = createFakeUser();
        LOG.debug(newFakeUser.toString());
        core.saveUserEntity(newFakeUser, newFakeUser.getIdUser());
        Assert.assertNotNull(core.getSpecificUser(newFakeUser.getIdUser()));

        //--- create account
        AccountModel newAccountModel = createFakeAccount();
        LOG.debug(newAccountModel.toString());
        Assert.assertNotNull(newAccountModel);

        //--- link account with a user
        accountCore.saveAccountEntityWithUser(newAccountModel, newAccountModel.getIdAccount(), newFakeUser.getIdUser());
        AccountModel tmpAccount =  accountCore.getSpecificAccount(newAccountModel.getIdAccount());
        Assert.assertNotNull(accountCore.getSpecificAccount(newAccountModel.getIdAccount()));
        Assert.assertEquals(tmpAccount.getUserId(), newFakeUser.getIdUser());

        //--- delete account
        accountCore.deleteSpecificAccountByUserId(newFakeUser.getIdUser());
        //Assert.assertNull(accountCore.getSpecificAccount(newAccountModel.getIdAccount()));

        //--- user style alive
        Assert.assertNotNull(core.getSpecificUser(newFakeUser.getIdUser()));
    }

    @Test
    public void createSaveAndUpdateAccount() throws Exception {
        //--- create account
        AccountModel newAccountModel = createFakeAccount();
        LOG.debug(newAccountModel.toString());
        Assert.assertNotNull(newAccountModel);

        //--- save account
        accountCore.saveAccountEntity(newAccountModel, newAccountModel.getIdAccount());
        Assert.assertNotNull(accountCore.getSpecificAccount(newAccountModel.getIdAccount()));

        //--- edit account
        accountCore.editSpecificAccountById(newAccountModel.getIdAccount(), "typeOfAccount", "LIVRET_B");

        //--- get and check account
        AccountModel tmp = accountCore.getSpecificAccount(newAccountModel.getIdAccount());
        Assert.assertNotNull(tmp);
        Assert.assertEquals(tmp.getTypeOfAccount().toString(),"LIVRET_B");
    }

    @Test
    public void createSaveUpdateAndDeleteAccount() throws Exception {
        //--- create account
        AccountModel newAccountModel = createFakeAccount();
        LOG.debug(newAccountModel.toString());
        Assert.assertNotNull(newAccountModel);

        //--- save account
        accountCore.saveAccountEntity(newAccountModel, newAccountModel.getIdAccount());
        Assert.assertNotNull(accountCore.getSpecificAccount(newAccountModel.getIdAccount()));

        //--- edit account
        accountCore.editSpecificAccountById(newAccountModel.getIdAccount(), "typeOfAccount", "LIVRET_B");

        //--- get and check account
        AccountModel tmp = accountCore.getSpecificAccount(newAccountModel.getIdAccount());
        Assert.assertNotNull(tmp);
        Assert.assertEquals(tmp.getTypeOfAccount().toString(),"LIVRET_B");

        //--- delete account
        accountCore.deleteSpecificAccountById(newAccountModel.getIdAccount());
        Assert.assertNull(accountCore.getSpecificAccount(newAccountModel.getIdAccount()));
    }


    @Test
    public void createSaveAndUpdateAccountWithTwoUser() throws Exception {
        //---- create user1
        UserModel newFakeUser1 = createFakeUser();
        LOG.debug(newFakeUser1.toString());
        core.saveUserEntity(newFakeUser1, newFakeUser1.getIdUser());
        Assert.assertNotNull(core.getSpecificUser(newFakeUser1.getIdUser()));

        //--- create account
        AccountModel newAccountModel = createFakeAccount();
        LOG.debug(newAccountModel.toString());
        Assert.assertNotNull(newAccountModel);

        //--- link account with a user
        accountCore.saveAccountEntityWithUser(newAccountModel, newAccountModel.getIdAccount(), newFakeUser1.getIdUser());
        AccountModel tmpAccount =  accountCore.getSpecificAccount(newAccountModel.getIdAccount());
        Assert.assertNotNull(accountCore.getSpecificAccount(newAccountModel.getIdAccount()));
        Assert.assertEquals(tmpAccount.getUserId(), newFakeUser1.getIdUser());

        //---- create user2
        UserModel newFakeUser2 = createFakeUser();
        LOG.debug(newFakeUser2.toString());
        core.saveUserEntity(newFakeUser2, newFakeUser2.getIdUser());
        Assert.assertNotNull(core.getSpecificUser(newFakeUser2.getIdUser()));
        AccountModel tmpo = accountCore.getSpecificAccount(newAccountModel.getIdAccount());
        LOG.info("===============++++++> user id account: " +tmpo.getUserId().toString());
        LOG.info("===============++++++> new user: " +newFakeUser2.getIdUser().toString());
        //--- edit account
        accountCore.editSpecificAccountById(newAccountModel.getIdAccount(), "userId",
                newFakeUser2.getIdUser().toString());

        //--- get and check account
        AccountModel tmp = accountCore.getSpecificAccount(newAccountModel.getIdAccount());
        Assert.assertNotNull(tmp);
        Assert.assertEquals(tmp.getUserId().toString(),newFakeUser2.getIdUser().toString());
    }
    @After
    public void deleteHasMap() {
        accountCore.getAccountList().clear();
    }

}
