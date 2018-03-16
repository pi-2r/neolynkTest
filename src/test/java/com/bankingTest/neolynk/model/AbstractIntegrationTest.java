package com.bankingTest.neolynk.model;


import com.bankingTest.neolynk.core.UserCore;
import com.bankingTest.neolynk.enums.AccountTypeEnum;
import com.bankingTest.neolynk.utils.Tools;
import com.github.javafaker.Faker;
import org.junit.rules.TestName;
import org.junit.Rule;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by zen on 16/03/18.
 */
public class AbstractIntegrationTest {

    //--- const
    protected static final int MIN_AGE = 18;
    protected static final int MAX_AGE =70;
    protected static final int NUMBER_YEAR =99;



    @Rule
    public TestName testName = new TestName();

    //--- core banking
    public UserCore core = new UserCore();
    public Tools tools = new Tools();

    /**
     * Create fake user
     * @return
     */
    protected UserModel createFakeUser() {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String userEmail= firstName + "." + lastName +"@"+faker.company().suffix()+".com";
        String address = faker.address().fullAddress();
        String phoneNumber = faker.phoneNumber().cellPhone();
        int age = randomUserAge();

        UserModel basicUser = new UserModel(firstName, lastName, address, phoneNumber,  userEmail, age);

        return basicUser;
    }

    /**
     * get a new age for a new user
     * @return
     */
    private static int randomUserAge() {
        Random r = new Random();
        return r.nextInt((MAX_AGE - MIN_AGE) + 1) + MIN_AGE;
    }


    protected AccountModel createFakeAccount() {
        Faker faker = new Faker();
        Date now = new Date();
        String actualDate = new SimpleDateFormat("yyyy-MM-dd").format(now);

        Double balance = faker.random().nextDouble();
        AccountModel accountModel = new AccountModel(actualDate, balance, AccountTypeEnum.LIVRET_A);
        return accountModel;
    }

}
