package com.caja.ideal.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class ResourceNotFound extends DataIntegrityViolationException {
    public ResourceNotFound(String msg) {
        super(msg);
    }


}
