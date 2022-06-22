package com.croft.workoutApp.utils;

public class RegisterUtil {

    public static boolean checkPass(String password, String confirm) {
        String pass1 = password;
        String pass2 = confirm;

        if (pass1.equals(pass2)) {
            return true;
        }
        return false;
    }

    public static String formatName(String name) {

        if (name.length() < 0) {
            return null;
        }
        if (name.length() == 1) {
            return name.substring(0, 1).toUpperCase();
        }
        String fixedName = name.substring(0, 1).toUpperCase() + name.substring(1);
        return fixedName;
    }

}
