package com.example.nycftaetix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class Profile extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private Fragment fragment = null;
    private FirebaseAuth firebaseAuth;
    private TextView textViewEmailName;
    private DatabaseReference databaseReference;
    private TextInputEditText cNumber;
    private TextInputEditText  CVN;
    private TextInputEditText  Month;
    private TextInputEditText  name;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        firebaseAuth=FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(getApplicationContext(),LoginPage.class));
        }
        databaseReference = FirebaseDatabase.getInstance().getReference();
        cNumber = findViewById(R.id.name_on_card_editText);
        CVN = findViewById(R.id.cvv_editText);
        

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