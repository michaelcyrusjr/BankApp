package com.michaelcyrusjr.bankapp.model;

/**
 * @author Michael Cyrus Jr
 **/
public class Customer {
    private int customerId;
    private String firstName;
    private String lastName;
    private String fullName;
    private String emailAddress;
    private String password;

    // Constructors
    public Customer (String firstName, String lastName, String emailAddress, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
    }
    public Customer (int customerId, String firstName, String lastName, String emailAddress, String password) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    //Getters
    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public String getfullName () {
        return this.fullName;
    }
    public String emailAddress() {
        return this.emailAddress;
    }
    public String getPassword() {
        return this.password;
    }

    // Setters
    public void setCustomerId (int customerId) {
        this.customerId = customerId;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setFullName() {
        this.fullName = (firstName + " " + lastName);
    }
    public void setEmailAddress() {
        this.emailAddress = emailAddress;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}

