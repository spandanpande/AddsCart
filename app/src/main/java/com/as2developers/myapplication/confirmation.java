package com.as2developers.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class confirmation extends AppCompatActivity {

    TextView payment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        payment = findViewById(R.id.paymentMode);

        if(getIntent().hasExtra("mode")) {
            String str = getIntent().getStringExtra("mode");
            payment.setText(str);
        }
    }
}