package com.bankingTest.neolynk.utils;

import com.bankingTest.neolynk.enums.AccountTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.UUID;

/**
 * Created by zen on 16/03/18.
 */
public class Tools {

    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(Tools.class);

    /**
     * Check if String can be cast in integer
     * @param str
     * @return
     */
    public boolean checkIfInteger(String str) {
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

    /**
     * Check if String can be cast in UUID
     * @param uuidValue
     * @return
     */
    public boolean checkIfUUID(String uuidValue) {
        try{
            UUID.fromString(uuidValue);
            return true;
        } catch (IllegalArgumentException exception){
            return false;
        }
    }

    /**
     * check if String can be cast in double
     * @param doubleValue
     * @return
     */
    public boolean checkIfDouble(String doubleValue){
        try
        {
            Double.parseDouble(doubleValue);
            return true;
        }
        catch(NumberFormatException e)
        {
            return false;
        }
    }


    /**
     * Check if the account exist
     * @param typeOfAccount
     * @return
     */
    public Boolean checkIfAccountTypeExist (String typeOfAccount) {
        if(AccountTypeEnum.forName(typeOfAccount) != null) {
            return true;
        }

        return false;
    }

    /**
     * Check if the user get a description account
     * @param typeOfAccount
     * @return
     */
    public String descriptionOfAccountType(String typeOfAccount) {
        if( checkIfAccountTypeExist(typeOfAccount)) {
            return AccountTypeEnum.descriptionName(typeOfAccount);
        }
        return null;
    }

    /**
     * detect string with regex
     * @param regex
     * @param foundString
     * @return
     */
    public static Boolean regexFoundString(String regex, String foundString){

        final Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        final Matcher matcher = pattern.matcher(foundString);

        if (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                LOG.info("Group " + i + ": " + matcher.group(i));
                return true;
            }
        }
        return false;
    }

    /**
     * detect and extract value with regex
     * @param regex
     * @param foundString
     * @return
     */
    public static String regexFoundAndExtractString(String regex, String foundString){

        final Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        final Matcher matcher = pattern.matcher(foundString);

        if (matcher.find()) {
           return matcher.group(0);
        }
        return null;
    }

    private Double calcul(String value, Double oldBalance) {
        String tmp = value.trim();
        //--- detect "+"
        double num1 =0;
        double num2= 0;

            String [] operands = tmp.replaceAll("\\s+", "").split("[\\+\\-\\/\\*\\%\\^]");

            String operator = tmp.replaceAll("(\\s+|\\d+|\\.)", "");

            Double [] numbers = new Double[operands.length];
            for (int i = 0; i < operands.length; i++)
            {
                numbers[i] = new Double(operands[i]);
            }

            num1 = numbers[0];
            num2 = numbers[1];

            double result = 0;

            switch (operator)
            {
                case "+":
                    result = add(num1, num2);
                    break;
                case "-":
                default:
                    System.out.println("Not valid.");
            }



        return oldBalance;
    }

    public static double add(double num1, double num2)
    {
        double sum = num1 + num2;
        return sum;
    }

    public static void main(String[] args){
        Tools tools = new Tools();
        tools.calcul("800+1000089", 100.0);
    }
}
