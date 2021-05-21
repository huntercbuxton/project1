package com.ex;

import com.ex.controller.Controller;
import com.ex.model.store.dao.DAO;
import com.ex.service.Service;
import io.javalin.Javalin;


public abstract class AbstractApp {

    public Service service;
    public DAO dao;
    public Controller controller;
    public Javalin javApp;
    public String dbname;

    public abstract void run();
    public abstract void willRun();
    public abstract void didRun();

    public AbstractApp(Service service, DAO dao, Controller controller, Javalin javApp) {
        this.service = service;
        this.dao = dao;
        this.controller = controller;
        this.javApp = javApp;
    }

    public AbstractApp() {}
}
