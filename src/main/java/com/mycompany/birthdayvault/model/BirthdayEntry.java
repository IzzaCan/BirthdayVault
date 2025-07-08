package com.mycompany.birthdayvault.model;

import java.io.Serializable;
import java.time.LocalDate;

public class BirthdayEntry implements Serializable {

    private String user;
    private String name;
    private LocalDate birthDate;
    private String message;

    public BirthdayEntry(String user, String name, LocalDate birthDate, String message) {
        this.user = user;
        this.name = name;
        this.birthDate = birthDate;
        this.message = message;
    }

    // Getter & Setter
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
