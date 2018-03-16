package com.bankingTest.neolynk.core;

import com.bankingTest.neolynk.model.AccountModel;
import com.bankingTest.neolynk.model.UserModel;
import com.bankingTest.neolynk.utils.Tools;

import java.util.*;

/**
 * Created by zen on 16/03/18.
 */
public class UserCore {

    //---- record all user
    private HashMap<UUID, UserModel> userList= new HashMap<>();

    //---- record account
    public Map<UUID, AccountModel> accountList = Collections.synchronizedMap(new HashMap<UUID, AccountModel>());

    Tools tools =  new Tools();

    /**
     * get all user list
     * @return
     */
    public HashMap<UUID, UserModel> getUserList(){
        return userList;
    }

    /**
     * Save user into HasMap
     * @param userModel
     * @param uuid
     * @return
     */
    public UserModel saveUserEntity (UserModel userModel, UUID uuid){
        return userList.put(uuid, userModel);
    }

    /**
     * find user by id
     * @param value
     * @return
     */
    public UserModel getSpecificUser(UUID value) {
        if (checkIfUserExist(value)){
           return userList.get(value);
        }
        return null;
    }

    /**
     * Remove user by id
     * @param value
     * @return
     */
    public UserModel delteSpecificUserById(UUID value) {
        if (checkIfUserExist(value)){
            return userList.remove(value);
        }
        return null;
    }

    /**
     * Check if user exist
     * @param value
     * @return
     */
    private Boolean checkIfUserExist(UUID value){
        if (userList.containsKey(value)) {
            return true;
        }
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
                if (tools.IsInt_ByException(changeValue)) {
                    editUser.setAge(Integer.valueOf(changeValue));
                }
            }
            //--- save update about user
           return userList.put(value, editUser);
        }
        return null;
    }


}
