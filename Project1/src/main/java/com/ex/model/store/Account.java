package com.ex.model.store;

import org.bson.types.ObjectId;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class Account {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Reimbursement reimbursement : reimbursements) {
            sb.append(reimbursement.toString());
        }

        String arrStr = sb.toString();
        return "Account{"
                + "firstName='" + firstName + '\''
                + " lastName='" + lastName + '\''
                + " id=" + id //+ '\''
                + " email='" + email + '\''
                + " password='" + password + '\''
                + " accountType='" + accountType + '\''
                + " userName='" + userName + '\''
                + " reimbursements=" + reimbursements.toString()
                + '}';
    }

    public String jsonStr() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    private ObjectId id;
    private String email;
    private String password;
    private String accountType;
    private String firstName;
    private String lastName;
    private String userName;
    private List<Reimbursement> reimbursements;

    public List<Reimbursement> getReimbursements() { return reimbursements; }
    public void setReimbursements(List<Reimbursement> reimbursements) { this.reimbursements = reimbursements; }

    public ObjectId getId() { return id; }
    public void setId(ObjectId id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }


    public Account(String email, String password, String accountType, String firstName, String lastName, String userName, List<Reimbursement> reimbursements) {
        this.email = email;
        this.password = password;
        this.accountType = accountType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.reimbursements = reimbursements;
    }

    public Account() {
        this.email = "email";
        this.password = "password";
        this.accountType = "accountType";
        this.firstName = "firstName";
        this.lastName = "lastName";
        this.userName = "userName";
        this.reimbursements = new ArrayList<>();
    }

}
