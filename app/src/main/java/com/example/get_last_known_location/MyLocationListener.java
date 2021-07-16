package com.example.get_last_known_location;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class MyLocationListener implements LocationListener {
    Context  context;

    public MyLocationListener(Context context){

        this.context=context;
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        String loca="log:"+String.valueOf(location.getLongitude())+"lat:"
                +String.valueOf(location.getLatitude());
        Toast.makeText(context, loca, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

        Toast.makeText(context, "gps status is changed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        Toast.makeText(context, "gps is enabled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

        Toast.makeText(context, "gps is disabled", Toast.LENGTH_SHORT).show();
    }
}
