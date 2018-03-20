package com.bankingTest.neolynk.controller;

import com.bankingTest.neolynk.core.AccountCore;
import com.bankingTest.neolynk.core.UserCore;
import com.bankingTest.neolynk.enums.AccountTypeEnum;
import com.bankingTest.neolynk.model.AccountModel;
import com.bankingTest.neolynk.model.UserModel;
import com.bankingTest.neolynk.utils.Tools;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


/**
 * Created by zen on 16/03/18.
 */
@RestController
@RequestMapping("/account")
@Api(value="accountController", description="Operation on account")
public class accountController {

    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(accountController.class);

    @Autowired
    private UserCore userCore;
    @Autowired
    private AccountCore accountCore;
    @Autowired
    private Tools tools;

    @ApiOperation(value = "View a list all accounts")
    @RequestMapping(value = "/listAllAccounts", method= RequestMethod.GET)
    public ResponseEntity<AccountModel> listAllAccount(){
        return new ResponseEntity(accountCore.getAccountList(), HttpStatus.OK);
    }

    @ApiOperation(value = "Add a account")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity creatUser(@RequestParam(value = "balance")  Double balance,
                                    @ApiParam(name = "typeOfAccount", value = "LIVRET_A, LIVRET_B or LIVRET_GOLD", defaultValue = "LIVRET_A", required = true)
                                    @RequestParam(value = "typeOfAccount") String typeOfAccount,
                                    @RequestParam(value = "userId", required = false)  String userId){
        Date now = new Date();
        String actualDate = new SimpleDateFormat("yyyy-MM-dd").format(now);
        AccountModel accountModel = new AccountModel(actualDate, balance, AccountTypeEnum.forName(typeOfAccount));
        if (userId !=null && tools.checkIfUUID(userId)) {
            if (userCore.getSpecificUser(UUID.fromString(userId)) != null){
                    accountCore.saveAccountEntityWithUser(accountModel, accountModel.getIdAccount(), UUID.fromString(userId));
                    return new ResponseEntity("Account with user is saved successfully", HttpStatus.CREATED);
                }
                else {
                    return new ResponseEntity("User doesn't exist", HttpStatus.BAD_REQUEST);
                }
        }else {
            accountCore.saveAccountEntity(accountModel, accountModel.getIdAccount());
            return new ResponseEntity("Account with no user is saved successfully", HttpStatus.CREATED);
        }

    }

    @ApiOperation(value = "Update a account")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity editUser( @RequestParam(value = "UUID")  String uuid,
                                    @ApiParam(name = "keyName", value = "balance, typeOfAccount(LIVRET_A, LIVRET_B or LIVRET_GOLD) or userId", required = true)
                                    @RequestParam(value = "keyName")  String keyName,
                                    @RequestParam(value = "changeValue")  String changeValue) {

        if (uuid != null && tools.checkIfUUID(uuid)) {
            if ("userId".equals(keyName)){
                if (tools.checkIfUUID(changeValue)) {
                    if (userCore.getSpecificUser(UUID.fromString(changeValue)) != null) {
                        accountCore.editSpecificAccountById(UUID.fromString(uuid), keyName, changeValue);
                        return new ResponseEntity(accountCore.getSpecificAccount(UUID.fromString(uuid)), HttpStatus.OK);
                    }
                }else {
                    return new ResponseEntity("User doesn't exist", HttpStatus.BAD_REQUEST);
                }
            }
            else {
                accountCore.editSpecificAccountById(UUID.fromString(uuid), keyName, changeValue);
                return new ResponseEntity(accountCore.getSpecificAccount(UUID.fromString(uuid)), HttpStatus.OK);
            }
        }
        return new ResponseEntity("Error during update", HttpStatus.BAD_REQUEST);
    }


    @ApiOperation(value = "Search all accounts by user")
    @RequestMapping(value = "/showAllAccountsByUser/{id}", method= RequestMethod.GET)
    public ResponseEntity showProduct(@RequestParam(value = "userId")  String userId){
        if(tools.checkIfUUID(userId)) {
            if (userCore.getSpecificUser(UUID.fromString(userId)) != null) {
                return new ResponseEntity(accountCore.getAllAccountPerUser(UUID.fromString(userId)), HttpStatus.OK);
            }else {
                return new ResponseEntity("User doesn't exist", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity("Bad request", HttpStatus.BAD_REQUEST);
    }


    @ApiOperation(value = "Get sum of Balance by user")
    @RequestMapping(value = "/sumOfBalanceByUser/{id}", method= RequestMethod.GET)
    public ResponseEntity sumOfBalanceByUser(@RequestParam(value = "userId")  String userId){
        if(tools.checkIfUUID(userId)) {
            if (userCore.getSpecificUser(UUID.fromString(userId)) != null) {
                return new ResponseEntity(accountCore.getSumBalanceOfAllAccountPerUser(UUID.fromString(userId)), HttpStatus.OK);
            }else {
                return new ResponseEntity("User doesn't exist", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity("Bad request", HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "Delete a user")
    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteAccountByIdAccount(@RequestParam(value = "accountId")  String accountId){
        if(tools.checkIfUUID(accountId)) {
            accountCore.deleteSpecificAccountByUserId(UUID.fromString(accountId));
            return new ResponseEntity("Account deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity("Bad request", HttpStatus.BAD_REQUEST);
    }

}
