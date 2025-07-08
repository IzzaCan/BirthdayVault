package com.mycompany.birthdayvault.service;

import com.mycompany.birthdayvault.model.BirthdayEntry;

import java.io.*;
import java.util.List;

public class BirthdayDataManager {

    // SERIALIZATION - BACKUP TO FILE / Simpan list BirthdayEntry ke file
    public static void backupToFile(List<BirthdayEntry> entries, File file) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(entries);
            System.out.println("Backup completed: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error during backup:");
            e.printStackTrace();
        }
    }

    // DESERIALIZATION - RESTORE FROM FILE / Baca list BirthdayEntry dari file
    @SuppressWarnings("unchecked")
    public static List<BirthdayEntry> restoreFromFile(File file) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = in.readObject();
            if (obj instanceof List<?>) {
                return (List<BirthdayEntry>) obj;
            } else {
                System.err.println("Invalid data format in file.");
                return null;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error during restore:");
            e.printStackTrace();
            return null;
        }
    }
}
