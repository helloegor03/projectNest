package com.helloegor03.common.exceptions.auth;

public class UserIsAlreadyExistsException extends AuthException{
    public UserIsAlreadyExistsException(String message) {
        super(message);
    }
}
