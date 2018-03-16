package com.bankingTest.neolynk.model;

import com.bankingTest.neolynk.core.Core;

import java.util.UUID;

/**
 * Created by zen on 16/03/18.
 */
public class UserModel  {

    private UUID idUser;

    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String email;
    private Integer age;


    /**
     *  Constructor
     * @param firstName
     * @param lastName
     * @param address
     * @param phoneNumber
     * @param email
     * @param age
     */
    public UserModel(String firstName, String lastName, String address, String phoneNumber, String email, Integer age) {
        this.idUser = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.age = age;
    }



    //---- all getter and setter
    public UUID getIdUser() {return idUser;}
    public void setIdUser(UUID idUser) {this.idUser = idUser;}
    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName; }
    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}
    public String getPhoneNumber() {return phoneNumber;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public Integer getAge() {return age;}
    public void setAge(Integer age) {this.age = age;}

    @Override
    public String toString() {
        return "UserModel{" +
                "idUser=" + idUser +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }

}
