package com.as2developers.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.as2developers.myapplication.Modals.OrderModal;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class confirmation extends AppCompatActivity {

    TextView paymentMode,addressLine,items,expectedDate;
    Button button;
    FirebaseDatabase database;
    DatabaseReference reference,refId;
    FirebaseAuth mAuth;
    TextView reqId;
    String AddressLine,Items,date,mode,Lon,Lat,locality,longAddress,uniqueID,reqID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        paymentMode = findViewById(R.id.paymentMode);
        button = findViewById(R.id.button2);
        addressLine = findViewById(R.id.addressLine);
        items = findViewById(R.id.list);
        expectedDate = findViewById(R.id.expected_pickup);
        reqId = findViewById(R.id.Request_id);

        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        uniqueID = UUID.randomUUID().toString();

        //refId = database.getReference("orderId");
        reference = database.getReference("Orders").child(uniqueID);

        DatabaseReference deltoday = database.getReference("TodayPickup");
        deltoday.setValue(null);
        DatabaseReference delfuture = database.getReference("FuturePickup");
        delfuture.setValue(null);

//        refId.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                reqID = snapshot.getValue(String.class);
//                int reqestID = 0;
//                if (reqID != null) {
//                    reqestID = Integer.parseInt(reqID);
//                }
//                reqestID++;
//                reqID = Integer.toString(reqestID);
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        //reference = database.getReference("Orders").child(reqID);
//        setOrderDataToFirebase();

        showOrderDataFromFirebase();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(confirmation.this, "Back to Home Page", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(confirmation.this,SelectLocationFromMap.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                finish();
            }
        });
    }

    private void showOrderDataFromFirebase() {

        Intent intent = getIntent();

        AddressLine = intent.getStringExtra("AddressLine");
        Items = intent.getStringExtra("items");
        date = intent.getStringExtra("date");
        mode = intent.getStringExtra("mode");
        Lon = intent.getStringExtra("Longitude");
        Lat = intent.getStringExtra("Latitude");
        locality = intent.getStringExtra("locality");
        longAddress = intent.getStringExtra("longAddress");


        DatabaseReference getorderid = database.getReference("OrderId").child("Items");
        getorderid.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> ItemType = new ArrayList<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String data = dataSnapshot.getValue(String.class);
                    ItemType.add(data);
                }
                reqId.setText(String.valueOf(ItemType.size()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        addressLine.setText(String.format("%s\n%s\n%s", AddressLine, locality, longAddress));
        paymentMode.setText(mode);
        expectedDate.setText(date);
        items.setText(Items);


//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                OrderModal orderModal = snapshot.getValue(OrderModal.class);
//                AddressLine = orderModal.getAddress();
//                Items = orderModal.getItems();
//                date = orderModal.getDate();
//                mode = orderModal.getPaymentMode();
//                locality = orderModal.getLocality();
//                longAddress = orderModal.getLongAddress();
//
//                addressLine.setText(String.format("%s\n%s\n%s", AddressLine, locality, longAddress));
//                paymentMode.setText(mode);
//                expectedDate.setText(date);
//                items.setText(Items);
//                reqId.setText(uniqueID);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }

//    private void setOrderDataToFirebase() {
//        Intent intent = getIntent();
//
//        AddressLine = intent.getStringExtra("AddressLine");
//        Items = intent.getStringExtra("items");
//        date = intent.getStringExtra("date");
//        mode = intent.getStringExtra("mode");
//        Lon = intent.getStringExtra("Longitude");
//        Lat = intent.getStringExtra("Latitude");
//        locality = intent.getStringExtra("locality");
//        longAddress = intent.getStringExtra("longAddress");
//
//        OrderModal order = new OrderModal(Items,AddressLine,date,mode,Lat,Lon,locality,longAddress);
//        reference.setValue(order);
//
//    }

    @Override
    public void onBackPressed() {
        Toast.makeText(confirmation.this, "Back to Home Page..", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(confirmation.this,SelectLocationFromMap.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        finish();
    }
}
















//package com.as2developers.myapplication;
//
//import android.content.Intent;
//import android.media.MediaPlayer;
//import android.os.Bundle;
//import android.os.Handler;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.airbnb.lottie.LottieAnimationView;
//import com.android.volley.Request;
//import com.as2developers.myapplication.Modals.OrderModal;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.ChildEventListener;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.HashMap;
//import java.util.UUID;
//
//public class confirmation extends AppCompatActivity {
//
//    TextView paymentMode,addressLine,items,expectedDate;
//    Button button;
//    FirebaseDatabase database;
//    DatabaseReference reference,refId;
//    FirebaseAuth mAuth;
//    TextView reqId;
//    String AddressLine,Items,date,mode,Lon,Lat,locality,longAddress,uniqueID,reqID;
//   // LottieAnimationView lottieAnimationView;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_confirmation);
//
//        paymentMode = findViewById(R.id.paymentMode);
//        button = findViewById(R.id.button2);
//        addressLine = findViewById(R.id.addressLine);
//        items = findViewById(R.id.list);
//        expectedDate = findViewById(R.id.expected_pickup);
//        reqId = findViewById(R.id.Request_id);
//       // lottieAnimationView = (LottieAnimationView) findViewById(R.id.confirmationAnim);
//        database = FirebaseDatabase.getInstance();
//        mAuth = FirebaseAuth.getInstance();
//        FirebaseUser user = mAuth.getCurrentUser();
//        uniqueID = UUID.randomUUID().toString();
//
//        //refId = database.getReference("orderId");
//        reference = database.getReference("Orders").child(uniqueID);
//
//
////        refId.addListenerForSingleValueEvent(new ValueEventListener() {
////            @Override
////            public void onDataChange(@NonNull DataSnapshot snapshot) {
////                reqID = snapshot.getValue(String.class);
////                int reqestID = 0;
////                if (reqID != null) {
////                    reqestID = Integer.parseInt(reqID);
////                }
////                reqestID++;
////                reqID = Integer.toString(reqestID);
////            }
////            @Override
////            public void onCancelled(@NonNull DatabaseError error) {
////
////            }
////        });
//
//        //reference = database.getReference("Orders").child(reqID);
////        setOrderDataToFirebase();
//
//        showOrderDataFromFirebase();
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(confirmation.this, "Back to Home Page", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(confirmation.this,SelectLocationFromMap.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
//                finish();
//            }
//        });
//
//    }
//
//    private void showOrderDataFromFirebase() {
//
//        Intent intent = getIntent();
//
//        AddressLine = intent.getStringExtra("AddressLine");
//        Items = intent.getStringExtra("items");
//        date = intent.getStringExtra("date");
//        mode = intent.getStringExtra("mode");
//        Lon = intent.getStringExtra("Longitude");
//        Lat = intent.getStringExtra("Latitude");
//        locality = intent.getStringExtra("locality");
//        longAddress = intent.getStringExtra("longAddress");
//
//
//        addressLine.setText(String.format("%s\n%s\n%s", AddressLine, locality!=null ? locality: "",longAddress!=null ? longAddress : ""));
//        paymentMode.setText(mode);
//        expectedDate.setText(date);
//        items.setText(Items);
//        reqId.setText(uniqueID);
//
////        reference.addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(@NonNull DataSnapshot snapshot) {
////                OrderModal orderModal = snapshot.getValue(OrderModal.class);
////                AddressLine = orderModal.getAddress();
////                Items = orderModal.getItems();
////                date = orderModal.getDate();
////                mode = orderModal.getPaymentMode();
////                locality = orderModal.getLocality();
////                longAddress = orderModal.getLongAddress();
////
////                addressLine.setText(String.format("%s\n%s\n%s", AddressLine, locality, longAddress));
////                paymentMode.setText(mode);
////                expectedDate.setText(date);
////                items.setText(Items);
////                reqId.setText(uniqueID);
////            }
////
////            @Override
////            public void onCancelled(@NonNull DatabaseError error) {
////
////            }
////        });
//    }
//
////    private void setOrderDataToFirebase() {
////        Intent intent = getIntent();
////
////        AddressLine = intent.getStringExtra("AddressLine");
////        Items = intent.getStringExtra("items");
////        date = intent.getStringExtra("date");
////        mode = intent.getStringExtra("mode");
////        Lon = intent.getStringExtra("Longitude");
////        Lat = intent.getStringExtra("Latitude");
////        locality = intent.getStringExtra("locality");
////        longAddress = intent.getStringExtra("longAddress");
////
////        OrderModal order = new OrderModal(Items,AddressLine,date,mode,Lat,Lon,locality,longAddress);
////        reference.setValue(order);
////
////    }
//
//    @Override
//    public void onBackPressed() {
//        Toast.makeText(confirmation.this, "Back to Home Page", Toast.LENGTH_SHORT).show();
//        startActivity(new Intent(confirmation.this,SelectLocationFromMap.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
//        finish();
//    }
//}
