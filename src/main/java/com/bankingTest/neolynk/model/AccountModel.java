package com.bankingTest.neolynk.model;

import com.bankingTest.neolynk.enums.AccountTypeEnum;
import java.util.UUID;

/**
 * Created by zen on 16/03/18.
 */
public class AccountModel {

    private UUID idAccount;

    private String dateOfCreation;
    private Double balance;
    private AccountTypeEnum typeOfAccount;
    private UUID userId;

    /**
     * Constructor
     * @param dateOfCreation
     * @param balance
     * @param typeOfAccount
     */
    public AccountModel(String dateOfCreation, Double balance, AccountTypeEnum typeOfAccount) {
        this.idAccount = UUID.randomUUID();
        this.dateOfCreation = dateOfCreation;
        this.balance = balance;
        this.typeOfAccount = typeOfAccount;
    }

    //---- all getter and setter
    public UUID getIdAccount() {return idAccount;}
    public void setIdAccount(UUID idAccount) {this.idAccount = idAccount;}
    public String getDateOfCreation() {return dateOfCreation;}
    public void setDateOfCreation(String dateOfCreation) {this.dateOfCreation = dateOfCreation;}
    public Double getBalance() {return balance;}
    public void setBalance(Double balance) {this.balance = balance;}
    public AccountTypeEnum getTypeOfAccount() {return typeOfAccount;}
    public void setTypeOfAccount(AccountTypeEnum typeOfAccount) {this.typeOfAccount = typeOfAccount;}
    public UUID getUserId() {return userId;}
    public void setUserId(UUID userId) {this.userId = userId;}

    @Override
    public String toString() {
        return "AccountModel{" +
                "idAccount=" + idAccount +
                ", dateOfCreation='" + dateOfCreation + '\'' +
                ", balance=" + balance +
                ", typeOfAccount=" + typeOfAccount +
                ", userId=" + userId +
                '}';
    }
}
