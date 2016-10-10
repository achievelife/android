package com.example.english.basicgpssystem;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.sip.SipAudioCall;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;

/**
 * Created by English on 2016/10/8.
 */
@SuppressWarnings("MissingPermission")
public class GPS_Service extends Service {
    private LocationListener listener;
    private LocationManager locationManager;
    public IBinder onBind(Intent intent){
        return null;
    }

    public void OnCreate(){
        listener = new LocationListener(){
             public void onLocationChanged(Location location){
                          Intent i=new Intent("location_update");
                 i.putExtra("coordinates",location.getLongitude()+" "+location.getLatitude());
                 sendBroadcast(i);
             }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                       Intent i=new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                }
            };
                locationManager=(LocationManager)getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3000,0,listener);
        }

    public void onDestroy(){
                super.onDestroy();
         if(locationManager!=null){
             locationManager.removeUpdates(listener);
         }
     }
    }
