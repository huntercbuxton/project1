package com.ex;

import com.ex.model.store.dao.DAO;
import io.javalin.Javalin;

import com.ex.service.*;
import com.ex.controller.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.javalin.apibuilder.ApiBuilder.*;

public class App extends  AbstractApp {

    public static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(App.class.getName());
    public static final Logger rootLogger = LogManager.getRootLogger();

    /**
     * to be performed before running the app
     */
    @Override
    public void willRun() {
        rootLogger.info("app willRum() called");
//        BasicConfigurator.configure();
        registerJavalinURIs();
        mvcSetup();
    }

    /**
     * runs the app
     */
    @Override
    public void run() {
        javApp.start(7777);
    }

    /**
     * to be performed after running the app
     */
    @Override
    public void didRun() { }

    /* helper methods */

    /**
     * register the paths used by API endpoints
     */
    protected void registerJavalinURIs() {

        javApp.routes(() -> {
//            before(Controller.ensureLogin);
            get("/", ctx -> {
                ctx.render("this is a test");
            });
            post("/login", ctx -> controller.loginPost(ctx));
            get("/dashboard", ctx -> controller.dashboardGet(ctx));
//            get("/logout", ctx -> controller.logoutGet(ctx));
            get("/tools", ctx -> controller.toolsGet(ctx));
            get("/account", ctx -> controller.accountPost(ctx));
//            get("/requests", ctx -> controller.requestsGet(ctx));
//            post("/logout", ctx -> controller.logoutPost(ctx));
            get("/login/?type=employee", ctx -> {
                ctx.render("/loginpage.html" );
            });
            get("/login/?type=manager", ctx -> {
                ctx.render("/loginpage.html");
            });
        });
    }

    /**
     * set up the objects used to execute the program
     */
    protected void mvcSetup() {
        dao = new DAO(dbname);
//        dao.test();
//        dao.testQuery();
        dao.addMockData();
//        Account toRegister = new Account("redbaron@camelfucker.com", "80085", "employee", "proletariat", "martyr", "shoresy", ( new ArrayList<Reimbursement>() ));
//        Account acct = dao.addAccount(toRegister);
//        System.out.println("returned from addAccount : " + acct);
        service = new Service(dao);
        controller = new Controller(service);

    }

    /* constructors */

    public App(Javalin javApp, String dbname) {
        this.javApp = javApp;
        this.dbname = dbname;
    }

    public App() {

        super();
        dbname = "Project1DB";
        javApp = Javalin.create(config -> {
            config.addStaticFiles("/public");
        });
    }

}



//        javApp.get("/", ctx -> { ctx.re»nder("/public/guesthome.html"); });
//
//        javApp.get("/login", ctx -> { ctx.render("/public/login.html"); });
//
//        javApp.get("/logout", ctx -> { controller.logoutRequest(ctx); });
//
//        javApp.before("/secure", Controller.ensureLogin);
//
//        javApp.get("/secure", ctx -> { ctx.render("/public/managerhome.html"); });
//        javApp.post("/secure", ctx -> { ctx.render("/public/managerhome.html"); });

//        javApp.before("/secure/*", Controller.ensureLogin);

//        javApp.post("/secure/manager", ctx -> {
//            controller.managerLoginRequest(ctx);
//        });
//
//        javApp.get("/secure/main", ctx -> {
//            ctx.render("<h1>this is the manager home page</h1>");
//        });
//
//        javApp.get("/secure/manager", ctx -> {
//             ctx.render("/public/home/managerhome.html");
//        });
//
//        javApp.post("/endsession", ctx -> {
//            controller.logoutRequest(ctx);
//        });