package com.example.nycftaetix;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;


public class Tickets extends AppCompatActivity {
    private Chip ticketChip;
    private TextView oneWayTextView;
    private TextView weeklyTextView;
    private Button oneWayActivateButton;
    private Button weeklyActivateButton;
    private Button monthlyActivateButton;
    private Chip oneWayChip;
    private Chip monthlyChip;
    private Chip weeklyChip;
    private int buttonCounter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets);
        ticketChip = findViewById(R.id.tickets_chip);
        oneWayTextView = findViewById(R.id.one_way_textView);
        weeklyTextView = findViewById(R.id.weekly_textView);
        oneWayChip = findViewById(R.id.one_way_ticket_chip);
        monthlyChip = findViewById(R.id.monthly_ticket_chip);
        weeklyChip = findViewById(R.id.weekly_ticket_chip);
        oneWayActivateButton = findViewById(R.id.oneWay_active_button);
        weeklyActivateButton = findViewById(R.id.weekly_active_button);
        monthlyActivateButton = findViewById(R.id.monthly_active_button);


    }



    public void onMonthlyTickets(View view) {
        new MaterialAlertDialogBuilder(Tickets.this)
                .setTitle("Purchase Tickets")
                .setMessage("Monthly tickets cost $127.00. " +
                        "Would you like to proceed with the purchase?")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder alertDialogTwo = new AlertDialog.Builder(Tickets.this);

                        alertDialogTwo.setTitle("Purchase complete")
                                .setMessage("Monthly tickets last for 30 days from date of activation." +
                                        "You may activate your monthly tickets whenever you need it.")
                                .setNeutralButton("Close", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        monthlyActivateButton.setBackgroundColor(getResources().getColor(R.color.blue));
                                        monthlyActivateButton.setText(getResources().getString(R.string.activate));
                                        monthlyActivateButton();

                                    }
                                }).show();

                    }
                }).show();
    }
    //#004FB1

    public void onWeeklyTickets(View view) {
        new MaterialAlertDialogBuilder(Tickets.this)
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
                        AlertDialog.Builder alertDialogTwo = new AlertDialog.Builder(Tickets.this);

                        alertDialogTwo.setTitle("Purchase complete")
                                .setMessage("Weekly tickets last for 7 days from date of activation." +
                                        "You may activate your weekly tickets whenever you need it.")
                                .setNeutralButton("Close", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        weeklyActivateButton.setBackgroundColor(getResources().getColor(R.color.blue));
                                        weeklyActivateButton.setText(getResources().getString(R.string.activate));
                                        weeklyButton();

                                    }
                                }).show();

                    }
                }).show();
    }

    public void onOneWayTickets(View view) {
       new MaterialAlertDialogBuilder(Tickets.this)
                .setTitle("Purchase Tickets")
                .setMessage("One way tickets cost $2.75. " +
                        "Would you like to proceed with the purchase?")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder alertDialogTwo = new AlertDialog.Builder(Tickets.this);

                        alertDialogTwo.setTitle("Purchase complete")
                                .setMessage("One-way tickets can be used for one bus ride." +
                                        "You may activate your weekly tickets whenever you need it.")
                                .setNeutralButton("Close", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        oneWayActivateButton.setBackgroundColor(getResources().getColor(R.color.blue));
                                        oneWayActivateButton.setText(getResources().getString(R.string.activate));
                                        oneWayButton();
                                    }
                                }).show();
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


    private void weeklyButton(){
        weeklyActivateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buttonCounter == 0){
                    weeklyActivateButton.setText(getResources().getString(R.string.active));
                    buttonCounter++;
                }else if (buttonCounter == 1){
                    Intent TicketPurchased = new Intent(Tickets.this, TicketPurchase.class);
                    startActivity(TicketPurchased);
                    buttonCounter = 0;
                }
            }
        });
    }

    private void oneWayButton(){
        oneWayActivateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buttonCounter == 0){
                    oneWayActivateButton.setText(getResources().getString(R.string.active));
                    buttonCounter++;
                }else if (buttonCounter == 1){
                    Intent TicketPurchased = new Intent(Tickets.this, TicketPurchase.class);
                    startActivity(TicketPurchased);
                    buttonCounter = 0;
                }
            }
        });
    }
    private void monthlyActivateButton(){
        monthlyActivateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buttonCounter == 0){
                    monthlyActivateButton.setText(getResources().getString(R.string.active));
                    buttonCounter++;
                }else if (buttonCounter == 1){
                    Intent TicketPurchased = new Intent(Tickets.this, TicketPurchase.class);
                    startActivity(TicketPurchased);
                    buttonCounter = 0;
                }
            }
        });
    }

}