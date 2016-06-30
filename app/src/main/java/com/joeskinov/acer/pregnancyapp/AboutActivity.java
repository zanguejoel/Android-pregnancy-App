package com.joeskinov.acer.pregnancyapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

    }

    @Override
    protected void onResume() {
        super.onResume();
        String name = getIntent().getStringExtra("name");
        Toast.makeText(this, name, Toast.LENGTH_LONG).show();
    }
}
