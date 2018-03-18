package com.bankingTest.neolynk.core;

import com.bankingTest.neolynk.enums.AccountTypeEnum;
import com.bankingTest.neolynk.model.AccountModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by zen on 17/03/18.
 */
public class AccountCore extends AbstractCore {

    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(AccountCore.class);

    private UserCore userCore= new UserCore();
    //---- regex
    private static final String FOUND_USER_ID = "(?<=userId=)(.*)(?=\\})";
    private static final String ID_ACCOUNT = "(?<=idAccount=)(.*)(?=, dateOfCreation)";

    /**
     * Return all information account
     * @return
     */
    public HashMap<UUID, AccountModel> getAccountList(){
        LOG.debug("need all account in list");
        return accountList;
    }

    /**
     * Record a account without user
     * @param accountModel
     * @param uuid
     * @return
     */
    public AccountModel saveAccountEntity (AccountModel accountModel, UUID uuid){
        LOG.debug("save account without user in list with uuid:{}", uuid);
        return accountList.put(uuid, accountModel);
    }

    /**
     * return value for a specific account
     * @param value
     * @return
     */
    public AccountModel getSpecificAccount(UUID value) {
        if (checkIfAccountExist(value)){
            LOG.debug("get account with {} uuid", value);
            LOG.info(accountList.get(value).toString());
            return accountList.get(value);
        }
        LOG.debug("account with {} uuid not foud", value);
        return null;
    }

    /**
     * Check if the account existe
     * @param value
     * @return
     */
    private Boolean checkIfAccountExist(UUID value){
        if (accountList.containsKey(value)) {
            LOG.debug("account with {} uuid exist", value);
            return true;
        }
        LOG.debug("account with {} uuid doesn't exist", value);
        return false;
    }

    /**
     * Record a account directly with a user
     * @param accountModel
     * @param uuid
     * @param userId
     * @return
     */
    public AccountModel saveAccountEntityWithUser(AccountModel accountModel, UUID uuid, UUID userId){
        LOG.debug("save new account ({}) with a new user: {}", uuid, userId);
        accountModel.setUserId(userId);
        return accountList.put(uuid, accountModel);
    }

    /**
     * Delete account
     * @param value
     * @return
     */
    public AccountModel deleteSpecificAccountById(UUID value) {
        if (checkIfAccountExist(value)){
            LOG.debug("delete account with {} uuid ", value);
            return accountList.remove(value);
        }
        LOG.debug("can't delete account with {} uuid ", value);
        return null;
    }

    /**
     * Delete account from specific user id
     * @param userid
     * @return
     */
    public AccountModel deleteSpecificAccountByUserId(UUID userid) {

        Set set = accountList.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry)iterator.next();

            if (tools.regexFoundString(FOUND_USER_ID, mentry.getValue().toString())){
                LOG.info("found user in account list: " + mentry.getValue().toString());
                //--- extract uuid account and delete it
                String idAccount = tools.regexFoundAndExtractString(ID_ACCOUNT, mentry.getValue().toString());
                LOG.info("found id account: " + idAccount);
                deleteSpecificAccountById(UUID.fromString(idAccount));
            }
        }
        LOG.debug("can't delete account with {} uuid ", userid);
        return null;
    }

    /**
     * Edit specific account
     * @param value
     * @param keyName
     * @param changeValue
     * @return
     */
    public AccountModel editSpecificAccountById(UUID value, String keyName, String changeValue) {
        if (checkIfAccountExist(value)){
            AccountModel accountEdit = accountList.get(value);

            LOG.debug("change {} parameter with this value: {}, for the account with {} uuid", changeValue, keyName, value);

            if ("balance".equals(keyName)) {
                if( tools.checkIfDouble(changeValue)) {
                    accountEdit.setBalance(Double.parseDouble(changeValue));
                }
            }
            else if ("typeOfAccount".equals(keyName)) {
                if(tools.checkIfAccountTypeExist(changeValue)) {
                    accountEdit.setTypeOfAccount(AccountTypeEnum.forName(changeValue));
                }
            }
            else if ("userId".equals(keyName)) {
                if(tools.checkIfUUID(changeValue)) {
                    //-- check if the user exist
                    //if (userCore.getSpecificUser(UUID.fromString(changeValue)) != null) {
                        accountEdit.setUserId(UUID.fromString(changeValue));
                    //}
                }
            }

            //--- save update about account
            return accountList.put(value, accountEdit);

        }
        LOG.debug("Can't change {} parameter with this value: {}, for the account with {} uuid", value, keyName, changeValue);
        return null;
    }
}
