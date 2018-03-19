package com.bankingTest.neolynk.model;


import com.bankingTest.neolynk.core.AccountCore;
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
    private static final int MAX_AGE =70;

    private static final int MIN_MONEY = 100;
    private static final int MAX_MONEY = 1_00_000;

    @Rule
    public TestName testName = new TestName();

    //--- core banking
    public UserCore core = new UserCore();
    public AccountCore accountCore = new AccountCore();
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
        int age = randomNumber(MAX_AGE, MIN_AGE);

        UserModel basicUser = new UserModel(firstName, lastName, address, phoneNumber,  userEmail, age);

        return basicUser;
    }

    /**
     * get random number
     * @return
     */
    private static int randomNumber(int max, int min) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    /**
     * Create fake account
     * @return
     */
    protected AccountModel createFakeAccount() {
        Faker faker = new Faker();
        Date now = new Date();
        String actualDate = new SimpleDateFormat("yyyy-MM-dd").format(now);

        Double balance = Double.valueOf(randomNumber(MAX_MONEY, MIN_MONEY));
        AccountModel accountModel = new AccountModel(actualDate, balance, AccountTypeEnum.LIVRET_A);
        return accountModel;
    }

}
