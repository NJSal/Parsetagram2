package com.example.instagramclone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class RegisterActivity extends Activity {
    public static final String TAG = "RegisterActivity";
    private EditText etUsername;
    private EditText etEmail;
    private EditText etPassword;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create the ParseUser
                ParseUser newuser = new ParseUser();
                // Set user attributes
                newuser.setUsername(etUsername.getText().toString());
                newuser.setEmail(etEmail.getText().toString());
                newuser.setPassword(etPassword.getText().toString());
                // Invoke sign up in the background
                newuser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null){
                            Toast.makeText(RegisterActivity.this, "Successfully Registered!", Toast.LENGTH_LONG).show();
                            gotoLoginActivity();
                            Toast.makeText(RegisterActivity.this, "Went to LoginActivity", Toast.LENGTH_LONG).show();
                        } else{
                            Toast.makeText(RegisterActivity.this, "Unable to Register! " + e.getMessage(), Toast.LENGTH_LONG).show();
                            Log.e(TAG, "Unable to register new user", e);
                        }
                    }
                });
            }
        });
    }

    private void gotoLoginActivity() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}
