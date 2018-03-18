package com.bankingTest.neolynk.utils;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zen on 18/03/18.
 */
public class ToolTest {

    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(ToolTest.class);
    public Tools tools = new Tools();

    @Test
    public void checkIfStringInteger() throws Exception {
        Assert.assertTrue(tools.checkIfInteger("42"));
        Assert.assertFalse(tools.checkIfInteger(")àçéi&àéke&j&joi&péoj&o"));
    }

    @Test
    public void checkIfStringUUID() throws Exception {
        Assert.assertFalse(tools.checkIfUUID("42"));
        Assert.assertFalse(tools.checkIfUUID(")àçéi&àéke&j&joi&péoj&o"));
        Assert.assertTrue(tools.checkIfUUID("123e4567-e89b-12d3-a456-556642440000"));
    }

    @Test
    public void checkIfDouble() throws Exception {
        Assert.assertTrue(tools.checkIfDouble("42"));
        Assert.assertFalse(tools.checkIfDouble(")àçéi&àéke&j&joi&péoj&o"));
        Assert.assertFalse(tools.checkIfDouble("123e4567-e89b-12d3-a456-556642440000"));
        Assert.assertTrue(tools.checkIfDouble("42.987543"));
    }

    @Test
    public void checkIfAccountTypeExist() throws Exception {
        Assert.assertFalse(tools.checkIfAccountTypeExist("42"));
        Assert.assertFalse(tools.checkIfAccountTypeExist(")àçéi&àéke&j&joi&péoj&o"));
        Assert.assertFalse(tools.checkIfAccountTypeExist("123e4567-e89b-12d3-a456-556642440000"));
        Assert.assertFalse(tools.checkIfAccountTypeExist("42.987543"));
        Assert.assertTrue(tools.checkIfAccountTypeExist("LIVRET_A"));
    }

    @Test
    public void descriptionOfAccountType() throws Exception {
        Assert.assertEquals(tools.descriptionOfAccountType("LIVRET_A"), "LIVRET_A: small client");
        Assert.assertEquals(tools.descriptionOfAccountType("AAAAAAA"), null);
    }

    @Test
    public void regexFoundString() throws Exception {
        final String regex = "(?<=idAccount=)(.*)(?=, dateOfCreation)";
        final String string = "AccountModel{idAccount=2e19796f-31c3-4bbd-88ad-2a3ac4bc6cc7, dateOfCreation='2018-03-18', balance=0.14948617208157355, typeOfAccount=LIVRET_A, userId=3cc54563-6788-4af9-ad7e-fbe24d99ead9}";
        Assert.assertTrue(tools.regexFoundString(regex, string));
    }

    @Test
    public void regexFoundAndExtractString() throws Exception {
        final String regex = "(?<=idAccount=)(.*)(?=, dateOfCreation)";
        final String string = "AccountModel{idAccount=2e19796f-31c3-4bbd-88ad-2a3ac4bc6cc7, dateOfCreation='2018-03-18', balance=0.14948617208157355, typeOfAccount=LIVRET_A, userId=3cc54563-6788-4af9-ad7e-fbe24d99ead9}";
        Assert.assertEquals(tools.regexFoundAndExtractString(regex, string), "2e19796f-31c3-4bbd-88ad-2a3ac4bc6cc7");
    }
    @Test
    public void regexFoundAndExtractString2() throws Exception {
        final String regex = "(?<=userId=)(.*)(?=\\})";
        final String string = "\"e19056a6-3969-4225-8bbf-66dc50ee0184\" -> \"AccountModel{idAccount=e19056a6-3969-4225-8bbf-66dc50ee0184, dateOfCreation='2018-03-18', balance=0.8979192077464005, typeOfAccount=LIVRET_A, userId=2f7cece3-a3b1-48a9-b840-bde5213728d3}\"";
        Assert.assertEquals(tools.regexFoundAndExtractString(regex, string), "2f7cece3-a3b1-48a9-b840-bde5213728d3");
    }

}
