package com.idnp.tutoria_proyecto_final_idnp.secondregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import com.idnp.tutoria_proyecto_final_idnp.R;
import com.idnp.tutoria_proyecto_final_idnp.UsersSQLiteOpenHelper;

import java.util.Locale;

public class SecondRegisterView extends AppCompatActivity implements SecondRegister.View, OnMapReadyCallback {

    private GoogleMap mMap;

    final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 13;

    private double lat, lon;

    private CheckBox cbMaths;
    private CheckBox cbCommunication;
    private CheckBox cbEnglish;
    private TextView tvAddress;

    private String[] data;
    private UsersSQLiteOpenHelper admin;

    private SecondRegister.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_register);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        cbMaths = (CheckBox) findViewById(R.id.cbMaths);
        cbCommunication = (CheckBox) findViewById(R.id.cbCommunication);
        cbEnglish = (CheckBox) findViewById(R.id.cbEnglish);
        tvAddress = (TextView) findViewById(R.id.tvAddress);
        data = getIntent().getStringArrayExtra("data");
        admin = new UsersSQLiteOpenHelper(this,"tutoria", null, 1);

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if(permissionCheck == PackageManager.PERMISSION_DENIED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){

            }
            else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},13);
            }
        }


        presenter = new SecondRegisterPresenter(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Call ActivityCompat#requestPermissions
            // here to request the missing permissions, and then override
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission.

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_FINE_LOCATION);

        } else {
            //do the actions that require location
            mMap.setMyLocationEnabled(true);
        }
    }

    public void location(View view){
        LocationManager locationManager = (LocationManager) SecondRegisterView.this.getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                lat = location.getLatitude();
                lon = location.getLongitude();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(@NonNull String provider) {

            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {

            }
        };
        int permissionCheck = ContextCompat.checkSelfPermission(SecondRegisterView.this, Manifest.permission.ACCESS_FINE_LOCATION);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        presenter.searchMap(mMap, lat, lon, geocoder);
    }

    public void register(View view){
        presenter.register(admin, data, cbMaths.isChecked(), cbCommunication.isChecked(), cbEnglish.isChecked(), lat, lon, tvAddress.getText().toString());
    }

    @Override
    public void showAddress(String address) {
        tvAddress.setText(address);
    }

    @Override
    public void showMessage(int code) {
        if(code == 4){
            Toast.makeText(this, "Successful registration.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}