package com.example.english.app;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity
        implements OnMapReadyCallback,NavigationView.OnNavigationItemSelectedListener {
    int i;
  Classification c=new Classification();
    private GoogleMap mMap;
    SupportMapFragment supportMapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        supportMapFragment=(SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        supportMapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng p = new LatLng(43.000582, -78.781136);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p,13));

        mMap.addMarker(new MarkerOptions().position(p).title("Alumni Arena"));

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.setting) {


        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item){
            // Handle navigation view item clicks here.

            int id = item.getItemId();
            if (id == R.id.Center_for_the_Arts) {
                mark(1);
            } else if (id == R.id.Lockwood_Memorial_Library) {
                mark(2);

            } else if (id == R.id.Student_Union) {
                mark(3);
            } else if (id == R.id.Clemens_Hall) {
                mark(4);
            } else if (id == R.id.Charles_B_Sears_Law_Library) {
                mark(5);
            } else if (id == R.id.Alumni_Arena) {
                mark(6);
            } else if (id == R.id.Level_Bound) {

            } else if (id == R.id.Activity_Log) {

            } else if (id == R.id.Setting) {

             }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }

   public void mark(int i){
       switch(i){
           case 1:
               LatLng p1 = new LatLng(43.001205, -78.783030);
               mMap.addMarker(new MarkerOptions().position(p1).title("UB Center for the Arts"));
               mMap.moveCamera(CameraUpdateFactory.newLatLng(p1));
               MarkerLocationZoom(43.001205, -78.783030, 18);
               break;

           case 2:
                  LatLng p2 = new LatLng(43.000407, -78.785796);
            mMap.addMarker(new MarkerOptions().position(p2).title("Lockwood Memorial Library"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(p2));
            MarkerLocationZoom(43.000407, -78.785796, 18);
               break;
           case 3:
               LatLng p3 = new LatLng(43.001256, -78.786241);
            mMap.addMarker(new MarkerOptions().position(p3).title("Student Union"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(p3));
            MarkerLocationZoom(43.001256, -78.786241, 18);
               break;
           case 4:
                 LatLng p4 = new LatLng(43.000523, -78.784979);
            mMap.addMarker(new MarkerOptions().position(p4).title("Clemens Hall"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(p4));
            MarkerLocationZoom(43.000523, -78.784979, 18);
               break;
           case 5:
                LatLng p5 = new LatLng(43.000412, -78.787968);
            mMap.addMarker(new MarkerOptions().position(p5).title("Charles B. Sears Law Library"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(p5));
            MarkerLocationZoom(43.000412, -78.787968, 18);
               break;
           case 6:
               LatLng p6 = new LatLng(43.000582, -78.781136);
               mMap.addMarker(new MarkerOptions().position(p6).title("Alumni Arena"));
               mMap.moveCamera(CameraUpdateFactory.newLatLng(p6));
               MarkerLocationZoom(43.000582, -78.781136, 18);
               break;

       }
   }
    public void MarkerLocationZoom(double latitude, double longitude, float zoom) {
        // Add a marker in UB bookstore and move the camera
        LatLng UBBookStore = new LatLng(latitude, longitude);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(UBBookStore, zoom);
        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(UBBookStore));
        mMap.moveCamera(cameraUpdate);

    }

}
