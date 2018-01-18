package com.example.mahbub.chatapp;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mahbub.chatapp.adapter.ViewPagerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    // Firebase Authentication
    private FirebaseAuth mAuth;

    // UI references.
    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Firebase Authentication
        mAuth = FirebaseAuth.getInstance();
        // UI references.
        mToolbar = findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Chat App");

        // Viewpager and Fragments
        mTabLayout = findViewById(R.id.main_tabs);
        mViewPager = findViewById(R.id.main_tabPager);

        setDataToViewPager();
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setDataToViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment (new ChatsFragment(),"Chats");
        adapter.addFragment(new FriendsFragment(),"Friends");
        adapter.addFragment(new RequestsFragment(),"Requests");
        mViewPager.setAdapter(adapter);
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser == null){
            Intent startIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(startIntent);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.main_logout_button)
        {
            mAuth.getInstance().signOut();
            Log.d(TAG, "onOptionsItemSelected: Sign out");
            updateUI(null);
        } else if(item.getItemId() == R.id.main_account_settings){
            Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(settingsIntent);
        }
        return true;
    }
    
    
}
