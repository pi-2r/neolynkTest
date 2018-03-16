package com.bankingTest.neolynk.enums;

/**
 * Created by zen on 16/03/18.
 */
public enum AccountTypeEnum {

    LIVRET_A(1, "LIVRET_A"),
    LIVRET_B(2, "LIVRET_B"),
    LIVRET_GOLD(3, "LIVRET_GOLD");

    private final int id;
    private final String accountName;

    AccountTypeEnum(int id, String accountName) {
        this.id = id;
        this.accountName = accountName;
    }


    public int getId() {return id;}
    public String getaccountName() {return accountName;}

}
