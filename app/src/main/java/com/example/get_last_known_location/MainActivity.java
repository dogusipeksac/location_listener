package com.example.get_last_known_location;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.get_location);
    }

    public void getLocationButton(View view) {

            CheckUserPermsions();
    }
    void  getLocation(){
     /*   LocationManager lm=(LocationManager) getSystemService(LOCATION_SERVICE);
        Location location=lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        textView.setText("log:"+String.valueOf(location.getLongitude())+"lat:"
                +String.valueOf(location.getLatitude()));*/
        MyLocationListener myLocationListener=new MyLocationListener(this);
        LocationManager lm=(LocationManager) getSystemService(LOCATION_SERVICE);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,30,5,myLocationListener);

    }
    //access to permsions
    void CheckUserPermsions(){
        if ( Build.VERSION.SDK_INT >= 23){
            if ((ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED) &&
                    (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) !=
                            PackageManager.PERMISSION_GRANTED)){
                requestPermissions(new String[]{
                                android.Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.READ_CONTACTS},
                        REQUEST_CODE_ASK_PERMISSIONS);
                return ;
            }
        }

        getLocation();// init the contact list

    }
    //get acces to location permsion
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // gps
                    getLocation();
                }
                if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    // read contact list

                }
                else {
                    // Permission Denied
                    Toast.makeText( this,"you denail the location access" , Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}