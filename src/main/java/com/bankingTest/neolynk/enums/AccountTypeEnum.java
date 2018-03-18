package com.bankingTest.neolynk.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zen on 16/03/18.
 */

public enum AccountTypeEnum {

    LIVRET_A("LIVRET_A: small client"),
    LIVRET_B("LIVRET_B: correct client"),
    LIVRET_GOLD("LIVRET_GOLD: big client");

    private static final Map<String, AccountTypeEnum> accountType =new HashMap<String, AccountTypeEnum>();

    static {
        for (AccountTypeEnum value : EnumSet.allOf(AccountTypeEnum.class)) {
            accountType.put(value.name(), value);
        }
    }

    private String descrption;

    AccountTypeEnum(String descrption) {
        this.descrption = descrption;
    }

    public static AccountTypeEnum forName(String name) {return accountType.get(name);}
    public static String descriptionName(String name) {return accountType.get(name).descrption;}

}

