package com.ex.model.store.dao;

import com.ex.model.store.Account;
import com.ex.model.store.Reimbursement;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class MongoReimbursementDAO  extends  MongoAccountDAO {



    public List<Reimbursement> fetchReimbursements() {
        List<Reimbursement> dataList = new ArrayList<>();
        FindIterable<Account> fi = collection.find();
        MongoCursor<Account> cursor = fi.iterator();
        while (cursor.hasNext()) {
            Account nextAccount = cursor.next();
            dataList.addAll(nextAccount.getReimbursements());
        }
        return dataList;
    }

    public List<Reimbursement> fetchReimbursements(ObjectId id) {
        List<Reimbursement> list = new ArrayList<>();
        return  collection.find(new Document("_id", id)).first().getReimbursements();
    }


//    public void insertOne() {
//        collection.insertOne(new Reimbursement());
//    }


    public MongoReimbursementDAO(String collectionName, String host, String port, String dbname) {
        super(collectionName, host, port, dbname);
    }

}
