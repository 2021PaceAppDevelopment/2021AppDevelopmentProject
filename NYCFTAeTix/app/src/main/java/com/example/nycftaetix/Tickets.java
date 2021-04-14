package com.example.nycftaetix;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.chip.Chip;

public class Tickets extends AppCompatActivity {
    private Chip ticketChip;
    private TextView oneWayTextView;
    private TextView weeklyTextView;
    private Button activateButton;
    private Button activeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets);
        ticketChip = findViewById(R.id.tickets_chip);
        oneWayTextView = findViewById(R.id.one_way_textView);
        weeklyTextView = findViewById(R.id.weekly_textView);
        activateButton = findViewById(R.id.activate_button);
        activeButton = findViewById(R.id.active_button);

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

    public void onWallet(View view) {
        oneWayTextView.setVisibility(View.VISIBLE);
        weeklyTextView.setVisibility(View.VISIBLE);
        activeButton.setVisibility(View.VISIBLE);
        activateButton.setVisibility(View.VISIBLE);
    }
}