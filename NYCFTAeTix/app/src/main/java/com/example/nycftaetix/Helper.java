package com.example.nycftaetix;

import android.content.Context;
import android.widget.Toast;

public class Helper {
    public static String name = "Name";
    public static String email = "email";
    public static String cNumber = "1234123412341234";
    public static String CVN = "123";
    public static String Month = "00";
    public static String Year = "00";
    public static String date = "00/00";
    public static boolean Monthly;
    public static boolean Weekly;
    public static boolean Oneway;


    public Helper(){

    }
    public Helper(String cNumber, String CVN, String name, String Date,String email, boolean oneway, boolean weekly, boolean monthly){
        this.cNumber = cNumber;
        this.CVN = CVN;
        this.name = name;
        this.date = Date;
        this.email = email;
        this.Oneway = oneway;
        this.Weekly = weekly;
        this.Monthly = monthly;
    }


    public String getEmail(){
        return email;
    }

    public String getCVN(){
        return CVN;
    }

    public String getcNumber(){
        return cNumber;
    }

    public String getData() {return date;}

    public String getName() {return name;}

    public boolean getOneway(){
        return Oneway;
    }
    public boolean getWeekly(){
        return Weekly;
    }
    public boolean getMonthly(){
        return Monthly;
    }


/*    public static boolean isValidEmail(String email){
        if(email.contains("@")){
            return true;
        }
        return false;
    }

    public static void dispalyMessageToast(Context context, String displayMessage){
        Toast.makeText(context, displayMessage, Toast.LENGTH_LONG).show();
    }*/
}
