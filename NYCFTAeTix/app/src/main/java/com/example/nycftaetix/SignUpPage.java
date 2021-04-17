package com.example.nycftaetix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpPage extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private static final String TAG = SignUpPage.class.getSimpleName();
    private EditText email;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.signUpEmail);
        password = findViewById(R.id.signUpPassword);

    }

   @Override
   // need to work on placment can tell if signed in and will pull you in, may need to be on another page
/*    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            startActivity(new Intent(this,Profile.class));
        }
    }*/



    public void SubmitSignUp(View view) {
       // Log.i("SIGN UP PAGE", "This is email: " + email.toString() + ", This is Password: " + password.toString());

        mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(SignUpPage.this, "This email might not be valid or is already in use",
                                Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                });

    }

    public void updateUI(FirebaseUser account){

        if(account != null){
            Toast.makeText(this,"U Signed Up successfully",Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,Profile.class));

        }else {
            Toast.makeText(this,"U Didnt sign in",Toast.LENGTH_LONG).show();
        }

    }
}