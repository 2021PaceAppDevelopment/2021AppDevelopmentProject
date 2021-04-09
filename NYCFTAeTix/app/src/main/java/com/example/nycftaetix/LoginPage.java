package com.example.nycftaetix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity {
    private EditText mEmail;
    private EditText mPassword;
    private long counter;
    private Button submitButton;


    private DatabaseReference info;
    private FirebaseDatabase database;
    private static final String TAG = "LoginPage";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        submitButton = findViewById(R.id.loginSubmit);
        mEmail = findViewById(R.id.signUpEmail);
        mPassword = findViewById(R.id.passwordLogin);

        //checking to see if editText fields are empty. If they are, don't log in person, otherwise login
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

    }

    public void submitLogin(View view) {
        String increment = Long.toString(counter);
        String pwd = mPassword.getText().toString();
        String em = mEmail.getText().toString();

        SignIn u = new SignIn(em, pwd);
        info.child(increment).setValue(u);
        counter++;


        //clearing text after button has been clicked
        mEmail.getText().clear();
        mPassword.getText().clear();
        //Hiding the keyboard after the button has been clicked
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if(inputManager != null){
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
        Intent profileIntent = new Intent(this, Profile.class);
        startActivity(profileIntent);
    }

    private void eventListener(DatabaseReference postReference){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                SignIn users = snapshot.getValue(SignIn.class);
                Log.i(TAG, counter + " Email: " + users.getEmail() + " password: " + users.getPassword());
                counter = 0;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Getting username and email failed", error.toException());
            }
        };
        postReference.addValueEventListener(valueEventListener);

    }
}