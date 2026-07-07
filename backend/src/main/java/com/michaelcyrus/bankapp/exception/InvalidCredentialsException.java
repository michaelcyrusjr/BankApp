package com.michaelcyrus.bankapp.exception;

/**
 * @author Michael Cyrus Jr
 **/
public class InvalidCredentialsException extends RuntimeException {

    public InvalidCredentialsException(String message) {
        super(message);
    }
}
