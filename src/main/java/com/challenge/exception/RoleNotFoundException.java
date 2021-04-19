package com.challenge.exception;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(String role) {
        super("Role: " + role + " doesn't exist");
    }
}
