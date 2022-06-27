package com.croft.workoutApp.utils;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

public class RegisterUtil {

    public static boolean checkPass(String password, String confirm) {
        String pass1 = password;
        String pass2 = confirm;

        return pass1.equals(pass2);
    }


    public static String formatName(String name) {

        if (name.length() < 0) {
            return null;
        }
        if (name.length() == 1) {
            return name.substring(0, 1).toUpperCase();
        }
        String fixedName = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        return fixedName;
    }

}
