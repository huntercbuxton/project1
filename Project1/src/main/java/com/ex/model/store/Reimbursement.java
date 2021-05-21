package com.ex.model.store;

import org.bson.types.ObjectId;

public class Reimbursement {

    protected ObjectId id;
    protected int amount; // in pennies
    protected String status; // pending, complete, rejected

    public ObjectId getId() { return id; }
    public void setId(ObjectId id) { this.id = id; }

    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }


    public Reimbursement(int amount, String status) {
        this.amount = amount;
        this.status = status;
    }
    public Reimbursement() {
        this.amount = 1000;
        this.status = "pending";
    }


    @Override
    public String toString() {
        return "Reimbursement{"
                + "id=" + id // + '\''
                + " amount=" + amount //+ '\''
                + " status='" + status
                + "'}";
    }
}
