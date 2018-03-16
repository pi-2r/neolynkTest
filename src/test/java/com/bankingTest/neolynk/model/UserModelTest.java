package com.bankingTest.neolynk.model;



import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


/**
 * Created by zen on 16/03/18.
 */
public class UserModelTest extends AbstractIntegrationTest{

    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(UserModelTest.class);


    UserModel initUser;

    @Before
    public void init() {
        initUser=  createFakeUser();
    }
    @Test
    public void testInitUser() throws Exception {
        LOG.debug(initUser.toString());
        Assert.assertNotNull(initUser);

    }

    @Test
    public void testMinAge() throws Exception {
        if( initUser.getAge() > MIN_AGE){
            Assert.assertTrue(true);
        }
        else{
            Assert.assertTrue(false);
        }

    }

    @Test
    public void createNewFakeUser() throws Exception {
        UserModel newFakeUser = createFakeUser();
        LOG.debug(newFakeUser.toString());
        Assert.assertNotNull(newFakeUser);
    }

    @Test
    public void createAndSaveUser() throws Exception{
        UserModel newFakeUser = createFakeUser();
        LOG.debug(newFakeUser.toString());
        core.saveUserEntity(newFakeUser, newFakeUser.getIdUser());
        Assert.assertNotNull(core.getSpecificUser(newFakeUser.getIdUser()));
    }

    @Test
    public void checkIfUserNotExistById() throws Exception {
        UserModel newFakeUser = createFakeUser();
        core.saveUserEntity(newFakeUser, newFakeUser.getIdUser());
        Assert.assertNull(core.getSpecificUser(UUID.randomUUID()));
    }

    @Test
    public void createSaveAndDeleteUser() throws Exception {
        UserModel newFakeUser = createFakeUser();
        LOG.debug(newFakeUser.toString());
        core.saveUserEntity(newFakeUser, newFakeUser.getIdUser());
        //--- check if ok
        Assert.assertNotNull(core.getSpecificUser(newFakeUser.getIdUser()));
        //--- delete user
        core.delteSpecificUserById(newFakeUser.getIdUser());
        //-- check if is good == user ---> null
        Assert.assertNull(core.getSpecificUser(newFakeUser.getIdUser()));
    }

    @Test
    public void createSaveAndReadUser() throws Exception {
        UserModel newFakeUser = createFakeUser();
        LOG.debug(newFakeUser.toString());
        core.saveUserEntity(newFakeUser, newFakeUser.getIdUser());
        //--- check if ok
        UserModel specificUser = core.getSpecificUser(newFakeUser.getIdUser());

        Assert.assertEquals(newFakeUser.getEmail(), specificUser.getEmail());
    }

    @Test
    public void createSaveAndUpdateEmailUser() throws Exception {
        UserModel newFakeUser = createFakeUser();
        LOG.debug(newFakeUser.toString());
        String tmp =  newFakeUser.getEmail();
        core.saveUserEntity(newFakeUser, newFakeUser.getIdUser());

        //--- check if ok
        UserModel specificUser = core.getSpecificUser(newFakeUser.getIdUser());
        Assert.assertEquals(newFakeUser.getEmail(), specificUser.getEmail());

        //---change user email
        specificUser = core.editSpecificUserById(newFakeUser.getIdUser(), "email", "pierre.therrode@gmail.com");
        Assert.assertNotEquals(tmp, specificUser.getEmail());
        LOG.debug(specificUser.toString());
    }

    @Test
    public void listAllUser() throws Exception {
        int count = 0;
        //--- user 1
        UserModel newFakeUser = createFakeUser();
        LOG.debug(newFakeUser.toString());
        core.saveUserEntity(newFakeUser, newFakeUser.getIdUser());

        UserModel specificUser = core.getSpecificUser(newFakeUser.getIdUser());
        Assert.assertEquals(newFakeUser.getEmail(), specificUser.getEmail());

        //--- user 2
        UserModel newFakeUser2 = createFakeUser();
        LOG.debug(newFakeUser2.toString());
        core.saveUserEntity(newFakeUser2, newFakeUser2.getIdUser());

        UserModel specificUser2 = core.getSpecificUser(newFakeUser2.getIdUser());
        Assert.assertEquals(newFakeUser2.getEmail(), specificUser2.getEmail());

        //--- user 3
        UserModel newFakeUser3 = createFakeUser();
        LOG.debug(newFakeUser3.toString());
        String tmp =  newFakeUser3.getEmail();
        core.saveUserEntity(newFakeUser3, newFakeUser3.getIdUser());

        //--- check if ok
        UserModel specificUser3 = core.getSpecificUser(newFakeUser3.getIdUser());
        Assert.assertEquals(newFakeUser3.getEmail(), specificUser3.getEmail());

        //---change user email
        specificUser3 = core.editSpecificUserById(newFakeUser3.getIdUser(), "email", "pierre.therrode@gmail.com");
        Assert.assertNotEquals(tmp, specificUser3.getEmail());

        //--- user 4
        UserModel newFakeUser4 = createFakeUser();
        LOG.debug(newFakeUser.toString());
        core.saveUserEntity(newFakeUser4, newFakeUser4.getIdUser());
        //--- check if ok
        Assert.assertNotNull(core.getSpecificUser(newFakeUser4.getIdUser()));
        //--- delete user
        core.delteSpecificUserById(newFakeUser4.getIdUser());
        //-- check if is good == user ---> null
        Assert.assertNull(core.getSpecificUser(newFakeUser4.getIdUser()));

        //--- number of users
        for (Map.Entry me : core.getUserList().entrySet()) {
            count++;
            LOG.debug("===>Key: "+me.getKey() + " & Value: " + me.getValue());
        }
        Assert.assertEquals(count, 3);
    }

    @After
    public void deleteHasMap() {
        core.getUserList().clear();
    }

}
