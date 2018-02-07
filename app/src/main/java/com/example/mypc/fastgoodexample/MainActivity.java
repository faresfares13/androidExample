package com.example.mypc.fastgoodexample;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mypc.fastgoodexample.BackgroundWorkAndAdapters.ViewPagerAdapter;

import static android.support.v7.appcompat.R.styleable.Toolbar;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    ActionBarDrawerToggle drawerToggle;
    private ViewPager viewPager;
    private DrawerLayout drawer;
    public NavigationView navigationView;

    private String[] pageTitle = {"Home", "Vegetarian", "Vegan", "Quick & Easy", "Slow cooker"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager)findViewById(R.id.view_pager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawerLayout);


        setSupportActionBar(toolbar);


        //create default navigation drawer toggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //setting Tab layout (number of Tabs = number of ViewPager pages)
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        for (int i = 0; i < 5; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(pageTitle[i]));
        }

        //set gravity for tab bar
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //handling navigation view item event
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

        //set viewpager adapter
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        //change Tab selection when swipe ViewPager
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //change ViewPager page when tab selected
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    //Used to chane the color of action bar icons
    @NonNull
    private static Drawable setTintDrawable(@NonNull Drawable drawable, @ColorInt int color) {
        drawable.clearColorFilter();
        drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
        drawable.invalidateSelf();
        Drawable wrapDrawable = DrawableCompat.wrap(drawable).mutate();
        DrawableCompat.setTint(wrapDrawable, color);
        return wrapDrawable;
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home:
                viewPager.setCurrentItem(0);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            case R.id.shopping_list:
                showShoppingList();
                drawer.closeDrawer(GravityCompat.START);
                return true;
            case R.id.search:
                search();
                drawer.closeDrawer(GravityCompat.START);
                return true;
            case R.id.invite_friends:
                inviteFriends();
                drawer.closeDrawer(GravityCompat.START);
                return true;
            case R.id.about:
                about();
                drawer.closeDrawer(GravityCompat.START);
                return true;
            default:
                drawer.closeDrawer(GravityCompat.START);
                return true;
        }
    }

    private void search() {
        Intent i = new Intent();
        i.setClass(this,SearchActivity.class);
        startActivity(i);
    }

    private void showShoppingList() {
        Intent i = new Intent();
        i.setClass(this,ShoppingListActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_bar_items, menu);
        MenuItem[] location = {menu.findItem(R.id.shopping_list),
                menu.findItem(R.id.search),
                menu.findItem(R.id.invite_friends)};
        for(int i = 0; i < 3; i++) {
            setTintDrawable(location[i].getIcon(),Color.DKGRAY);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.invite_friends:

                inviteFriends();
                return true;
            case R.id.search:

                search();
                return true;
            case R.id.shopping_list:

                showShoppingList();
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }
    public void inviteFriends(){
        try{
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT,"FastGood");
            String txt = "Hello! I Recommend this App!!";
            i.putExtra(Intent.EXTRA_TEXT,txt);
            startActivity(Intent.createChooser(i,"Send invetation via.."));

        }
        catch(Exception e)
        {
            throw e;
        }

    }

    public void about(){
        Intent i = new Intent();
        i.setClass(this,About.class);
        startActivity(i);
    }
    @Override
    public void onBackPressed() {
        assert drawer != null;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}