package com.bankingTest.neolynk.core;

/**
 * Created by zen on 18/03/18.
 */

// Classe permettant de fabriquer une unité.
public class SimpleFabrique
{
    // La création d'une unité en fonction de son type est encapsulée dans la fabrique.
    public UserCore createUser(UserCore createUser){
        UserCore userCore= new UserCore();
        return userCore;
    }

}