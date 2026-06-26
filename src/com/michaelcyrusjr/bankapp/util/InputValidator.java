package com.michaelcyrusjr.bankapp.util;

/**
 * @author Michael Cyrus Jr
 **/
public class InputValidator {
    public static boolean isValidName(String name) {
        if (name == null) {
            return false;
        }

        return name.matches("[A-Za-z' -]+");
    }

    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }

        return email.matches("^[A-Za-z0-9'+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    public static boolean isValidPassword(String password) {
        if (password == null) {
            return false;
        }
        if (password.length() < 12 || password.length() > 64) {
            return false;
        }
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }
        if (!password.matches(".*[a-z].*")) {
            return false;
        }
        if (!password.matches(".*[0-9].*")) {
            return false;
        }
        if (!password.matches(".*[^A-Za-z0-9].*")) {
            return false;
        }
        if (password.matches(".*\\s.*")) {
            return false;
        }
        return true;
    }
}
