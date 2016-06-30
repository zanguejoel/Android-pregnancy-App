package com.joeskinov.acer.pregnancyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Button pregnancyTest = (Button)findViewById(R.id.pregnancyTest);
        Button pregnancyTips = (Button)findViewById(R.id.pregnancyTips);
        Button about = (Button)findViewById(R.id.about);

        pregnancyTest.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        startMySecondActivity("Pregnancy Test");
                    }
                }
        );

        pregnancyTips.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        startTipActivity("Pregnancy Tips", "Some pregnancy Tips");
                    }
                }
        );

        about.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        startAboutActivity("About Menu");
                    }
                }
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        String name = getIntent().getStringExtra("name");
        Toast.makeText(this, name, Toast.LENGTH_LONG).show();
    }
    private void startMySecondActivity(String  myString){

        Intent startIntent = new Intent(this, MainActivity.class);
        startIntent.putExtra("name", myString);
        startActivity(startIntent);
    }

    private void startTipActivity(String  myString, String string){

        Intent startIntent = new Intent(this, ResultActivity.class);
        startIntent.putExtra("name", myString);
        startIntent.putExtra("message", string);
        startActivity(startIntent);
    }
    private void startAboutActivity(String  myString){

        Intent startIntent = new Intent(this, AboutActivity.class);
        startIntent.putExtra("name", myString);
        startActivity(startIntent);
    }
}
