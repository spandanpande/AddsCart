package com.as2developers.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.material.navigation.NavigationView;

public class ConfirmPickupActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TextView txt_itemPickup, txt_pickUpdate, txt_confirm, scarpItem_txt, change_scrap, chane_Adddress, change_pickup,
            txt_homeAddress, txt_Address, txt_pickupTime;
    View fragment_location;
    ImageButton back;
    Button continue_btn;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_pickup);
        //for back
        back = findViewById(R.id.Img);
        //hooks for navigation bar
        drawerLayout =findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.dummy_content,R.string.dummy_content);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        txt_itemPickup = findViewById(R.id.text_itemPickup);
        txt_pickUpdate = findViewById(R.id.txt_pickupDate);
        txt_confirm = findViewById(R.id.txt_confirm);
        scarpItem_txt = findViewById(R.id.scarpitem_text);
        txt_homeAddress = findViewById(R.id.txt_homeAddress);
        txt_Address = findViewById(R.id.txt_Address);
        txt_pickupTime = findViewById(R.id.pickUp_time);

        fragment_location = findViewById(R.id.location_fragment);

        continue_btn = findViewById(R.id.btn_proceed);

        //to set date also selected items
        Intent gIntent = getIntent();
        String temp = gIntent.getStringExtra("date");
        txt_pickupTime.setText(temp +"\n10AM-6PM");
        scarpItem_txt.setText(getIntent().getStringExtra("items"));

        txt_Address.setText(getIntent().getStringExtra("AddressLine"));

        continue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ConfirmPickupActivity.this,DonateOrGetActivity.class);
                i.putExtra("AddressLine",getIntent().getStringExtra("AddressLine"));
                i.putExtra("items",getIntent().getStringExtra("items"));
                i.putExtra("date",getIntent().getStringExtra("date"));
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile:
                startActivity(new Intent(this,ProfilePage.class));
                break;
            case R.id.pickup:
                Toast.makeText(this, "Opening to a new pickup..", Toast.LENGTH_SHORT).show();
                break;
            case R.id.howItWorks:
                startActivity(new Intent(this,HowItWorks.class));;
                break;
            case R.id.aboutUs:
                startActivity(new Intent(this,AboutUs.class));
                break;
            case R.id.call_us:
                Toast.makeText(this, "This feature will coming soon!", Toast.LENGTH_SHORT).show();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    //also for navigation bar
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }
}