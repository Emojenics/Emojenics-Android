package com.example.imojen.imojnicsapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import  android.support.design.widget.NavigationView;
import  android.support.v4.view.GravityCompat;
import  android.support.v4.widget.DrawerLayout;
import  android.support.v7.app.ActionBarDrawerToggle;
import  android.view.Menu;
import  android.view.MenuItem;
import android.view.ViewGroup;
import  android.util.DisplayMetrics;
import android.content.Context;
import android.widget.GridView;
import android.view.ViewGroup;

public class GalleryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout  drawer=(DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toogle= new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        toogle.getDrawerArrowDrawable();
        drawer.addDrawerListener(toogle);
        toogle.syncState();

        NavigationView navView=(NavigationView)findViewById(R.id.nav_view);;
        navView.setNavigationItemSelectedListener(this);

        Context context= this;
        DisplayMetrics displayMetrics=context.getResources().getDisplayMetrics();
        float dpHeight=displayMetrics.heightPixels/displayMetrics.density;
        float dpWidth=displayMetrics.widthPixels/displayMetrics.density;

        GridView gridView=(GridView)findViewById(R.id.gridview);
        ViewGroup.LayoutParams params=gridView.getLayoutParams();
        float density=context.getResources().getDisplayMetrics().density;
        params.width =dpToPx(Math.round(dpWidth/3),density);
        params.height=dpToPx(Math.round(params.height*(381/272)),density);
        gridView.setLayoutParams(params);

        gridView.setAdapter(new ImageGridAdapter(this));



    }
    public int dpToPx(int dp, float density) {
        return Math.round(((float) dp) * density);
    }
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_camera)
        {

        }
        if(id == R.id.nav_gallery)
        {

        }
        if(id == R.id.nav_slideshow)
        {

        }
        if(id == R.id.nav_manage)
        {

        }
        if(id == R.id.nav_share)
        {

        }
        if(id == R.id.nav_send)
        {

        }
        DrawerLayout drawer=(DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
