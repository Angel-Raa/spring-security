package com.caja.ideal.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class UserNotFoundException extends DataIntegrityViolationException {

    public UserNotFoundException(String msg) {
        super(msg);
    }
}
