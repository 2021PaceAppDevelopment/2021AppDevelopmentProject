package com.example.nycftaetix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
    }

    public void Signup(View view) {
        Intent SignupPage = new Intent(this, SignUpPage.class);
        startActivity(SignupPage);
    }

    public void Login(View view) {
        Intent LoginPage = new Intent(this, LoginPage.class);
        startActivity(LoginPage);
    }
}