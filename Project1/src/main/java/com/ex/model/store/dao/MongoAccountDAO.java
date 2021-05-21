package com.ex.model.store.dao;

import com.ex.model.client.GenericClientResponse;
import com.ex.model.store.Account;
import com.ex.model.store.Reimbursement;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.ReturnDocument;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.*;
import static com.mongodb.client.model.Filters.eq;

public class MongoAccountDAO extends MongoDbh<Account> {

    protected String collectionName;

    public MongoCollection<Account> collection;

    public Account insert(Account account) {
        collection.insertOne(account);
        return account;
    }

    public Account addReimbursement(Account account, Reimbursement reimbursement) {
       return addReimbursementList(account, new ArrayList<>(Arrays.asList(reimbursement )));
    }

    public Account addReimbursementList(Account account, List<Reimbursement> newData) {
        List<Reimbursement> savedRequests = new ArrayList<>(account.getReimbursements());
        savedRequests.addAll(newData);
        account.setReimbursements(savedRequests);
        Document filterById = new Document("_id", account.getId());
        FindOneAndReplaceOptions returnDocAfterReplace = new FindOneAndReplaceOptions();
        returnDocAfterReplace.returnDocument(ReturnDocument.AFTER);
        Account updatedAccount = collection.findOneAndReplace(filterById, account, returnDocAfterReplace);
        return updatedAccount;
    }

    protected void setCollection() {
        collection = db.getCollection(collectionName, Account.class);
    }

    public void insertOne() {
        collection.insertOne(new Account());
    }

    public Account queryAuthInfo(String email, String password, String accountType) {
        Document filterByLogin = new Document("email", email);
        filterByLogin.put("password", password);
        filterByLogin.put("accountType", accountType);
        return collection.find(filterByLogin).first();
    }

    public Account fetchByID(ObjectId id)  {
        Document filterByID = new Document("_id", id);
        return collection.find(filterByID).first();
    }

    public GenericClientResponse update(Account account) {
        Document filterByID = new Document("_id", account.getId());
        Account oldData = collection.find(filterByID).first();
        if (oldData == null) {
            return new GenericClientResponse(1, "account does not exist");
        }

        account.setAccountType(oldData.getAccountType());

        Document filterById = new Document("_id", account.getId());
        FindOneAndReplaceOptions returnDocAfterReplace = new FindOneAndReplaceOptions();
        returnDocAfterReplace.returnDocument(ReturnDocument.AFTER);
        collection.findOneAndReplace(filterById, account, returnDocAfterReplace);

        return new GenericClientResponse(0, "");
    }

    public MongoAccountDAO(String collectionName, String host, String port, String dbname) {
        super(host, port, dbname);
        this.collectionName = collectionName;
        setCollection();
    }

}


//
//public class DocumentCodecProvider implements CodecProvider {
//    private final BsonTypeClassMap bsonTypeClassMap;
//
//    public DocumentCodecProvider(final BsonTypeClassMap bsonTypeClassMap) {
//        this.bsonTypeClassMap = bsonTypeClassMap;
//    }
//
//    @Override
//    public <T> Codec<T> get(final Class<T> clazz, final CodecRegistry registry) {
//        if (clazz == Document.class) {
//            // construct DocumentCodec with a CodecRegistry and a BsonTypeClassMap
//            return (Codec<T>) new DocumentCodec(registry, bsonTypeClassMap);
//        }
//
//        return null;
//    }
//}