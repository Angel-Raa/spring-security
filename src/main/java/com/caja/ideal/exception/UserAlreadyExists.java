package com.caja.ideal.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class UserAlreadyExists extends DataIntegrityViolationException {
    public UserAlreadyExists(String msg) {
        super(msg);
    }
}
