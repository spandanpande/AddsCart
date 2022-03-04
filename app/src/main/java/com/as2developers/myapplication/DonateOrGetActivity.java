package com.as2developers.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;


public class DonateOrGetActivity extends AppCompatActivity {

    ImageButton back;
    Button cash,donate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_or_get);

        back = findViewById(R.id.img);
        cash = findViewById(R.id.button);
        donate = findViewById(R.id.button2);

        Intent intent = getIntent();
        String AddressLine = intent.getStringExtra("AddressLine");
        String ScrapItem = intent.getStringExtra("items");
        String date = intent.getStringExtra("date");
        cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DonateOrGetActivity.this,confirmation.class);
                i.putExtra("mode","cash");
                i.putExtra("AddressLine",AddressLine);
                i.putExtra("items",ScrapItem);
                i.putExtra("date",date);
                startActivity(i);
            }
        });

        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DonateOrGetActivity.this, Impact.class);
                i.putExtra("mode","NGO");
                i.putExtra("AddressLine",AddressLine);
                i.putExtra("items",ScrapItem);
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