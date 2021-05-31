package com.example.thelasttime;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thelasttime.HelperClass.HomeAdapter.CatgoriersCard;
import com.example.thelasttime.HelperClass.HomeAdapter.FeatureAdpater;
import com.example.thelasttime.HelperClass.HomeAdapter.FeaturedHelpersClass;
import com.example.thelasttime.HelperClass.HomeAdapter.ViewedCard;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class mmeennuu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextInputLayout fullname,email,phoneN0,pass;
    TextView username;
    RecyclerView viewedCard;
    ViewedCard adapter1;
    RecyclerView featuredRecycler;
    FeatureAdpater adapter;
    RecyclerView catgoriersCard;
    CatgoriersCard adapter2;
    //Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_mmeennuu);
        featuredRecycler = findViewById(R.id.featred_recycler);
        featuredRecycler();
        viewedCard = findViewById(R.id.featred_recycler1);
        viewedCard();
        catgoriersCard = findViewById(R.id.featred_recycler2);
        catgoriersCard();

        //Menu
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.menu_drawer);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(mmeennuu.this,
                drawerLayout,
                R.string.open,
                R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    private void featuredRecycler() {

        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelpersClass> featuredLocations = new ArrayList<>();

        featuredLocations.add(new FeaturedHelpersClass(R.drawable.cgv, "CGV CINEMA", "CGV is one of the top 5 largest cinema clusters in the world and the largest distributor and cinema complex in Vietnam."));
        featuredLocations.add(new FeaturedHelpersClass(R.drawable.kfc, "KFC", "KFC specializes in fried and grilled chicken products, with side dishes and sandwiches made from fresh chicken."));
        featuredLocations.add(new FeaturedHelpersClass(R.drawable.mc, "McDonald's", "McDonald's is known as the world famous fast food restaurant system with more than 35,000 stores."));
        featuredLocations.add(new FeaturedHelpersClass(R.drawable.gym, "California", "In 2007 California Fitness & Yoga became the first and largest international fitness company to launch in Vietnam."));

        adapter = new FeatureAdpater(featuredLocations);
        featuredRecycler.setAdapter(adapter);

        GradientDrawable gradient = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600});

    }


    private void viewedCard() {
        viewedCard.setHasFixedSize(true);
        viewedCard.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<FeaturedHelpersClass> featuredLocations = new ArrayList<>();

        featuredLocations.add(new FeaturedHelpersClass(R.drawable.cgv, "CGV CINEMA", "CGV is one of the top 5 largest cinema clusters in the world and the largest distributor and cinema complex in Vietnam."));
        featuredLocations.add(new FeaturedHelpersClass(R.drawable.kfc, "KFC", "KFC specializes in fried and grilled chicken products, with side dishes and sandwiches made from fresh chicken."));
        featuredLocations.add(new FeaturedHelpersClass(R.drawable.mc, "McDonald's", "McDonald's is known as the world famous fast food restaurant system with more than 35,000 stores."));
        featuredLocations.add(new FeaturedHelpersClass(R.drawable.gym, "California", "In 2007 California Fitness & Yoga became the first and largest international fitness company to launch in Vietnam."));

        adapter1 = new ViewedCard(featuredLocations);
        viewedCard.setAdapter(adapter1);

        GradientDrawable gradient = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600});

    }


    private void catgoriersCard() {
        catgoriersCard.setHasFixedSize(true);
        catgoriersCard.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<FeaturedHelpersClass> featuredLocations = new ArrayList<>();

        featuredLocations.add(new FeaturedHelpersClass(R.drawable.restaurant, "Restaurant", ""));
        featuredLocations.add(new FeaturedHelpersClass(R.drawable.hotel, "Hotels", ""));
        featuredLocations.add(new FeaturedHelpersClass(R.drawable.shopping, "Shopping's", ""));
        featuredLocations.add(new FeaturedHelpersClass(R.drawable.cinema, "Cinema", ""));

        adapter2 =new CatgoriersCard(featuredLocations);
        catgoriersCard.setAdapter(adapter2);

        GradientDrawable gradient = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600});
    }


    //onclick
    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.ProfileMenu:
                showUser();
                break;
            case R.id.SignoutMenu:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(mmeennuu.this,Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    private void showUser() {
        Intent intent = getIntent();
        String fullnameDB = intent.getStringExtra("fullname");
        String emailDB = intent.getStringExtra("email");
        String phoneDB = intent.getStringExtra("phone");
        String passDB = intent.getStringExtra("pass");
        String userDB = intent.getStringExtra("username");

       //again

        Intent intentt = new Intent(getApplication(), Profile.class);
        intentt.putExtra("fullname",fullnameDB);
        intentt.putExtra("email",emailDB);
        intentt.putExtra("phone",phoneDB);
        intentt.putExtra("pass",passDB);
        intentt.putExtra("username",userDB);
        startActivity(intentt);
    }
}

