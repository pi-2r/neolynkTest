package com.bankingTest.neolynk.controller;


import com.bankingTest.neolynk.core.AccountCore;
import com.bankingTest.neolynk.core.UserCore;
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
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by zen on 16/03/18.
 */
@RestController
@RequestMapping("/user")
@Api(value="userController", description="Operation on user")
public class UserController {

    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserCore userCore;
    @Autowired
    private AccountCore accountCore;
    @Autowired
    private Tools tools;


    @ApiOperation(value = "View a list all users")
    @RequestMapping(value = "/listAllUser", method= RequestMethod.GET)
    public ResponseEntity<UserModel> listAllUser(){
        return new ResponseEntity(userCore.getUserList(), HttpStatus.OK);
    }

    @ApiOperation(value = "Search a user with an UUID")
    @RequestMapping(value = "/show/{id}", method= RequestMethod.GET)
    public ResponseEntity<UserModel> showProduct(@RequestParam(value = "userId")  String userId){
        if(tools.checkIfUUID(userId)) {
            return new ResponseEntity(userCore.getSpecificUser(UUID.fromString(userId)), HttpStatus.OK);
        }
        return new ResponseEntity("Bad request", HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "Add a user")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity creatUser(@RequestParam(value = "firstName")  String firstName,
                                    @RequestParam(value = "lastName")  String lastName,
                                    @RequestParam(value = "address", required = false)  String address,
                                    @RequestParam(value = "phoneNumber", required = false)  String phoneNumber,
                                    @RequestParam(value = "email", required = false)  String email,
                                    @RequestParam(value = "age", required = false)  Integer age){

        UserModel basicUser = new UserModel(firstName, lastName, address, phoneNumber,  email, age);
        userCore.saveUserEntity(basicUser, basicUser.getIdUser());
        return new ResponseEntity("User saved successfully", HttpStatus.OK);
    }


    @ApiOperation(value = "Update a user")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity editUser( @RequestParam(value = "userId")  String userId,
                                    @ApiParam(name = "keyName", value = "firstName, lastName, address, phoneNumber, email or age", defaultValue = "firstName", required = true)
                                    @RequestParam(value = "keyName")  String keyName,
                                    @RequestParam(value = "changeValue")  String changeValue){
        if(tools.checkIfUUID(userId)) {
            userCore.editSpecificUserById(UUID.fromString(userId), keyName, changeValue);
            return new ResponseEntity(userCore.getSpecificUser(UUID.fromString(userId)), HttpStatus.OK);
        }
        return new ResponseEntity("Bad request", HttpStatus.BAD_REQUEST);

    }

    @ApiOperation(value = "Delete a user")
    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@RequestParam(value = "userId")  String userId){
        if(tools.checkIfUUID(userId)) {
            userCore.delteSpecificUserById(UUID.fromString(userId));
            accountCore.deleteSpecificAccountByUserId(UUID.fromString(userId));
            return new ResponseEntity("User deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity("Bad request", HttpStatus.BAD_REQUEST);
    }

}
