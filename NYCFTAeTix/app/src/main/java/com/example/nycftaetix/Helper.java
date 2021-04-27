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

    public Helper(){

    }
    public Helper(String email, String cNumber, String CVN, String Month, String Year, String name){
        this.email = email;
        this.cNumber = cNumber;
        this.CVN = CVN;
        this.Month = Month;
        this.Year = Year;
        this.name = name;
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

    public String getMonth(){
        return Month;
    }

    public String getYear(){
        return Year;
    }

    public String getData() {return date;}


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
