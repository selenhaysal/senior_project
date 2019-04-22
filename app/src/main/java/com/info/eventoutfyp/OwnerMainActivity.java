package com.info.eventoutfyp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import android.widget.Toast;




public class OwnerMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {



    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    private DrawerLayout drawer;
    private DatabaseReference databaseReference;
    private int temp;
    private String email,name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);

        //get firebase auth instance
        auth = FirebaseAuth.getInstance();
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(OwnerMainActivity.this, LoginActivity.class));
                    finish();
                }

                name = user.getUid();
                email = user.getEmail();
                ((TextView) findViewById(R.id.nameLabel)).setText(name);
                ((TextView) findViewById(R.id.emailLabel)).setText(email);


            }
        };


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);





        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new EventsFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_events);
        }

//
//        databaseReference = FirebaseDatabase.getInstance().getReference(user.getUid());
//        //email.setText(user.getEmail());
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                User user = dataSnapshot.getValue(User.class);
//                name.setText(user.getId());
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Toast.makeText(OwnerMainActivity.this, databaseError.getCode(),Toast.LENGTH_SHORT).show();
//            }
//        });


    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        if (id == R.id.nav_events) {
            // Handle the camera action
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new EventsFragment()).commit();
        } else if (id == R.id.nav_myEventList) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new EventListFragment()).commit();

        } else if (id == R.id.nav_profile) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();

        } else if (id == R.id.nav_settings) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingsFragment()).commit();

        } else if (id == R.id.nav_exit) {
            signOut();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




    //sign out method
    public void signOut() {
        auth.signOut();
    }




    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }
}