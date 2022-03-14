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
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.SleepClassifyEvent;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;

public class FormFillupActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    LinearLayout linearLayout_location, linearLayoutAddress, nameLayout, fistLayout, secondLayout, thirdLayout,
            fourthLayout, fifthLayout, sixthLayout, btn_Layout;
    TextView pickup_laction, homelocation, homeAddress_text, txt_goodmorning, txt_name,
            paper, paper_price, metal, metal_price, plastic, plastic_price, eWaste, eWsate_price, iron, iron_price, otherItems, otherItems_price;
    Button btn_continue,edt_address;
    MaterialCardView card1, card2, card3, card4, card5, card6;
    ImageButton ImgBtn,dropdown;
    ImageView paperImage, plastic_image, metal_image, eWaste_image, iron_image, otherItem_image, addNewAddress;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    BottomSheetDialog sheetDialog;




    String[] items = {"Paper","Plastic","Metal","E-waste","Iron","Others"};
    HashMap<String,Boolean> map_item;
    String locationType,AddressLine,Lat,Lon,longAddress,locality;
    private static final int REQUEST_CALL =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_fillup);
        // All LinearLayout
        linearLayout_location = findViewById(R.id.linearleyout_loacation);
        nameLayout = findViewById(R.id.nameLout);
        fistLayout = (LinearLayout) findViewById(R.id.first_layout);
        secondLayout = findViewById(R.id.second_layout);
        thirdLayout = findViewById(R.id.thirdLinear_layout);
        fourthLayout = findViewById(R.id.fourth_linear_layout);
        fifthLayout = findViewById(R.id.fifth_layout);
        sixthLayout = findViewById(R.id.sixth_layout);
        ImgBtn = findViewById(R.id.btn_side_nav);


        // editAddress and drop down
        edt_address=findViewById(R.id.btn_edit_address);
        dropdown=findViewById(R.id.dropDrown);
        dropdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sheetDialog = new BottomSheetDialog(FormFillupActivity.this,R.style.BottomSheetStyle1);

                view=getLayoutInflater().inflate(R.layout.activity_add_new_address,(LinearLayout)findViewById(R.id.sheet1));
                LinearLayout LL_homeAddress=sheetDialog.findViewById(R.id.homeAddress);
                Button addNewAddress=sheetDialog.findViewById(R.id.Add_New_Address);
                TextView textAddress_type=sheetDialog.findViewById(R.id.txt_Addresstype);
                TextView textAddress=sheetDialog.findViewById(R.id.txt_Address);

                sheetDialog.setContentView(view);
                sheetDialog.show();

            }
        });
        edt_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sheetDialog = new BottomSheetDialog(FormFillupActivity.this,R.style.BottomSheetStyle1);
                view=getLayoutInflater().inflate(R.layout.activity_add_new_address,(LinearLayout)findViewById(R.id.sheet1));
                LinearLayout LL_homeAddress=sheetDialog.findViewById(R.id.homeAddress);
                Button addNewAddress=sheetDialog.findViewById(R.id.Add_New_Address);
                TextView textAddress_type=sheetDialog.findViewById(R.id.txt_Addresstype);
                TextView textAddress=sheetDialog.findViewById(R.id.txt_Address);
                sheetDialog.setContentView(view);
                sheetDialog.show();

            }
        });

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
        homeAddress_text = findViewById(R.id.homeAddress_text);
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

        locationType = getIntent().getStringExtra("locationType");
        AddressLine = getIntent().getStringExtra("LocationDetails");
        longAddress = getIntent().getStringExtra("longAddress");
        locality = getIntent().getStringExtra("locality");
        Lat = getIntent().getStringExtra("Latitude");
        Lon = getIntent().getStringExtra("Longitude");
        homelocation.setText(locationType);
        homeAddress_text.setText(AddressLine);




        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.dummy_content,R.string.dummy_content);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        //to calculate how many item got selected
        items = new String[]{"Paper","Plastic","Metal","E-waste","Iron","Others"};
        map_item = new HashMap<String,Boolean>();
        for (int i = 0; i < 6; i++) {
            map_item.put(items[i],false);
        }
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //adding items to values
                String s="";
                int itemcount =0;
                for (int i = 0; i < 6; i++) {
                    if(map_item.get(items[i])){
                        s+=items[i]+" ";
                        itemcount++;
                    }
                }

                if(s==""){
                    Toast.makeText(FormFillupActivity.this, "Please Selected At least one item!", Toast.LENGTH_SHORT).show();
                }
                else {
                    startActivity(new Intent(FormFillupActivity.this, SetDate.class).putExtra("items", s).putExtra("itemCount",Integer.toString(itemcount)).putExtra("AddressLine",AddressLine).putExtra("LocationType",locationType).putExtra("Latitude",Lat).putExtra("Longitude",Lon).putExtra("locality",locality).putExtra("longAddress",longAddress));
                    Toast.makeText(FormFillupActivity.this, s, Toast.LENGTH_SHORT).show();
                }
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
            case R.id.howItWorks:
                startActivity(new Intent(this,HowItWorks.class));;
                break;
            case R.id.aboutUs:
                startActivity(new Intent(this,AboutUs.class));
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


    public void PaperClick(View view) {
        if(!map_item.get("Paper")){
            map_item.put("Paper",true);
            fistLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.selected_bg));
        }
        else{
            map_item.put("Paper",false);
            fistLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.custom_background_white_10r_grey_border));
        }
    }

    public void PlasticClick(View view) {
        if(!map_item.get("Plastic")){
            map_item.put("Plastic",true);
            secondLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.selected_bg));
        }
        else{
            map_item.put("Plastic",false);
            secondLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.custom_background_white_10r_grey_border));
        }
    }
    public void MetalsClick(View view) {
        if(!map_item.get("Metal")){
            map_item.put("Metal",true);
            thirdLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.selected_bg));
        }
        else{
            map_item.put("Metal",false);
            thirdLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.custom_background_white_10r_grey_border));
        }
    }

    public void E_Waste(View view) {
        if(!map_item.get("E-waste")){
            map_item.put("E-waste",true);
            fourthLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.selected_bg));
        }
        else{
            map_item.put("E-waste",false);
            fourthLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.custom_background_white_10r_grey_border));
        }
    }

    public void IronClick(View view) {
        if(!map_item.get("Iron")){
            map_item.put("Iron",true);
            fifthLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.selected_bg));
        }
        else{
            map_item.put("Iron",false);
            fifthLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.custom_background_white_10r_grey_border));
        }
    }

    public void OthersClick(View view) {
        if(!map_item.get("Others")){
            map_item.put("Others",true);
            sixthLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.selected_bg));
        }
        else{
            map_item.put("Others",false);
            sixthLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.custom_background_white_10r_grey_border));
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