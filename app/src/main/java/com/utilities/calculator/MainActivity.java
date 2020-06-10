package com.utilities.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner = findViewById(R.id.methods);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_methods, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }
    public void Calculate(View view) {
        Spinner spinner = findViewById(R.id.methods);
        EditText x = findViewById(R.id.editA);
        EditText y = findViewById(R.id.editB);
        float a = new Float(x.getText().toString());
        float b = new Float(y.getText().toString());
        float c;
        switch(spinner.getSelectedItem().toString()) {
            case "divided by":
                c = a/b;
                break;
            case "times":
                c = a*b;
                break;
            case "plus":
                c = a+b;
                break;
            case "minus":
                c = a-b;
                break;
            default:
                c = 0.f;
                break;
        }
        TextView z = findViewById(R.id.answer);
        z.setText(c+"");
        //z.setText(a + " " +b);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}