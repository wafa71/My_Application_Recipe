package com.example.myapplicationrecipe;

import android.os.Bundle;
import android.view.MenuItem;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity {
BottomNavigationView bottomNavigationView ;
MainActivity mainActivity = new MainActivity();
FirstFragment firstFragment = new FirstFragment();
//    private AppBarConfiguration appBarConfiguration;
//    private ActivityMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Home home = new Home() ;
//        FirstFragment firstFragment = new FirstFragment();
        SecondFragment secondFragment = new SecondFragment();
        setContentView(R.layout.activity_menu);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, home  ).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, home).commit();
                        return true;
//                    case R.id.favmenu:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.container,firstFragment ).commit();
//                        return true;

                    case R.id.listper:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, secondFragment).commit();
                        return true;
                }
                return false;


    }});
    }


}