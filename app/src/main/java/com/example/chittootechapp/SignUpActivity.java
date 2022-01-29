package com.example.chittootechapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    EditText edit_email, edit_password, edit_name, edit_number;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edit_email = findViewById(R.id.edit_email);
        edit_password = findViewById(R.id.edit_password);
        edit_name = findViewById(R.id.edit_name);
        edit_number = findViewById(R.id.edit_number);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        Button button_login = findViewById(R.id.button_login);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });

        Button button_register = findViewById(R.id.button_register);
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerformAuth();
            }
        });
    }

    private void PerformAuth() {
        String username = edit_name.getText().toString();
        String email = edit_email.getText().toString();
        String password = edit_password.getText().toString();
        String number = edit_number.getText().toString();

        if(username.isEmpty() || username.length()<6){
            edit_name.setError("Enter Proper Username (>6 words)");
        }
        else if(email.isEmpty()){
            edit_email.setError("Enter Proper Email");
        }
        else if(number.isEmpty() || number.length()<10){
            edit_number.setError("Enter Proper PhoneNumber");
        }
        else if(password.isEmpty() || password.length()<7){
            edit_password.setError("Enter Proper Password (>7 words)");
        }
        else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        sendUserToNextActivity();
                        Toast.makeText(SignUpActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(SignUpActivity.this,"" +task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(intent);
    }
}