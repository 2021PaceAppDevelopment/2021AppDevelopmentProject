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
    public static boolean Oneway;
    public static boolean Weekly;
    public static boolean Monthly;

    public Helper(){

    }
    public Helper(String cNumber, String CVN, String name, String Date){
        this.cNumber = cNumber;
        this.CVN = CVN;
        this.name = name;
        this.date = Date;
    }

    public Helper(boolean Oneway, boolean Weekly, boolean Monthly ){
        // this will be used to call ticket saves
        this.Oneway = Oneway;
        this.Weekly = Weekly;
        this.Monthly = Monthly;

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
