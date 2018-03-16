package com.bankingTest.neolynk.utils;

import java.util.UUID;

/**
 * Created by zen on 16/03/18.
 */
public class Tools {

    public boolean IsInt_ByException(String str)
    {
        try
        {
            Integer.parseInt(str);
            return true;
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
    }

    public String randomStringId() {
       return String.valueOf(UUID.randomUUID()).replaceAll("-","");
    }
}
