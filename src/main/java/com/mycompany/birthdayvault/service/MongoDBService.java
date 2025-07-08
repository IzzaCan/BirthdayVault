package com.mycompany.birthdayvault.service;

import com.mongodb.client.*;
import com.mycompany.birthdayvault.model.BirthdayEntry;
import org.bson.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MongoDBService {

    private final MongoClient client;
    private final MongoDatabase database;
    private final MongoCollection<Document> collection;

    public MongoDBService() {
        client = MongoClients.create("mongodb://localhost:27017");
        database = client.getDatabase("birthdayvault_db");
        collection = database.getCollection("birthdays");
    }

    public void addBirthday(BirthdayEntry entry) {
        Document doc = new Document("user", entry.getUser())
                .append("name", entry.getName())
                .append("birthDate", entry.getBirthDate().toString())
                .append("message", entry.getMessage());
        collection.insertOne(doc);
    }

    public List<BirthdayEntry> getAllBirthdaysByUser(String username) {
        List<BirthdayEntry> list = new ArrayList<>();
        Document query = new Document("user", username);
        for (Document doc : collection.find(query)) {
            String name = doc.getString("name");
            LocalDate date = LocalDate.parse(doc.getString("birthDate"));
            String message = doc.getString("message");
            list.add(new BirthdayEntry(username, name, date, message));
        }
        return list;
    }

    public void deleteBirthday(String username, String name) {
        Document query = new Document("user", username).append("name", name);
        collection.deleteOne(query);
    }

    public void updateBirthday(String username, String originalName, BirthdayEntry updatedEntry) {
        Document query = new Document("user", username).append("name", originalName);
        Document update = new Document("$set", new Document()
                .append("name", updatedEntry.getName())
                .append("birthDate", updatedEntry.getBirthDate().toString())
                .append("message", updatedEntry.getMessage()));
        collection.updateOne(query, update);
    }
}
