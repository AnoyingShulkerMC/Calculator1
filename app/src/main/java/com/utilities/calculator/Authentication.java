package com.utilities.calculator;

import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.biometrics.BiometricPrompt;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Authentication extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_authenticate);
            FingerprintManager fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
            if (!fingerprintManager.isHardwareDetected()) {
                Intent intent = new Intent(this, PasswordAuthentication.class);
                startActivity(intent);
                return;
            } else {
                if (!fingerprintManager.hasEnrolledFingerprints()) {
                    Intent intent = new Intent(this, PasswordAuthentication.class);
                    startActivity(intent);
                    return;
                }
            }
            final Executor executor = Executors.newSingleThreadExecutor();
            final BiometricPrompt biometricPrompt = new BiometricPrompt.Builder(this)
                    .setTitle("Fingerprint Authentication")
                    .setSubtitle("")
                    .setDescription("")
                    .setNegativeButton("Cancel", executor, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).build();
            Button button = findViewById(R.id.auth);
            final Authentication thisClass = this;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    biometricPrompt.authenticate(new CancellationSignal(), executor, new BiometricPrompt.AuthenticationCallback() {
                        @Override
                        public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                            Intent intent = new Intent(thisClass, MainActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            });
        } catch(Exception e) {
            Intent intent = new Intent(this, PasswordAuthentication.class);
            startActivity(intent);
        }

    }
}
