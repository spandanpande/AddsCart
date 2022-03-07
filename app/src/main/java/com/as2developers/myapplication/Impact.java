package com.as2developers.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Impact extends AppCompatActivity {

    Button proceed;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_impact);
        back = findViewById(R.id.Img);
        proceed = findViewById(R.id.button3);

        Intent intent = getIntent();
        String AddressLine = intent.getStringExtra("AddressLine");
        String Items = intent.getStringExtra("items");
        String date = intent.getStringExtra("date");
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Impact.this, confirmation.class);
                i.putExtra("mode","Donated to NGO");
                i.putExtra("AddressLine",AddressLine);
                i.putExtra("items",Items);
                i.putExtra("date",date);
                startActivity(i);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}