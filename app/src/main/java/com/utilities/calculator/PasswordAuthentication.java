package com.utilities.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PasswordAuthentication extends AppCompatActivity {
    private static final String psswd = "PressY0urButt0n";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_authentication);
    }
    public void Authenticate(View view) {
        EditText text = findViewById(R.id.password);
        if(text.getText().toString() == psswd) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            TextView textView = findViewById(R.id.wrong);
            textView.setVisibility(View.VISIBLE );
        }
    }
}
