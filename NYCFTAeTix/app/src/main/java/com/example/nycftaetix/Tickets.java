package com.example.nycftaetix;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.chip.Chip;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class Tickets extends AppCompatActivity {
    private Chip ticketChip;
    private TextView oneWayTextView;
    private TextView weeklyTextView;
    private Button activateButton;
    private Button activeButton;
    private Chip oneWayChip;
    private Chip monthlyChip;
    private Chip weeklyChip;
    private AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets);
        ticketChip = findViewById(R.id.tickets_chip);
        oneWayTextView = findViewById(R.id.one_way_textView);
        weeklyTextView = findViewById(R.id.weekly_textView);
        activateButton = findViewById(R.id.activate_button);
        activeButton = findViewById(R.id.active_button);
        oneWayChip = findViewById(R.id.one_way_ticket_chip);
        monthlyChip = findViewById(R.id.monthly_ticket_chip);
        weeklyChip = findViewById(R.id.weekly_ticket_chip);


    }

    public void onMonthlyTickets(View view) {
        alertDialog = new MaterialAlertDialogBuilder(Tickets.this)
                .setTitle("Purchase Tickets")
                .setMessage("Monthly tickets cost $127.00. " +
                        "Would you like to proceed with the purchase?")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }

    public void onWeeklyTickets(View view) {
        alertDialog = new MaterialAlertDialogBuilder(Tickets.this)
                .setTitle("Purchase Tickets")
                .setMessage("Weekly tickets cost $33.00. " +
                        "Would you like to proceed with the purchase?")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }

    public void onOneWayTickets(View view) {
        alertDialog = new MaterialAlertDialogBuilder(Tickets.this)
                .setTitle("Purchase Tickets")
                .setMessage("One way tickets cost $2.75. " +
                        "Would you like to proceed with the purchase?")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
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


    public void ActivateTicket(View view) {
        Intent TicketPurchased = new Intent(this, TicketPurchase.class);
        startActivity(TicketPurchased);
    }



}