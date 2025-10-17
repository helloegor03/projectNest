package com.helloegor03.common.exceptions.auth;

public class UserNotFoundException extends AuthException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
