package com.joeskinov.acer.pregnancyapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {
    public String[] menuArray = {"Don't forget breakfast" ,
            "Eat foods with fiber. " ,
            "Choose healthy snacks",
            "Take a prenatal vitamin with iron and folic acid every day.",
            " Eat up to 12 ounces a week (2 average meals) of fish or shellfish. ",
            " Stay away from soft cheeses and lunch meat. ",

            };
    private String message ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_home, menuArray);
        ListView listView = (ListView) findViewById(R.id.menulist);
        listView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        String name = getIntent().getStringExtra("name");
        message = getIntent().getStringExtra("message");
        TextView messageTest = (TextView) findViewById(R.id.messagetext);
        messageTest.setText(message);
        Toast.makeText(this, name, Toast.LENGTH_LONG).show();
    }
}
