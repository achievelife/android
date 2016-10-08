package cse442.achievelife;


import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.identity.intents.Address;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;


public class FitnessMap extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private GoogleMap googleMap;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient myGoogleApiClient;
    private LocationRequest myLocationRequest;
    private Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_fitness);

        if (googleServicesAvailable()) {
            //Toast.makeText(this, "play services perfect!", Toast.LENGTH_LONG).show();
            setContentView(R.layout.activity_fitness);
            initMap();
        }


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;

        initMarkerLocationZoom(43.002961, -78.7868477, 18);



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
        }
        googleMap.setMyLocationEnabled(true);

        setMarkers("Alumni Arena", 43.000582, -78.781136);
        setMarkers("UB Center for the Arts", 43.001205, -78.783030);
        setMarkers("Lockwood Memorial Library", 43.000407, -78.785796);
        setMarkers("Student Union", 43.001256, -78.786241);
        setMarkers("Clemens Hall", 43.000523, -78.784979);
        setMarkers("Charles B. Sears Law Library", 43.000412, -78.787968);



/*
        myGoogleApiClient = new GoogleApiClient.Builder(this).addApi(LocationServices.API)
                .addConnectionCallbacks(this).addOnConnectionFailedListener(this)
                .build();
        myGoogleApiClient.connect();
*/

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

    public void initMap() {

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mainMap); // id get from layout.activity_fitness.xml
        mapFragment.getMapAsync(this); // once getMapAsync, onMapReady function call

    }

    public void initMarkerLocationZoom(double latitude, double longitude, float zoom) {
        // Add a marker in UB bookstore and move the camera
        LatLng UBBookStore = new LatLng(latitude, longitude);
        googleMap.addMarker(new MarkerOptions().position(UBBookStore).title("UB Bookstore"));
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(UBBookStore, zoom);
        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(UBBookStore));
        googleMap.moveCamera(cameraUpdate);

    }
    public void goToLocationZoom(double latitude, double longitude, float zoom) {
        LatLng latLng = new LatLng(latitude, longitude);
        // googleMap.addMarker(new MarkerOptions().position(UBBookStore).title("UB Bookstore"));
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, zoom);
        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(UBBookStore));
        googleMap.animateCamera(cameraUpdate);
    }

    public void setMarkers(String locality, double latitude, double longitude){
        MarkerOptions options = new MarkerOptions().title(locality)
                .position(new LatLng(latitude, longitude));
        marker = googleMap.addMarker(options);

    }










    @Override
    public void onConnected(@Nullable Bundle bundle) {
        myLocationRequest = LocationRequest.create();
        myLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY); // get the best user location
        myLocationRequest.setInterval(1000); // how many times i want the location.every 1sec get the location fetched

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(myGoogleApiClient, myLocationRequest, this); // this is who is listening the location


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        if(location == null){
            Toast.makeText(this, "Cannot get current location, check your GPS!", Toast.LENGTH_LONG).show();

        }else{
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 18);
            googleMap.animateCamera(cameraUpdate);

        }

    }


    //only for learning below, we might use it for sprint3
    /*
    public void getGeoLocation1(View view) throws IOException{

        EditText et = (EditText) findViewById(R.id.editText);
        String location = et.getText().toString();

        Geocoder gc = new Geocoder(this);
        List<Address> list = gc.getFromLocationName(location,1);
        Address address = list.get(0);
        String locality = address.getLocality();

        Toast.makeText(this, locality, Toast.LENGTH_LONG).show();

        double latitude = address.getLatitude();
        double longitude = address.getLongitude();
        initMarkerLocationZoom(latitude, longitude, 18);

    }
*/


}
