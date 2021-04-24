package com.example.nycftaetix;

import android.content.Context;
import android.widget.Toast;

public class Helper {
    public static final String name = "Name";
    public static final String email = "email";
    public static final String cNumber = "1234123412341234";
    public static final String CVN = "123";
    public static final String Month = "00";
    public static final String Year = "00";

    public Userinfromation(){

    }
    public Userinformation(String email, String cNumber, String CVN, String Month, String Year, String name){
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
