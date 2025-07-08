package com.mycompany.birthdayvault;

import com.mycompany.birthdayvault.view.Main;

public class BirthdayVaultApp {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}