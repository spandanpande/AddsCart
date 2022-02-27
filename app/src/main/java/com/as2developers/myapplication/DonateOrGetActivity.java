package com.as2developers.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;


public class DonateOrGetActivity extends AppCompatActivity {

    ImageButton profile;
    Button cash,donate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_or_get);

        cash = findViewById(R.id.button);
        donate = findViewById(R.id.button2);

        cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DonateOrGetActivity.this,confirmation.class);
                i.putExtra("mode","cash");
                startActivity(i);
            }
        });

        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DonateOrGetActivity.this, Impact.class);
                startActivity(i);
            }
        });

    }
}