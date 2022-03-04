package com.as2developers.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class confirmation extends AppCompatActivity {

    TextView paymentMode,addressLine,items,expectedDate;
    Button button;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth mAuth;
    String AddressLine,Items,date,mode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        paymentMode = findViewById(R.id.paymentMode);
        button = findViewById(R.id.button2);
        addressLine = findViewById(R.id.addressLine);
        items = findViewById(R.id.list);
        expectedDate = findViewById(R.id.expected_pickup);

        database = FirebaseDatabase.getInstance();
        reference= database.getReference("Users").child(mAuth.getCurrentUser().toString());
        setOrderDataToFirebase();

        AddressLine = reference.child("AddressLine").toString();
        Items = reference.child("Items").toString();
        date = reference.child("Date").toString();
        mode = reference.child("Payment Mode").toString();

        addressLine.setText(AddressLine);
        items.setText(Items);
        expectedDate.setText(date);
        paymentMode.setText(mode);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(confirmation.this, "Back to Home Page", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(confirmation.this,SelectLocationFromMap.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                finish();
            }
        });
    }

    private void setOrderDataToFirebase() {
        Intent intent = getIntent();

        AddressLine = intent.getStringExtra("AddressLine");
        Items = intent.getStringExtra("items");
        date = intent.getStringExtra("date");
        mode = intent.getStringExtra("mode");


        HashMap<Object, String> hashMap = new HashMap<>();
        hashMap.put("Items",Items);
        hashMap.put("AddressLine", AddressLine);
        hashMap.put("Payment Mode",mode);
        hashMap.put("Date",date);
        reference.setValue(hashMap);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(confirmation.this, "Back to Home Page", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(confirmation.this,SelectLocationFromMap.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        finish();
    }
}