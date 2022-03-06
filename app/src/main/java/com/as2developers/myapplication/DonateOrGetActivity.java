package com.as2developers.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class DonateOrGetActivity extends AppCompatActivity {

    ImageButton back;
    Button cash,donate;
    String Name , Address;
    TextView userName,userAddress;

    FirebaseDatabase database;
    DatabaseReference ref;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_or_get);

        back = findViewById(R.id.img);
        cash = findViewById(R.id.button);
        donate = findViewById(R.id.button2);
        userName = findViewById(R.id.userName);
        userAddress = findViewById(R.id.userAddress);

        Intent intent = getIntent();
        String AddressLine = intent.getStringExtra("AddressLine");
        String ScrapItem = intent.getStringExtra("items");
        String date = intent.getStringExtra("date");

        userAddress.setText(AddressLine);

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