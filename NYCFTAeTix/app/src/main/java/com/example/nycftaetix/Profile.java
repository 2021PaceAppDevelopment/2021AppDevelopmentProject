package com.example.nycftaetix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    Button btnEdit;

    private TextInputEditText editcNumber;
    private TextInputEditText  editCVN;
    private TextInputEditText  editMonth;
    private TextInputEditText  editname;
    private TextInputEditText editdate;
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

        editcNumber= (TextInputEditText)findViewById(R.id.credit_card_number_editText);
        editCVN = (TextInputEditText) findViewById(R.id.cvv_editText);
        editdate = (TextInputEditText) findViewById(R.id.cc_expire_editText);
        editname = (TextInputEditText) findViewById(R.id.name_on_card_editText);
        btnEdit = (Button) findViewById(R.id.add_credit_card_button);
        FirebaseUser user=firebaseAuth.getCurrentUser();
        btnEdit.setOnClickListener((View.OnClickListener) this);
        textViewEmailName =(TextView)findViewById(R.id.NYCFTA_Email_textView);
        textViewEmailName.setText(user.getEmail());
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();


    }

    private void Helper(){
        String cNumber = editcNumber.getText().toString().trim();
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