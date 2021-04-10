package com.example.nycftaetix;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    public void onTickets(View view) {
        Intent ticketPage = new Intent(this, Tickets.class);
        startActivity(ticketPage);
    }
    public void onMap(View view) {
        Intent mapIntent = new Intent(this, Map.class);
        startActivity(mapIntent);
    }

    public void onProfile(View view) {
        Intent profileIntent = new Intent(this, Profile.class);
        startActivity(profileIntent);
    }

    public void logOut(View view) {
        Intent logOutIntent = new Intent(this, LoginPage.class);
        startActivity(logOutIntent);
    }
}