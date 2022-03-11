package com.as2developers.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.as2developers.myapplication.Modals.UserModal;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


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

        database=FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        ref = database.getReference("Users").child(user.getPhoneNumber());

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserModal user01 = snapshot.getValue(UserModal.class);
                String username = user01.getName();
                userName.setText(username);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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
                i.putExtra("Latitude",getIntent().getStringExtra("Latitude"));
                i.putExtra("Longitude",getIntent().getStringExtra("Longitude"));
                i.putExtra("locality",getIntent().getStringExtra("locality"));
                i.putExtra("longAddress",getIntent().getStringExtra("longAddress"));
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
                i.putExtra("Latitude",getIntent().getStringExtra("Latitude"));
                i.putExtra("Longitude",getIntent().getStringExtra("Longitude"));
                i.putExtra("locality",getIntent().getStringExtra("locality"));
                i.putExtra("longAddress",getIntent().getStringExtra("longAddress"));
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