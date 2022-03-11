package com.example.interviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnLaunchApp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assign value to the button
        btnLaunchApp = findViewById(R.id.btnLaunchApp);

        //Click Listener for launch button
        btnLaunchApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCountryActivity();
            }
        });
    }

    public void openCountryActivity(){
        Intent intent = new Intent(this, CountryActivity.class);
        startActivity(intent);
    }
}