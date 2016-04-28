package com.tecnoinf.proy.dataAccess;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by JuanPablo on 4/27/2016.
 */
public class MongoDB {

    public static final String DB_HOST = "localhost";
    public static final int DB_PORT = 27017;
    public static final String DB_NAME = "usbus";
    private static final MongoDB INSTANCE = new MongoDB();
    private final Datastore datastore;

    private MongoDB() {

        Morphia morphia = new Morphia();
        ServerAddress addr = new ServerAddress(DB_HOST, DB_PORT);
        List<MongoCredential> credentialsList = new ArrayList<MongoCredential>();
        MongoCredential credential = MongoCredential.createCredential("admin",
                DB_NAME, "admin".toCharArray());
        credentialsList.add(credential);
        MongoClient client = new MongoClient(addr);

        datastore = morphia.createDatastore(client, "usbus");
        datastore.ensureIndexes();
    }

    public static MongoDB instance() {
        return INSTANCE;
    }

    public Datastore getDatabase() {
        return datastore;
    }
}