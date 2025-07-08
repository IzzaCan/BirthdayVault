package com.mycompany.birthdayvault.service;

import com.mycompany.birthdayvault.model.User;
import com.mycompany.birthdayvault.util.HashUtil;
import com.mongodb.client.*;
import org.bson.Document;

public class AuthService {
    // Koleksi MongoDB untuk user
    private final MongoCollection<Document> users;

    public AuthService() {
        // Inisialisasi koneksi ke database MongoDB dan koleksi users
        users = MongoClients.create("mongodb://localhost:27017")
                             .getDatabase("birthdayvault_db")
                             .getCollection("users");
    }

    // Register user baru
    public boolean register(String username, String password) {
        if (getUser(username) != null) return false; // Jika user sudah ada, gagal
        users.insertOne(new Document()
                .append("username", username)
                .append("hashedPassword", HashUtil.hashPassword(password)));
        return true;
    }

    // Login: cek username dan password
    public boolean login(String username, String password) {
        User user = getUser(username);
        return user != null && HashUtil.verifyPassword(password, user.getHashedPassword());
    }

    // Ambil user dari MongoDB berdasarkan username
    private User getUser(String username) {
        Document doc = users.find(new Document("username", username)).first();
        // Konversi Document MongoDB ke objek User
        return (doc != null) ? new User(doc.getString("username"), doc.getString("hashedPassword")) : null;
    }
}
