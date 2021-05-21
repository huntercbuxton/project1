package com.ex.service;

import com.ex.model.client.LoginRequest;
import com.ex.model.client.LoginResponse;
import com.ex.model.store.dao.DAO;
import com.ex.model.store.Account;


public class Service {

    protected DAO dao;

    public void setDao(DAO dao) {
        this.dao = dao;
    }

    /**
     * performs the logic for logging in a user
     * @param request a request object holding the username/email and password
     * @return the user's UID, or the reson for the request being denied
     */
    public LoginResponse validLoginInfo(LoginRequest request) {
        Account account = dao.searchByLogin(request.getEmail(), request.getPassword(), request.getAccountType());
        LoginResponse response = new LoginResponse();
        if (account == null) {
            response.failure();
        } else {
            response.setResponse(account.getId().toString());
        }
        return  response;
    }

//    public boolean verifyManagerID(ObjectId id) {
//         Account account = dao.getAccountDAO().fetchByID(id).getId()
//         if (account?.eccountType().equals('manager')) {
//            List<Reimbursement> rList = dao.getReimbursmentDAO().fetchReimbursements();
//            ctx.json(rList);
//         }
//    }

    public DAO getDao() {
        return dao;
    }

    public Service(DAO dao) {
        this.dao = dao;
    }

    public Service() {}


    /*  not APU */



    public Account registerNewEmployee(Account account) {
        return dao.addAccount(account);
    }

}
