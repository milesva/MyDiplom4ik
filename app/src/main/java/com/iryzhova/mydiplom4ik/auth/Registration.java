package com.iryzhova.mydiplom4ik.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.iryzhova.mydiplom4ik.R;
import com.iryzhova.mydiplom4ik.UserProfile;

import java.util.regex.Pattern;

public class Registration extends AppCompatActivity {

    private EditText mEditTextPasswordR, mEditTextUsernameR;
    private Button mButtonRegistR;

    ProgressDialog progressDialog;


    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Create account");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        mAuth = FirebaseAuth.getInstance();

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        mEditTextPasswordR=findViewById(R.id.editTextPasswordR);
        mEditTextUsernameR=findViewById(R.id.editTextUsernameR);
        mButtonRegistR=findViewById(R.id.buttonRegistR);
        mButtonRegistR.setOnClickListener(v->{
            String email=mEditTextUsernameR.getText().toString().trim();
            String password=mEditTextPasswordR.getText().toString().trim();

            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                mEditTextUsernameR.setError("Invalid");
                mEditTextUsernameR.setFocusable(true);
            }
            else if (password.length()<6){
                mEditTextPasswordR.setError("Very short");
                mEditTextPasswordR.setFocusable(true);
            }
            else{
                registerUser(email, password);
            }
        });

    }

    private void registerUser(String email, String password) {
        progressDialog.show();


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(Registration.this, "Successful authentication",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Registration.this, UserProfile.class));
                            finish();
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(Registration.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(Registration.this, ""+e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }



}