package com.bankingTest.neolynk.core;

import com.bankingTest.neolynk.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by zen on 16/03/18.
 */
@Service
public class UserCore extends AbstractCore {

    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(UserCore.class);

    /**
     * get all user list
     * @return
     */
    public HashMap<UUID, UserModel> getUserList(){
        LOG.debug("need all user in list");
        return userList;
    }

    /**
     * Save user into HasMap
     * @param userModel
     * @param uuid
     * @return
     */
    public UserModel saveUserEntity (UserModel userModel, UUID uuid){
        LOG.debug("save user in list with uuid:{}", uuid);
        return userList.put(uuid, userModel);
    }

    /**
     * find user by id
     * @param value
     * @return
     */
    public UserModel getSpecificUser(UUID value) {
        if (checkIfUserExist(value)){
            LOG.debug("get user with {} uuid", value);
           return userList.get(value);
        }
        LOG.debug("user with {} uuid not foud", value);
        return null;
    }

    /**
     * Remove user by id
     * @param value
     * @return
     */
    public UserModel delteSpecificUserById(UUID value) {
        if (checkIfUserExist(value)){
            LOG.debug("delete user with {} uuid ", value);
            return userList.remove(value);
        }
        LOG.debug("can't delete user with {} uuid ", value);
        return null;
    }

    /**
     * Check if user exist
     * @param value
     * @return
     */
    public Boolean checkIfUserExist(UUID value){
        if (userList.containsKey(value)) {
            LOG.debug("user with {} uuid exist", value);
            return true;
        }
        LOG.debug("user with {} uuid doesn't exist", value);
        return false;
    }

    /**
     * update User Information
     * @param value
     * @param keyName
     * @param changeValue
     * @return
     */
    public UserModel editSpecificUserById(UUID value, String keyName, String changeValue) {
        if (checkIfUserExist(value)){
            UserModel editUser = userList.get(value);

            LOG.debug("change {} parameter with this value: {}, for the user with {} uuid", changeValue, keyName, value);

            if ("firstName".equals(keyName)){
                editUser.setFirstName(changeValue);
            }else if ("lastName".equals(keyName)) {
                editUser.setLastName(changeValue);
            }else if ("address".equals(keyName)) {
                editUser.setAddress(changeValue);
            }else if ("phoneNumber".equals(keyName)) {
                editUser.setPhoneNumber(changeValue);
            }else if ("email".equals(keyName)) {
                editUser.setEmail(changeValue);
            }else if ("age".equals(keyName)) {
                if (tools.checkIfInteger(changeValue)) {
                    editUser.setAge(Integer.valueOf(changeValue));
                }
            }
            //--- save update about user
           return userList.put(value, editUser);
        }
        LOG.debug("Can't change {} parameter with this value: {}, for the user with {} uuid", value, keyName, changeValue);
        return null;
    }

}
