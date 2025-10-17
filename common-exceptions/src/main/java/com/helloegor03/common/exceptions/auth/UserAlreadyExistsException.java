package com.helloegor03.common.exceptions.auth;

public class UserAlreadyExistsException extends AuthException{
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
