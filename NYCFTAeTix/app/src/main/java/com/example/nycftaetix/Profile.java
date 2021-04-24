package com.example.nycftaetix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;


public class Profile extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private Fragment fragment = null;


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
        FirebaseAuth.getInstance().signOut();
        Intent MainPageIntent = new Intent(this, MainPage.class);
        startActivity(MainPageIntent);
    }
}