package com.michaelcyrus.bankapp.entity;
import jakarta.persistence.*;

/**
 * @author Michael Cyrus Jr
 **/
@Entity
@Table(name="customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 55)
    private String firstName;

    @Column(nullable = false, length = 55)
    private String lastName;

    @Column(nullable = false, unique = true, length = 55)
    private String email;

    @Column(nullable = false, length = 255)
    private String passwordHash;


    public Customer() {
    }

    public Customer (String firstName, String lastName, String email, String passwordHash) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passwordHash = passwordHash;
    }


    public Long getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return this.passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }



}
