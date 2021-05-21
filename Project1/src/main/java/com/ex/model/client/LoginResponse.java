package com.ex.model.client;

import com.google.gson.*;

public class LoginResponse extends GenericClientResponse {

    protected String token;

    public void success(String token) {
        errCode = 0;
        response = token;
    }

    public void failure() {
        errCode = 1;
        response = "incorrect login info";
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return (new Gson()).toJson(this);
    }

//    public LoginResponse(boolean didSucceed) {
//        this.errCode = didSucceed ? 0 : 1;
//    }

    public LoginResponse() {
        success("success");
    }

}
