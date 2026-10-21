package org.example.models;

public class CreateUserRequest {
    private String firstName;
    private String lastName;

    public CreateUserRequest(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}