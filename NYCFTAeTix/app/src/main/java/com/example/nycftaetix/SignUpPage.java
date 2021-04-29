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
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class SignUpPage extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private static final String TAG = SignUpPage.class.getSimpleName();
    private TextInputEditText email;
    private TextInputEditText password;
    private TextInputEditText Cpassword;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    public static boolean Oneway;
    public boolean Monthly;
    public boolean Weekly;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.signUpEmail_editText);
        password = findViewById(R.id.signUpPassword_editText);
        Cpassword = findViewById(R.id.confirmPassword_editText);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

    }

   //@Override
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
        if(password.getText().toString().equals(Cpassword.getText().toString())) {
            mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            Oneway = false;
                            Weekly = false;
                            Monthly = false;
                            HelperTicket HelperTic = new HelperTicket(Oneway,Weekly,Monthly);

                            databaseReference.child(user.getUid()).setValue(HelperTic);
                            Toast.makeText(getApplicationContext(),"User Information updated", Toast.LENGTH_LONG).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpPage.this, "This email might not be valid or is already in use, Password may be too short",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    });
        }else{
            Toast.makeText(SignUpPage.this, "Passwords do not match " + password.getText().toString() +Cpassword.getText().toString(), Toast.LENGTH_SHORT).show();

        }

    }

    public void updateUI(FirebaseUser account){

        if(account != null){
            Toast.makeText(this,"U Signed Up successfully",Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,Profile.class));

        }else {
            Toast.makeText(this,"U Didnt sign up",Toast.LENGTH_LONG).show();
        }

    }

    private void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());
    }


    public void onDataCatch(DataSnapshot dataSnapshot){
        String userDetails = dataSnapshot.getValue().toString();;
    }
}