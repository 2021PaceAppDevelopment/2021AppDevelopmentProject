package com.example.nycftaetix;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginPage extends AppCompatActivity {

    private static final String TAG = "LoginPage";
    private long counter;
    private Button submitButton;
    private FirebaseAuth mAuth;
    private DatabaseReference info;
    private FirebaseDatabase database;
    private TextInputEditText mEmail;
    private TextInputEditText mPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar loginToolbar = findViewById(R.id.login_toolbar);
        setSupportActionBar(loginToolbar);


        setContentView(R.layout.activity_login_page);
        mAuth = FirebaseAuth.getInstance();
        submitButton = findViewById(R.id.loginSubmit);
        mEmail = findViewById(R.id.signUpEmail_editText);
        mPassword = findViewById(R.id.signUpPassword_editText);
        counter = 0;


       /* //checking to see if editText fields are empty. If they are, don't log in person, otherwise login
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mEmail.getText().toString()) && TextUtils.isEmpty(mPassword.getText().toString())){
                    Toast.makeText(LoginPage.this, "Please input email and password to login. ", Toast.LENGTH_SHORT).show();
                }else{
                    submitLogin(submitButton);
                }
            }
        });
        //retrieving an instance of database, and referencing the location it should write to
        database = FirebaseDatabase.getInstance();
        info = database.getReference().child("users");
        info.keepSynced(true);
        info.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data : snapshot.getChildren()){
                    SignIn users = (SignIn) data.getValue(SignIn.class);
                    Log.i(TAG, counter + "Email: " + users.getEmail() + " password: " + users.getPassword());
                    counter++;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Getting username and email failed", error.toException());
            }
        });*/
    }

    public void submitLogin(View view) {
        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(mEmail.getText().toString(), mPassword.getText().toString())
                .addOnCompleteListener(this, task -> {
                    Log.i("LoginPageInput", "This is Email: " + mEmail.getText().toString() + "This is Password " + mPassword.getText());
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        Toast.makeText(LoginPage.this, "Wrong Email and/or Password",
                                Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                });
        // [END sign_in_with_email]



        /*long increment = counter;
        String pwd = mPassword.getText().toString();
        String em = mEmail.getText().toString();
        SignIn u = new SignIn(em, pwd);
        info.child(String.valueOf(increment)).setValue(u);
        //clearing text after button has been clicked
        mEmail.getText().clear();
        mPassword.getText().clear();
        //Once logged in goes to ticket page
        Intent profileIntent = new Intent(this, Tickets.class);
        startActivity(profileIntent);*/
    }


    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Toast.makeText(this, "You Signed in!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, Profile.class));

        } else {
            Toast.makeText(this, "Your Sign in has failed", Toast.LENGTH_LONG).show();
        }

    }

}