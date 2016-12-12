package com.example.english.app;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;

import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_TERRAIN;


public class MainActivity extends AppCompatActivity
        implements GoogleMap.OnInfoWindowClickListener, GoogleMap.OnMarkerClickListener, OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener, NavigationView.OnNavigationItemSelectedListener{

    private static final long MIN_DISTANCE_CHANG_FOR_UPDATE = 10;
    private static final long MINI_TIME_BW_UPDATE = 1000 * 60 * 1;
    private GoogleMap mMap;
    private SupportMapFragment supportMapFragment;
    private Marker _marker;
    private LocationRequest _myLocationRequest;
    private GoogleApiClient client;
    private GoogleApiClient _myGoogleApiClient;
    private Location _currentLocation;
    private HashMap<String, Float> _distanceTo;

    public MainActivity() {
        _currentLocation = new Location("");
        _distanceTo = new HashMap<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);


        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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
    public void onMapReady(GoogleMap map) {
        mMap = map;
        mMap.setMapType(MAP_TYPE_TERRAIN);


        //bookstore to be initial the location mark
        initMarkerLocationZoom(43.0008093, -78.7911584, 18);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
        }
        mMap.setMyLocationEnabled(true);

        _myGoogleApiClient = new GoogleApiClient.Builder(this).addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        _myGoogleApiClient.connect();

        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);

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

        if (id == R.id.mapTypeNone) {
            mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
        }if (id == R.id.mapTypeNormal) {
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }if (id == R.id.mapTypeSatellite) {
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        }if (id == R.id.mapTypeHybrid) {
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item){
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        if (id == R.id.Profile) {
            startActivity(new Intent(MainActivity.this,Profile.class));

        }else if (id == R.id.Activity_Log) {
            startActivity(new Intent(MainActivity.this,activity_log.class));
        }else if (id == R.id.Setting) {
        }else if (id == R.id.Intelligence) {
        }else if (id == R.id.Fitness) {
        }else if (id == R.id.Society) {
            startActivity(new Intent(MainActivity.this, activity_description.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;


    }
    public boolean googleServicesAvailable() {
        // get the instance of the google api availability
        GoogleApiAvailability googleApiAvailable = GoogleApiAvailability.getInstance();
        // check if it available
        int isAvailable = googleApiAvailable.isGooglePlayServicesAvailable(this);
        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (googleApiAvailable.isUserResolvableError(isAvailable)) {
            Dialog dialog = googleApiAvailable.getErrorDialog(this, isAvailable, 0);
            dialog.show();
        } else {
            Toast.makeText(this, "can not connect to play services", Toast.LENGTH_LONG).show();

        }
        return false;

    }



    public void initMarkerLocationZoom(double latitude, double longitude, float zoom) {
        // Add a marker in UB bookstore and move the camera
        LatLng UB = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(UB).title("UB Bookstore").alpha(0.5f));
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(UB, zoom);
        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(UBBookStore));

        mMap.moveCamera(cameraUpdate);

    }



    public void setMarkers(Location currentLocation, String locality, double latitude, double longitude) {

        Location destination = new Location(locality);
        destination.setLatitude(latitude);
        destination.setLongitude(longitude);

        float distance = currentLocation.distanceTo(destination);  /** get the distance*******************************/

        _distanceTo.put(locality, distance);

        if (distance < 100) {
            _marker = mMap.addMarker(new MarkerOptions().title(locality)
                    .position(new LatLng(latitude, longitude))
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                    .snippet("The distance is: " + Float.toString(distance) + " meters"));
        } else if (distance < 500) {
            _marker = mMap.addMarker(new MarkerOptions().title(locality)
                    .position(new LatLng(latitude, longitude))
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                    .snippet("The distance is: " + Float.toString(distance) + " meters"));
        } else {
            _marker = mMap.addMarker(new MarkerOptions().title(locality)
                    .position(new LatLng(latitude, longitude))
                    .snippet("The distance is: " + Float.toString(distance) + " meters"));
        }
        _marker.showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(latitude, longitude)));

    }



    public void onConnected(Bundle bundle) {
        _myLocationRequest = LocationRequest.create();
        _myLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY); // get the best user location
        _myLocationRequest.setInterval(1000); // how many times i want the location.every 1sec get the location fetched

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(_myGoogleApiClient, _myLocationRequest, (com.google.android.gms.location.LocationListener) this); // this is who is listening the location

    }


    public void onConnectionSuspended(int i) {

    }


    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void setCurrentLocation(Location location){
        _currentLocation = location;
    }


    public void onLocationChanged(Location location) {
        setCurrentLocation(location);

        if (location == null) {
            Toast.makeText(this, "Cannot get current location, check your GPS!", Toast.LENGTH_LONG).show();

        } else {

            setMarkers(_currentLocation, "Talbert Hall", 43.000772, -78.7915403);
            setMarkers(_currentLocation, "Alumni Arena", 43.000582, -78.781136);
            setMarkers(_currentLocation, "UB Center for the Arts", 43.001205, -78.783030);
            setMarkers(_currentLocation, "Lockwood Memorial Library", 43.000407, -78.785796);
            setMarkers(_currentLocation, "Student Union", 43.001256, -78.786241);
            setMarkers(_currentLocation, "Clemens Hall", 43.000523, -78.784979);
            setMarkers(_currentLocation, "Charles B. Sears Law Library", 43.000412, -78.787968);
            setMarkers(_currentLocation, "Oscar A. Silverman Library", 43.001107, -78.789558);
            setMarkers(_currentLocation, "Commons", 43.001845, -78.785193);
            setMarkers(_currentLocation, "UB Stadium", 42.999159, -78.777510);


            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 18);
            mMap.animateCamera(cameraUpdate);


        }

    }

/*
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }*/

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    public void onStart() {
        super.onStart();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //client.connect();
        //AppIndex.AppIndexApi.start(client, getIndexApiAction());
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.start(client, getIndexApiAction0());
    }

    @Override
    public void onStop() {
        super.onStop();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction0());

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        // AppIndex.AppIndexApi.end(client, getIndexApiAction());
        //  client.disconnect();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.disconnect();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction0() {
        Thing object = new Thing.Builder()
                .setName("FitnessMap Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }



    @Override
    public void onInfoWindowClick(Marker marker) {

        float distance;
        if (marker.getTitle().equals("Alumni Arena")){
            distance = _distanceTo.get("Alumni Arena");
            if(distance >= 500){
                Toast.makeText(this, "Sorry, You are far away from the destination, cannot do activity", Toast.LENGTH_LONG).show();
            }
            else if (distance > 100 && distance < 500){
                Toast.makeText(this, "Hey, You are almost close to destination, you only can do activity when you are there!", Toast.LENGTH_LONG).show();

            }
            else{
                Intent intent = new Intent(getApplicationContext(), aaActivity.class);
                startActivity(intent);
            }
        }
        else if(marker.getTitle().equals("Oscar A. Silverman Library")){
            distance = _distanceTo.get("Oscar A. Silverman Library");
            if(distance >= 500){
                Toast.makeText(this, "Sorry, You are far away from the destination, cannot do activity", Toast.LENGTH_LONG).show();
            }
            else if (distance > 100 && distance < 500){
                Toast.makeText(this, "Hey, You are almost close to destination, you only can do activity when you are there!", Toast.LENGTH_LONG).show();

            }
            else{
                Intent intent = new Intent(getApplicationContext(), cpActivity.class);
                startActivity(intent);
            }
        }
        else if(marker.getTitle().equals("Lockwood Memorial Library")) {
            distance = _distanceTo.get("Lockwood Memorial Library");
            if(distance >= 500){
                Toast.makeText(this, "Sorry, You are far away from the destination, cannot do activity", Toast.LENGTH_LONG).show();
            }
            else if (distance > 100 && distance < 500){
                Toast.makeText(this, "Hey, You are almost close to destination, you only can do activity when you are there!", Toast.LENGTH_LONG).show();

            }
            else{
                Intent intent = new Intent(getApplicationContext(), lwActivity.class);
                startActivity(intent);
            }
        }
        else if(marker.getTitle().equals("Student Union")) {
            distance = _distanceTo.get("Student Union");
            if(distance >= 500){
                Toast.makeText(this, "Sorry, You are far away from the destination, cannot do activity", Toast.LENGTH_LONG).show();
            }
            else if (distance > 100 && distance < 500){
                Toast.makeText(this, "Hey, You are almost close to destination, you only can do activity when you are there!", Toast.LENGTH_LONG).show();

            }
            else{
                Intent intent = new Intent(getApplicationContext(), suActivity.class);
                startActivity(intent);
            }
        }
        else if(marker.getTitle().equals("UB Stadium")) {
            distance = _distanceTo.get("UB Stadium");
            if(distance >= 500){
                Toast.makeText(this, "Sorry, You are far away from the destination, cannot do activity", Toast.LENGTH_LONG).show();
            }
            else if (distance > 100 && distance < 500){
                Toast.makeText(this, "Hey, You are almost close to destination, you only can do activity when you are there!", Toast.LENGTH_LONG).show();

            }
            else{
                Intent intent = new Intent(getApplicationContext(), stActivity.class);
                startActivity(intent);
            }
        }


    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }
}








