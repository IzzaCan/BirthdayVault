package com.mycompany.birthdayvault.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageUtil {
    private static String currentLanguage = "en"; // Default
    private static ResourceBundle bundle;

    public static void setLanguage(String languageCode) {
        currentLanguage = languageCode;
        bundle = ResourceBundle.getBundle("i18n.messages", new Locale(languageCode));
    }

    public static String getCurrentLanguage() {
        return currentLanguage;
    }

    public static String getString(String key) {
        if (bundle == null) {
            setLanguage(currentLanguage);
        }
        return bundle.getString(key);
    }
}
