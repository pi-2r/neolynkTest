package com.bankingTest.neolynk.core;

import com.bankingTest.neolynk.model.AccountModel;
import com.bankingTest.neolynk.model.UserModel;
import com.bankingTest.neolynk.utils.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by zen on 18/03/18.
 */
public class AbstractCore {

    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(AbstractCore.class);

    //---- record all user
    protected HashMap<UUID, AccountModel> accountList= new HashMap<>();
    //---- record all user
    protected HashMap<UUID, UserModel> userList= new HashMap<>();
    Tools tools =  new Tools();



}
