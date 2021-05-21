package com.ex.model.client;

public class LoginRequest {
    protected String email;
    protected String password;
    protected String accountType;

    public String getPassword() {
        return password;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getEmail() {
        return email;
    }

    public LoginRequest(String email, String password, String accountType) {
        this.email = email;
        this.password = password;
        this.accountType = accountType;
    }

    public LoginRequest() { }
}
