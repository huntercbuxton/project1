package com.ex.controller;

import com.ex.model.client.GenericClientResponse;
import com.ex.model.client.LoginRequest;
import com.ex.model.client.LoginResponse;
import com.ex.model.store.Account;
import com.ex.model.store.Reimbursement;
import com.ex.model.store.dao.MongoReimbursementDAO;
import com.ex.service.*;
import io.javalin.http.Context;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;


public class Controller {

    protected Service service;
    protected HashMap<String, String> activeTokens = new HashMap<>();

//    public static Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
//    public static Logger javalinLogger = Logger.getLogger("io.javalin.Javalin");
    public static final Logger logger = LogManager.getLogger(Controller.class.getName());
    public static final Logger rootLogger = LogManager.getRootLogger();

    /**
     * endpoint for user login attempts
     * @param ctx the javalin context
     */
    public void loginPost(Context ctx) {

        LoginRequest request = new LoginRequest(ctx.formParam("email"), ctx.formParam("password"), ctx.formParam("accountType"));
        LoginResponse response = service.validLoginInfo(request);
        logger.info("endpoint loginPost recieved a request for login with data email = " + ctx.formParam("email") + " password = " + ctx.formParam("password")  + "  accountTYpe = " + ctx.formParam("accountType") +  " the service return the following Respinse: " + response);
        String accountId = response.getResponse();
//        AuthToken newToken = new AuthToken(accountId);
        ctx.sessionAttribute("currentUser", accountId);
        ctx.sessionAttribute("accountType", ctx.formParam("accountType"));
        rootLogger.info("before returning, the value of the response body is " + response);
        ctx.header("Access-Control-Allow-Origin","*");
        ctx.json(response);
    }

    /**
     * endpoint for user dashboard info
     * @param ctx the javalin context
     */
    public void dashboardGet(Context ctx) throws JSONException {
        rootLogger.info("user" + ctx.queryParam("fromUser") + " requested account info for " + ctx.queryParam("fromUser"));
        String idStr = ctx.queryParam("fromUser");
        ObjectId id = new ObjectId(idStr);
        Account data = service.getDao().getAccountDAO().fetchByID(id);
        ctx.header("Access-Control-Allow-Origin","*");
        ctx.json(data);
    }

    /**
     * endpoint for user logout
     * @param ctx the javalin context
     */
    public void logoutGet(Context ctx) {
        ctx.sessionAttribute("currentUser", null);
        ctx.render("/public/logout.html");
    }

    /**
     * endpoint for user account updates
     * @param ctx the javalin context
     */
    public void accountPost(Context ctx) {

        Account account = new Account();
        account.setEmail(ctx.formParam("email"));
        account.setPassword(ctx.formParam("password"));
        account.setFirstName(ctx.formParam("firstName"));
        account.setLastName(ctx.formParam("lastName"));
        account.setId(new ObjectId(Objects.requireNonNull(ctx.formParam("id"))));
        GenericClientResponse response = service.getDao().getAccountDAO().update(account);
        ctx.json(response);
    }

    /**
     * endpoint for user reimbursments requests
     * @param ctx the javalin context
     */
    public void toolsGet(Context ctx) {
        List<Reimbursement> data = service.getDao().getReimbursmentDAO().fetchReimbursements(new ObjectId(Objects.requireNonNull(ctx.formParam("id"))));
        ctx.header("Access-Control-Allow-Origin","*");

        List<JSONObject> requests = new ArrayList<>();
        for (Reimbursement item: data) {
            JSONObject doc = new JSONObject();
            try {
                doc.put("amount", item.getAmount());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            requests.add(doc);
        }

        ctx.json(requests);
    }

//     public void requestsGet(Context ctx) {
//        MongoReimbursementDAO reimbursementDAO = service.getDao().getReimbursmentDAO();
//        String id = ctx.queryParam("accountid");
//
//    }

//    public void registerNewEmployee(Context ctx) {
//        Account toRegister = new Account("urmoma@rubberisglue.com", "80085", "employee", "proletariat", "martyr", "shoresy", ( new ArrayList<Reimbursement>() ));
//        Account acct =service.registerNewEmployee(toRegister);
//        System.out.println("returned from registerNewEmployee service : " + acct);
//    }

    public Controller() {
        this.service = new Service();
    }

    public Controller(Service service) {
        this.service = service;
    }

}
