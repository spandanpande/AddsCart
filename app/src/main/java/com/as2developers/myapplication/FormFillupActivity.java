package com.as2developers.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.SleepClassifyEvent;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.navigation.NavigationView;

public class FormFillupActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    LinearLayout linearLayout_location, linearLayoutAddress, nameLayout, fistLayout, secondLayout, thirdLayout,
            fourthLayout, fifthLayout, sixthLayout, btn_Layout;
    TextView pickup_laction, homelocation, homeAddress_text, txt_goodmorning, txt_name,
            paper, paper_price, metal, metal_price, plastic, plastic_price, eWaste, eWsate_price, iron, iron_price, otherItems, otherItems_price;
    Button btn_continue;
    MaterialCardView card1, card2, card3, card4, card5, card6;
    ImageButton ImgBtn;
    ImageView paperImage, plastic_image, metal_image, eWaste_image, iron_image, otherItem_image, addNewAddress;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_fillup);
        // All LinearLayout
        linearLayout_location = findViewById(R.id.linearleyout_loacation);
        linearLayoutAddress = findViewById(R.id.linerleyoutAddress);
        nameLayout = findViewById(R.id.nameLout);
        fistLayout = findViewById(R.id.first_layout);
        secondLayout = findViewById(R.id.second_layout);
        thirdLayout = findViewById(R.id.thirdLinear_layout);
        fourthLayout = findViewById(R.id.fourth_linear_layout);
        fifthLayout = findViewById(R.id.fifth_layout);
        sixthLayout = findViewById(R.id.sixth_layout);
        ImgBtn = findViewById(R.id.btn_side_nav);
//        btn_Layout = findViewById(R.id.btn_Layout);


        // All ImageView
        paperImage = findViewById(R.id.paper_image);
        plastic_image = findViewById(R.id.plastic_image);
        metal_image = findViewById(R.id.metal_image);
        eWaste_image = findViewById(R.id.eWaste_image);
        iron_image = findViewById(R.id.iron_image);
        otherItem_image = findViewById(R.id.otheritems_image);


        // All TextView
        pickup_laction = findViewById(R.id.pickuplocation);
        homelocation = findViewById(R.id.home_location);
//        addNewAddress = findViewById(R.id.addNewAddress);
        homeAddress_text = findViewById(R.id.homeAddress_text);
//        txt_goodmorning = findViewById(R.id.txt_goodmorinig);
//        txt_name = findViewById(R.id.textname);
        paper = findViewById(R.id.paper);
        paper_price = findViewById(R.id.paper_price);
        plastic = findViewById(R.id.plastic);
        plastic_price = findViewById(R.id.plasticPrice);
        metal = findViewById(R.id.metal);
        metal_price = findViewById(R.id.metal_price);
        eWaste = findViewById(R.id.ewaste);
        eWsate_price = findViewById(R.id.ewasteprice);
        iron = findViewById(R.id.iron);
        iron_price = findViewById(R.id.ironprice);
        otherItems = findViewById(R.id.otherItems);
        otherItems_price = findViewById(R.id.otheritems_price);
        btn_continue = findViewById(R.id.btn_continue);


        // All Material CardView
        card1 = findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);
        card3 = findViewById(R.id.card3);
        card4 = findViewById(R.id.card4);
        card5 = findViewById(R.id.card5);
        card6 = findViewById(R.id.card6);

        //hooks for navigation bar
        drawerLayout =findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.dummy_content,R.string.dummy_content);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FormFillupActivity.this, SetDate.class));
            }
        });

        ImgBtn = findViewById(R.id.btn_side_nav);
        ImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
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
//                Intent callIntent = new Intent(Intent.ACTION_CALL);
//                String s = "+918867825522";
//                callIntent.setData(Uri.parse("tel:"+s));//change the number.
//                startActivity(callIntent);
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