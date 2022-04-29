package com.ing.test.exception;

public class EntityNotFoundException extends HttpException {

    public EntityNotFoundException(String message) {
        super("NOT_FOUND", message);
    }
}
