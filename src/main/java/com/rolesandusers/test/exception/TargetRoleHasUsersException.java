package com.rolesandusers.test.exception;

public class TargetRoleHasUsersException extends RuntimeException {
    public TargetRoleHasUsersException(String message) {
        super(message);
    }
}
