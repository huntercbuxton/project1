package com.ex.model.store.dao;

abstract class iDbh<T> {

    protected String hostname = "localhost";
    protected String port = "27017";
    protected String dbname;

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

//    abstract void createConnection() throws RuntimeException;
//    abstract void testConnection() throws Exception;

    abstract T connect();

    public iDbh(String hostname, String port, String dbname) {
        this.hostname = hostname;
        this.port = port;
        this.dbname = dbname;
    }

//    public iDbh() {}
}
