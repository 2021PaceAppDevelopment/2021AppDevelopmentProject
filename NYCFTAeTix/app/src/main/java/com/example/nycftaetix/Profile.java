package com.example.nycftaetix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class Profile extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private Fragment fragment = null;
    private FirebaseAuth firebaseAuth;
    private TextView textViewEmailName;
    private DatabaseReference databaseReference;
    Button btnEdit;

    private TextInputEditText editcNumber;
    private TextInputEditText  editCVN;
    private TextInputEditText  editMonth;
    private TextInputEditText  editname;
    private TextInputEditText editdate;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private DatabaseReference myRef;
    public boolean oneway;
    public boolean weekly;
    public boolean monthly;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar  profileToolbar =  findViewById(R.id.profile_toolbar);
        setSupportActionBar( profileToolbar);


        firebaseAuth=FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(getApplicationContext(),LoginPage.class));
        }
        databaseReference = FirebaseDatabase.getInstance().getReference();
        editcNumber= (TextInputEditText)findViewById(R.id.credit_card_number_editText);
        editCVN = (TextInputEditText) findViewById(R.id.cvv_editText);
        editdate = (TextInputEditText) findViewById(R.id.cc_expire_editText);
        editname = (TextInputEditText) findViewById(R.id.name_on_card_editText);
        btnEdit = (Button) findViewById(R.id.add_credit_card_button);
        FirebaseUser user=firebaseAuth.getCurrentUser();
        textViewEmailName =(TextView)findViewById(R.id.NYCFTA_Email_textView);
        textViewEmailName.setText(user.getEmail()); // use this as templet for setting card number might need an if statment for start

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();


    }

    private void Helper(){
        String cNumber = editcNumber.getText().toString().trim();
        String Date = editdate.getText().toString().trim();
        String name = editname.getText().toString().trim();
        String CVN = editCVN.getText().toString().trim();
        String email = textViewEmailName.getText().toString().trim();


        FirebaseUser user = firebaseAuth.getCurrentUser();

        myRef = databaseReference.child(user.getUid());
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HelperTicket HelperTicket;
                HelperTicket =(HelperTicket)snapshot.getValue(HelperTicket.class);
                oneway = HelperTicket.getOneway();
                weekly = HelperTicket.getWeekly();
                monthly = HelperTicket.getMonthly();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("the read failed");
            }
        });

        Helper Helper = new Helper(cNumber,CVN,name,Date,email, oneway, weekly, monthly);
        HelperTicket helpTicket  = new HelperTicket(oneway,weekly,monthly);

        databaseReference.child(user.getUid()).setValue(Helper);
        Toast.makeText(getApplicationContext(),"User Information updated", Toast.LENGTH_LONG).show();
    }

    public void onClick(View view){
        if(view == btnEdit){
            Helper();

            sendUserData();
            finish();
            startActivity(new Intent(Profile.this, Tickets.class));
        }
    }
    private void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());
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