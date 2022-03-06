package com.as2developers.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.as2developers.myapplication.Modals.UserModal;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class ProfilePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //for slide navigation bar
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageButton imageButton;
    TextView Name, MobileNo,Email,address;

    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        //hooks for navigation bar
        drawerLayout =findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.dummy_content,R.string.dummy_content);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        imageButton = findViewById(R.id.img);
        Name = findViewById(R.id.Name);
        address = findViewById(R.id.PickUpAddress);
        Email = findViewById(R.id.EmailAddress);
        MobileNo = findViewById(R.id.MobileNumber);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Users");

        //SetUserDataFromFirebase();

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void SetUserDataFromFirebase() {
        String userName , userEmail , userAddress , userMobile;

        userName = reference.child("Name").toString();
        userAddress = reference.child("Address").toString();
        userEmail = reference.child("Email").toString();
        userMobile = reference.child("Mobile").toString();

        Name.setText(userName);
        Email.setText(userEmail);
        address.setText(userAddress);
        MobileNo.setText(userMobile);
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
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile:
                Toast.makeText(this, "You are already in profile!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.pickup:
                Toast.makeText(this, "Opening to a new pickup..", Toast.LENGTH_SHORT).show();
                break;
            case R.id.howItWorks:
                startActivity(new Intent(this,HowItWorks.class));
                break;
            case R.id.aboutUs:
                Intent i = new Intent(getApplicationContext(),AboutUs.class);
                startActivity(i);
                break;
            case R.id.call_us:
                Toast.makeText(this, "This feature will coming soon!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.home:
                startActivity(new Intent(this, SelectLocationFromMap.class));
                finish();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}