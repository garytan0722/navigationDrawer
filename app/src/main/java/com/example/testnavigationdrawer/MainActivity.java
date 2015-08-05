package com.example.testnavigationdrawer;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerListView;
    private DrawerAdapter mDrawerAdapter;
    private ActionBarDrawerToggle mDrawerToggle;

    private final List<DrawerEntryItem> mDrawerList = new ArrayList<DrawerEntryItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void initView() {
        // Toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        // DrawerLayout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = createDrawerToggle();
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        // ListView
        mDrawerListView = (ListView) findViewById(R.id.left_drawer);
        mDrawerAdapter = new DrawerAdapter(this, R.layout.drawer_item_layout, mDrawerList);
        mDrawerListView.setAdapter(mDrawerAdapter);
        mDrawerListView.setOnItemClickListener(new DrawerItemClickListener());

        // Drawer Data
        setDrawerData();
    }



    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
        getDrawerToggleDelegate().setActionBarUpIndicator(
                getResources().getDrawable(R.drawable.light_menu_r, getTheme()), R.string.app_name);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
        getDrawerToggleDelegate().setActionBarUpIndicator(
                getResources().getDrawable(R.drawable.light_menu_r, getTheme()), R.string.app_name);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private ActionBarDrawerToggle createDrawerToggle() {
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                mToolbar, R.string.open, R.string.close) {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getDrawerToggleDelegate().setActionBarUpIndicator(
                        getResources().getDrawable(R.drawable.dark_menu_r, getTheme()), R.string.open);
            }

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getDrawerToggleDelegate().setActionBarUpIndicator(
                        getResources().getDrawable(R.drawable.light_menu_r, getTheme()), R.string.close);
            }
        };
        return drawerToggle;
    }

    private void setDrawerData() {
        Resources res = getResources();

        DrawerEntryItem entry1 = new DrawerEntryItem("第一項目");
        DrawerEntryItem entry2 = new DrawerEntryItem("第二項目");

        mDrawerList.add(entry1);
        mDrawerList.add(entry2);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    // First drawer item clicked, do something
                    //fragment = new FirstFragment();
                    break;
                case 1:
                    // Second drawer item clicked, do something
                    //fragment = new SecondFragment();
                    break;

            }
            if (fragment != null) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_frame, fragment).commit();
            }
            mDrawerLayout.closeDrawer(mDrawerListView);
        }
    }
}
