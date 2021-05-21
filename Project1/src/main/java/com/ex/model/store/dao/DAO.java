package com.ex.model.store.dao;

import com.ex.model.store.Account;
import com.ex.model.store.Reimbursement;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.ReturnDocument;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.ConnectionString;
import static com.mongodb.client.model.Filters.eq;
import static java.util.Collections.singletonList;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class DAO  {

    /**
     * to populate the db for test purposes
     */
    public void addMockData() {

        MongoAccountDAO accountDao = getAccountDAO();

        List<Account> toLoad = new ArrayList<>();
        toLoad.add(new Account("bigfoot@cryptids.net","22222","employee","big","foot","kwest1", singletonList(new Reimbursement(99,"complete"))));
        toLoad.add(new Account("mothman@cryptids.net","22222","employee","moth","man","kwest2", singletonList(new Reimbursement(69,"rejected"))));
        toLoad.add(new Account("jerseydevil@cryptids.net","22222","employee","jersey","devil","kwest3", singletonList(new Reimbursement(3888,"pending]"))));
        toLoad.add(new Account("chupacabra@cryptids.net","22222","employee","chupacabra","mononym","kwest4", singletonList(new Reimbursement(12345,"complete"))));
        toLoad.add(new Account("ye@cryptids.net","22222","employee","kanye","west","kwest", singletonList(new Reimbursement(2,"complete"))));

        for (Account data : toLoad) {
            accountDao.insert(data);
            System.out.println(data);
        }

        Reimbursement newReimbursement = new Reimbursement(11111, "complete");
        Account account = accountDao.collection.find(new BasicDBObject()).first();
        Account updatedAccount = accountDao.addReimbursement(account, newReimbursement);
        System.out.println(updatedAccount);

        List<Reimbursement> reimbursementsToAdd = new ArrayList<>();
        reimbursementsToAdd.add(new Reimbursement(35, "pending"));
        reimbursementsToAdd.add(new Reimbursement(444, "rejected"));
        reimbursementsToAdd.add(new Reimbursement(3542, "complete"));
        reimbursementsToAdd.add(new Reimbursement(666, "pending"));
        reimbursementsToAdd.add(new Reimbursement(2, "complete"));

        Account accountData  = new Account("meninblack@cryptids.net","22222&","manager","agent","k","iliketurtles", singletonList(new Reimbursement(2,"complete")));
        Account anotherDocData = accountDao.insert(accountData);

        Account anotherUpdatedAccount = accountDao.addReimbursementList(anotherDocData, reimbursementsToAdd);

        System.out.println(anotherUpdatedAccount);

        MongoReimbursementDAO reimbursementDAO = getReimbursmentDAO();
        List<Reimbursement> reimbursements = reimbursementDAO.fetchReimbursements();
        System.out.println("ALL REIMBURSEMENTS : " + reimbursements);

        reimbursements.removeIf(x -> (x.getStatus().equals("complete")));
        System.out.println("NO COMPLETE REIMBURSEMENTS : " + reimbursements);
    }

    protected String dbname;

    public Account searchByLogin(String email, String password, String accountType) {
        return getAccountDAO().queryAuthInfo(email, password, accountType);
    }

    public Account addAccount(Account account) {
        MongoAccountDAO accountDAO = new MongoAccountDAO("accounts","localhost","27017","project1");
        return accountDAO.insert(account);
    }

    public MongoAccountDAO getAccountDAO() {
        return new MongoAccountDAO("accounts","localhost","27017","project1");
    }

    public MongoReimbursementDAO getReimbursmentDAO() {
        return new MongoReimbursementDAO("accounts", "localhost", "27017","project1");
    }

//    public void addReimbursementRequest(ObjectId obj, Reimbursement reimbursement) { }
//
//    public void testQuery() {
//        ConnectionString connectionString = new ConnectionString("/project1");
//        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
//        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
//        MongoClientSettings clientSettings = MongoClientSettings.builder()
//                .applyConnectionString(connectionString)
//                .codecRegistry(codecRegistry)
//                .build();
//        try (MongoClient mongoClient = MongoClients.create(clientSettings)) {
//            MongoDatabase db = mongoClient.getDatabase("project1");
//            MongoCollection<Account> accounts = db.getCollection("accounts", Account.class);
//            Account newAccount = new Account("cloudy@skinnyfat.net","pissword","employee","jimmy","buttface","jbutt", singletonList(new Reimbursement(12345,"complete")));
//            accounts.insertOne(newAccount);
//
//            Account account = accounts.find(eq("email", "sunny@chubby.net")).first();
//            System.out.println("Account Found: " + account);
//
//            List<Reimbursement> newReimbursements = new ArrayList<>(account.getReimbursements());
//            newReimbursements.add(new Reimbursement(666, "pending"));
//            account.setReimbursements(newReimbursements);
//            Document filterById = new Document("_id", account.getId());
//            FindOneAndReplaceOptions returnDocAfterReplace = new FindOneAndReplaceOptions();
//            returnDocAfterReplace.returnDocument(ReturnDocument.AFTER);
//            Account updatedAccount = accounts.findOneAndReplace(filterById, account, returnDocAfterReplace);
//            System.out.println("UPDATED ACCOUNT: " + updatedAccount);
//        }
////      .filter(eq("_id", new  ObjectId("609c2a4eb433fd5199ff026a"))).first();
//
//    }

//
//    public void testAccountDAO() {
//        MongoAccountDAO accountDAO = new MongoAccountDAO("accounts","localhost","27017","project1");
//        System.out.println("before: " + accountDAO.count() );
//        accountDAO.insertOne();
//        System.out.println("after: " + accountDAO.count() );
//    }

//    public static Mong setupMongoDAO()

    public DAO(String dbname) {
        this.dbname = dbname;
    }
}


//    public void test() {
//      MongoReimbursementDAO reimbursementDAO = new MongoReimbursementDAO("reimbursements","localhost","27017","project1");
////      reimbursementDAO.insertOne();
//        MongoAccountDAO accountDAO = new MongoAccountDAO("accounts","localhost","27017","project1");
//        Account account = accountDAO.collection.find(new BasicDBObject()).first();
//        Reimbursement innerData = new Reimbursement();
////        reimbursementDAO.collection.insertOne(innerData);
//        FindIterable<Reimbursement> cursor = reimbursementDAO.collection.find().sort(new Document("_id", -1)).limit(1);
//        System.out.println(cursor);
//
//        BasicDBObject query = new BasicDBObject();
//        query.put("_id", new  ObjectId("609c2a4eb433fd5199ff026a")); // (1)
//
//        BasicDBObject newDocument = new BasicDBObject();
//        newDocument.put("reimbursements", cursor); // (2)
//
//        BasicDBObject updateObject = new BasicDBObject();
//        updateObject.put("$push", newDocument); // (3)
//        accountDAO.collection.updateOne(query, updateObject);
//
//    }