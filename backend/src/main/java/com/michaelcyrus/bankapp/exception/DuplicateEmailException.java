package com.michaelcyrus.bankapp.exception;

/**
 * @author Michael Cyrus Jr
 **/
public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException(String message) {
        super(message);
    }
}
