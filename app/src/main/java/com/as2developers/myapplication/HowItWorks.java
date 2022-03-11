package com.as2developers.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class HowItWorks extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //for slide navigation bar
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageView back;
    private static final int REQUEST_CALL =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_it_works);
        back = findViewById(R.id.back);
        //hooks for navigation bar
        drawerLayout =findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.dummy_content,R.string.dummy_content);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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
                Intent i = new Intent(getApplicationContext(),ProfilePage.class);
                startActivity(i);
                break;
            case R.id.howItWorks:
                Toast.makeText(this, "You are already in!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.aboutUs:
                //  Toast.makeText(this, "you are in about us!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),AboutUs.class);
                startActivity(intent);
                break;
            case R.id.call_us:
                makePhoneCall();
                //Toast.makeText(this, "This feature will coming soon!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.home:
                startActivity(new Intent(this, SelectLocationFromMap.class));
                finish();
                break;
            case R.id.logOut:
                Toast.makeText(this, "Logging out..", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,Login_Phone.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                finish();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    private void makePhoneCall(){

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        }
        else{
            String phoneNo = "tel:"+"8867825522";
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse(phoneNo));
            startActivity(intent);
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==REQUEST_CALL){
            if(grantResults.length > 0  && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                //when permission granted call method
                makePhoneCall();
            }else{
                Toast.makeText(this, "Call Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

}