package org.example.tests.models;

public class UserFactory {

    public static User standardUser() {
        return new User("standard_user", "secret_sauce");
    }

    public static User lockedUser() {
        return new User("locked_out_user", "secret_sauce");
    }

    public static User errorUser() {
        return new User("error_user", "secret_sauce");
    }

    public static User standardUserWithWrongPass() {
        return new User("standard_user", "wrong_pass");
    }

    public static User lockedUserWithWrongPass() {
        return new User("locked_out_user", "secretsauce");
    }

    public static User errorUserWithWrongPass() {
        return new User("error_user", "secret_sauc");
    }
}