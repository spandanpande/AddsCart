package com.as2developers.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
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
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.as2developers.myapplication.Modals.UserModal;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.Objects;

public class ProfilePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //for slide navigation bar
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageButton imageButton;
    TextView Name, MobileNo,Email,address;

    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth mAuth;
    FirebaseUser user;
    private static final int REQUEST_CALL =1;

    String userName , userEmail , userAddress , userMobile;

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
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        if (user!=null){
            reference = database.getReference("Users").child(Objects.requireNonNull(user.getPhoneNumber()));
        }


        SetUserDataFromFirebase();

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void SetUserDataFromFirebase() {


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserModal user01 = snapshot.getValue(UserModal.class);
                userName= user01.getName();
                userEmail = user01.getEmail();
                userAddress = user01.getAddress();
                userMobile = user01.getMobileNo();


                Name.setText(userName);
                Email.setText(userEmail);
                address.setText(userAddress);
                MobileNo.setText(userMobile);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
                Toast.makeText(this, "You are already in profile!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.howItWorks:
                startActivity(new Intent(this,HowItWorks.class));
                break;
            case R.id.aboutUs:
                Intent i = new Intent(getApplicationContext(),AboutUs.class);
                startActivity(i);
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