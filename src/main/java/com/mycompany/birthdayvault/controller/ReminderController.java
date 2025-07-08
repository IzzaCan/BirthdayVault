package com.mycompany.birthdayvault.controller;

import com.mycompany.birthdayvault.model.BirthdayEntry;
import com.mycompany.birthdayvault.service.MongoDBService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ReminderController {
    private final MongoDBService dbService;

    public ReminderController() {
        this.dbService = new MongoDBService();
    }

    // Ambil daftar ulang tahun user yang jatuh pada hari ini
    
    public List<BirthdayEntry> getTodaysBirthdays(String username) {
        List<BirthdayEntry> entries = dbService.getAllBirthdaysByUser(username);
        LocalDate today = LocalDate.now();

        // Filter hanya yang tanggal + bulan sama dengan hari ini
        
        return entries.stream()
                .filter(entry -> entry.getBirthDate().getDayOfMonth() == today.getDayOfMonth()
                        && entry.getBirthDate().getMonth() == today.getMonth())
                .collect(Collectors.toList());
    }
}
