package com.michaelcyrusjr.bankapp.util;

/**
 * @author Michael Cyrus Jr
 **/
public class InputValidator {
    public static boolean isValidName(String name) {
        return name.matches("[A-Za-z' -]+");
    }

}
