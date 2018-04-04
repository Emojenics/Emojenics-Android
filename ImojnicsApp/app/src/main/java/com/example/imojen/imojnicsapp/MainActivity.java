package com.example.imojen.imojnicsapp;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.MenuItem;
import  android.content.Intent;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMenuItemClickListener  {
    private Toolbar mTopToolbar;
    Integer selection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        ImageButton imageButton = (ImageButton) toolbar.findViewById(R.id.action_plus);
//        final MainActivity act=this;
//        imageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent i = new Intent(act,CaptureActivity.class);
//                startActivity(i);
//            }
//        });

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.skyBlue));
        drawer.addDrawerListener(toggle);
        toggle.syncState();



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
//        invalidateOptionsMenu(); //Call this for set hide or visible navigationheader menu
        if (getIntent().hasExtra("slection")) {
            String firstvalue = getIntent().getStringExtra("slection");
            int row = Integer.parseInt(firstvalue);
            selection=row;
            switch(row)
            {

                case 1:
                    this.displaySelectedScreen(R.id.saved_emoji);
                    return;
                case 2:
                    this.displaySelectedScreen(R.id.account);
                    return;
                case 3:
                    this.displaySelectedScreen(R.id.enable_keyboard);
                    return;
                case 4:
                    this.displaySelectedScreen(R.id.settings);
                    return;
                case 5:
                    this.displaySelectedScreen(R.id.build_emoji);
                    return;
                case 6:
                    this.displaySelectedScreen(R.id.terms_use);
                    return;
                case 7:
                    this.displaySelectedScreen(R.id.about);
                    return;
            }

        }


        this.displaySelectedScreen(R.id.settings);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//If you want three dot button just uncomment the below section

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.main, menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings, menu);
        menu.removeItem(R.id.action_plus);
        return true;
    }
//    @Override
//    public boolean onMenuItemClick(MenuItem item)
//    {
//        if (super.onOptionsItemSelected(item))
//            return true;
//        else
//            return false;
//    }

//    public boolean onNavigationItemSelected( MenuItem item)
//    {
//        return super.onOptionsItemSelected(item);
//    }

//    @Override
//    public boolean onOptionsMenuItemtemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_plus) {
//
//            return  true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        this.displaySelectedScreen(id);


//        if (id == R.id.saved_emoji) {
//            // Handle the camera action
//            Intent myIntent = new Intent(MainActivity.this, GalleryActivity.class);
//           this.startActivity(myIntent);
//        } else if (id == R.id.account) {
////            Account Action
//
//        } else if (id == R.id.enable_keyboard) {
////            Enable Keyboard Action
//
//        } else if (id == R.id.settings) {
//
//        } else if (id == R.id.build_emoji) {
//
//        } else if (id == R.id.terms_use) {
//
//        }
//        else if (id == R.id.about) {
//
//        }
//        else if (id == R.id.logout) {
//
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void displaySelectedScreen(int itemid)
    {
        android.support.v4.app.Fragment fragment = null;
        switch (itemid)
        {
            case R.id.saved_emoji:
                invalidateOptionsMenu();
                fragment= new EmojenicsGalleryFragment();

                break;

            case R.id.account:
                invalidateOptionsMenu();
                fragment=new EditAccountFragment();
                break;
            case R.id.enable_keyboard:
                invalidateOptionsMenu();
                fragment= new KeyBoardFragment();
                break;
            case R.id.settings:
                invalidateOptionsMenu();
                fragment= new SettingsFragment();
                break;
            case R.id.build_emoji: {
                invalidateOptionsMenu();
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(this, CaptureActivity.class);
                startActivity(intent);
            }
                return;


            case R.id.terms_use:
                invalidateOptionsMenu();
                fragment=new TermsConditionFragment();
                break;
            case R.id.about:
                invalidateOptionsMenu();
                fragment=new AboutUsFragment();
                break;
            case R.id.logout: {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putBoolean("login", false);           // Saving boolean - true/false
// Save the changes in SharedPreferences
                editor.commit(); // commit changes
                editor.apply();
                Intent intent = new Intent(this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }

                break;
        }
        if(fragment != null)
        {
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.screenarea,fragment);
            ft.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_plus) {
            Intent i = new Intent(this,CaptureActivity.class);
            startActivity(i);
            return  true;
        }

        return false;
    }
}
