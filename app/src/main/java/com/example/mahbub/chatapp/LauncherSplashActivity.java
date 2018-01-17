package com.example.mahbub.chatapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LauncherSplashActivity extends AppCompatActivity {

    private static final String TAG = "LauncherSplashActivity";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        Log.d(TAG, "onStart: Current User: " + currentUser);
        updateUI(currentUser);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // For offline data
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            // If user is logged in then directly call the MainOptionSelectActivity
            Intent intent = new Intent(LauncherSplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            // For Showing the login/registration UI first
            // We have to call the login/registration activity first by intent
            Intent intent = new Intent(LauncherSplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
