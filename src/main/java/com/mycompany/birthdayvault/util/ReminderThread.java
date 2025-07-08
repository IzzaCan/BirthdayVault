package com.mycompany.birthdayvault.util;

import com.mycompany.birthdayvault.controller.ReminderController;
import com.mycompany.birthdayvault.model.BirthdayEntry;

import javax.swing.*;
import java.util.List;

public class ReminderThread extends Thread {
    private final String username;
    private final ReminderController controller;
    private volatile boolean running = true;

    public ReminderThread(String username) {
        this.username = username;
        this.controller = new ReminderController();
    }

    @Override
    public void run() {
        // Ambil data yang ulang tahun hari ini
        List<BirthdayEntry> todayList = controller.getTodaysBirthdays(username);

        if (!todayList.isEmpty()) {
            StringBuilder message = new StringBuilder("Ulang tahun hari ini:\n");
            for (BirthdayEntry entry : todayList) {
                message.append("- ").append(entry.getName()).append("\n");
            }

            // Tampilkan notifikasi dialog
            JOptionPane.showMessageDialog(null, message.toString(), "Reminder", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    // Hentikan thread reminder
    public void stopReminder() {
        running = false;
        this.interrupt();
    }
}
