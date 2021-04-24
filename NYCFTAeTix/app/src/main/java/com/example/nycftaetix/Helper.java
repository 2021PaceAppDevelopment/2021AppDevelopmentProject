package com.example.nycftaetix;

import android.content.Context;
import android.widget.Toast;

public class Helper {
    public static final String NAME = "Name";
    public static final String cNumber = "1234123412341234";
    public static final String CVN = "123";
    public static final String Month = "00";
    public static final String Year = "00";

    public static boolean isValidEmail(String email){
        if(email.contains("@")){
            return true;
        }
        return false;
    }

    public static void dispalyMessageToast(Context context, String displayMessage){
        Toast.makeText(context, displayMessage, Toast.LENGTH_LONG).show();
    }
}
