package com.iryzhova.mydiplom4ik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.iryzhova.mydiplom4ik.auth.Registration;

public class MainActivity extends AppCompatActivity {

    private Button mButtonRegist, mButtonAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);

        mButtonAuth=findViewById(R.id.buttonAuth);
        mButtonRegist=findViewById(R.id.buttonRegist);

        mButtonRegist.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, Registration.class));
        });
    }




    

}