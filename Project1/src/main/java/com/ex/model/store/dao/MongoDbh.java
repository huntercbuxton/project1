package com.ex.model.store.dao;

import com.ex.model.store.Account;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BasicBSONObject;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import  java.lang.RuntimeException;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


public abstract class MongoDbh<M> extends iDbh<MongoDatabase> {

    /* iDbh */

    @Override
    public MongoDatabase connect() { return  this.db; }

    protected MongoDatabase db;

    public MongoCollection<M> collection;

    public int count() {
        return ((int)collection.countDocuments());
    }


    protected ConnectionString createConnectionString() {
        return new ConnectionString("mongodb://" + hostname + ":" + port + "/" + dbname);
    }

    public MongoDatabase updateConn() {
        try {  db = createConnection(createConnectionString(), dbname); }
        catch (Exception e) {  System.out.println(e.getMessage()); }
        return db;
    }

//    public abstract MongoCollection<K> getCollection(String name);

    public static MongoDatabase createConnection(ConnectionString connectionString, String dbName) throws RuntimeException {
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(codecRegistry)
                .build();
        MongoClient mongoClient = MongoClients.create(clientSettings);
        return mongoClient.getDatabase(dbName);
    }

    public MongoDbh(String host, String port, String dbname) {
        super(host, port, dbname);
        this.db = updateConn();
    }
}
