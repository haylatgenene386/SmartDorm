
package com.smartdorm.model;

public abstract class Person {

    // Encapsulated attributes
    private String id;
    private String fullName;
    private String email;
    private String phoneNumber;

    // Constructor
    public Person(String id, String fullName,
                  String email, String phoneNumber) {

        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Setters
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Abstract method
    public abstract void displayInformation();
}
