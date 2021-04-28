package com.example.nycftaetix;

public class HelperTicket {

    public static boolean Oneway;
    public static boolean Weekly;
    public static boolean Monthly;

    public HelperTicket(){

    }

    public HelperTicket(boolean Oneway, boolean Weekly, boolean Monthly ){
        // this will be used to call ticket saves
        this.Oneway = Oneway;
        this.Weekly = Weekly;
        this.Monthly = Monthly;

    }

    public boolean getOneway(){
        return Oneway;
    }
    public boolean getWeekly(){
        return Weekly;
    }
    public boolean getMonthly(){
        return Monthly;
    }


}
