package cse442.achievelife;

import android.os.Bundle;
import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
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

import static com.google.android.gms.maps.GoogleMap.*;


public class FitnessMap extends FragmentActivity implements OnInfoWindowClickListener, OnMarkerClickListener, OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private GoogleMap _googleMap;
    private GoogleApiClient _googleApiClient;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    private GoogleApiClient _myGoogleApiClient;
    private LocationRequest _myLocationRequest;
    private Marker _marker;
    private Location _location;
    private Context _context;
    private boolean _isGpsEnabled;
    private boolean _isNetworkEnabled;
    private boolean _canGetLocation;
    private double _latitude;
    private double _longtitude;
    private static final long MIN_DISTANCE_CHANG_FOR_UPDATE = 10;
    private static final long MINI_TIME_BW_UPDATE = 1000 * 60 * 1;

    protected LocationManager _locationManager;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    public FitnessMap() {
        _isGpsEnabled = false;
        _canGetLocation = false;
        _isNetworkEnabled = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_fitness);

        if (googleServicesAvailable()) {
            //Toast.makeText(this, "play services perfect!", Toast.LENGTH_LONG).show();
            setContentView(R.layout.activity_fitness);
            initMap();
        }



        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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
        _googleMap = map;
        _googleMap.setMapType(MAP_TYPE_TERRAIN);


        //bookstore to be initial the location mark
        initMarkerLocationZoom(43.0008093, -78.7911584, 18);




        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
        }
        _googleMap.setMyLocationEnabled(true);







        /************************************/
        // below are all testing distance
        // hard code, no
        Location currentLocation = new Location("");
        currentLocation.setLatitude(43.000774);
        currentLocation.setLongitude(-78.7911897);            /**change code here, enable to get current latitude and longtitude, not hard code**********************************/

        /************************************************************/
        setMarkers(currentLocation, "Talbert Hall", 43.000772, -78.7915403);
        setMarkers(currentLocation, "Alumni Arena", 43.000582, -78.781136);
        setMarkers(currentLocation, "UB Center for the Arts", 43.001205, -78.783030);
        setMarkers(currentLocation, "Lockwood Memorial Library", 43.000407, -78.785796);
        setMarkers(currentLocation, "Student Union", 43.001256, -78.786241);
        setMarkers(currentLocation, "Clemens Hall", 43.000523, -78.784979);
        setMarkers(currentLocation, "Charles B. Sears Law Library", 43.000412, -78.787968);
        setMarkers(currentLocation, "Oscar A. Silverman Library",43.001107,-78.789558);
        setMarkers(currentLocation, "Commons", 43.001845, -78.785193);
        setMarkers(currentLocation, "UB Stadium", 43.000045, -78.777585);


        _googleMap.setOnMarkerClickListener((OnMarkerClickListener) this);
        _googleMap.setOnInfoWindowClickListener(this);

    }



    public double getLatitude() {
        if (_location != null) {
            _latitude = _location.getLatitude();
        }
        return _latitude;
    }

    public double getLongtitude() {
        if (_location != null) {
            _longtitude = _location.getLongitude();
        }

        return _longtitude;
    }

    public boolean canGetLocation() {
        return this._canGetLocation;
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
        LatLng UB = new LatLng(latitude, longitude);
        _googleMap.addMarker(new MarkerOptions().position(UB).title("UB Bookstore").alpha(0.5f));
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(UB, zoom);
        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(UBBookStore));

        _googleMap.moveCamera(cameraUpdate);

    }

    public void goToLocationZoom(double latitude, double longitude, float zoom) {
        LatLng latLng = new LatLng(latitude, longitude);
        // googleMap.addMarker(new MarkerOptions().position(UBBookStore).title("UB Bookstore"));
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, zoom);
        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(UBBookStore));
        _googleMap.animateCamera(cameraUpdate);
    }

    public void setMarkers(Location currentLocation, String locality, double latitude, double longitude) {

        Location destination = new Location(locality);
        destination.setLatitude(latitude);
        destination.setLongitude(longitude);

        float distance = currentLocation.distanceTo(destination);  /** get the distance*******************************/

        if (distance < 100) {
            _marker = _googleMap.addMarker(new MarkerOptions().title(locality)
                    .position(new LatLng(latitude, longitude))
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                    .snippet("The distance is: " + Float.toString(distance) + " meters"));
        } else if (distance < 500) {
            _marker = _googleMap.addMarker(new MarkerOptions().title(locality)
                    .position(new LatLng(latitude, longitude))
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                    .snippet("The distance is: " + Float.toString(distance) + " meters"));
        } else {
            _marker = _googleMap.addMarker(new MarkerOptions().title(locality)
                    .position(new LatLng(latitude, longitude))
                    .snippet("The distance is: " + Float.toString(distance) + " meters"));
        }
        _marker.showInfoWindow();
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
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

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        if (location == null) {
            Toast.makeText(this, "Cannot get current location, check your GPS!", Toast.LENGTH_LONG).show();

        } else {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 18);
            _googleMap.animateCamera(cameraUpdate);

        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
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
    public boolean onMarkerClick(Marker marker) {
        /*
        if (marker.getTitle().equals("Alumni Arena")){

            return true;
        }
*/
        
        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

        if (marker.getTitle().equals("Alumni Arena")){

            Intent intent = new Intent(getApplicationContext(), aaActivity.class);
            startActivity(intent);
        }
        else if(marker.getTitle().equals("Oscar A. Silverman Library")){
            Intent intent = new Intent(getApplicationContext(), cpActivity.class);
            startActivity(intent);
        }
        else if(marker.getTitle().equals("Lockwood Memorial Library")) {
            Intent intent = new Intent(getApplicationContext(), lwActivity.class);
            startActivity(intent);
        }
        else if(marker.getTitle().equals("Student Union")) {
            Intent intent = new Intent(getApplicationContext(), suActivity.class);
            startActivity(intent);
        }
        else if(marker.getTitle().equals("UB Stadium")) {
            Intent intent = new Intent(getApplicationContext(), stActivity.class);
            startActivity(intent);
        }

    }
}


