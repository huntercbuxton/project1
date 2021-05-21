package com.ex.model.client;

import com.google.gson.*;

public class GenericClientResponse {

    protected int errCode = 0;
    protected String response;

    public int getErrCode() {
        return errCode;
    }
    public String getResponse() {
        return response;
    }

    @Override
    public String toString() {  return (new Gson()).toJson(this); }

    protected void defaultResponse() {
        this.errCode = 0;
        this.response = "";
    }

    public GenericClientResponse(int errCode, String response) {
        this.errCode = errCode;
        this.response = response;
    }

    public GenericClientResponse() {
        defaultResponse();
    }
}
