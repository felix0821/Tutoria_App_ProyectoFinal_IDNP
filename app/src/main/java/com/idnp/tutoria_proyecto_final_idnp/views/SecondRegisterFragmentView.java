package com.idnp.tutoria_proyecto_final_idnp.views;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.firebase.firestore.FirebaseFirestore;
import com.idnp.tutoria_proyecto_final_idnp.R;
import com.idnp.tutoria_proyecto_final_idnp.UsersSQLiteOpenHelper;
import com.idnp.tutoria_proyecto_final_idnp.interfaces.SecondRegister;
import com.idnp.tutoria_proyecto_final_idnp.presenters.SecondRegisterPresenter;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SecondRegisterFragmentView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondRegisterFragmentView extends Fragment implements SecondRegister.View, OnMapReadyCallback {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private GoogleMap mMap;
    private MapView mapView;

    final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 13;

    private double lat, lon;

    private CheckBox cbMaths;
    private CheckBox cbCommunication;
    private CheckBox cbEnglish;
    private TextView tvAddress;
    private Button local;
    private Button btnRegister;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private String[] data;
    private UsersSQLiteOpenHelper admin;

    private SecondRegister.Presenter presenter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SecondRegisterFragmentView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SecondRegisterFragmentView.
     */
    // TODO: Rename and change types and number of parameters
    public static SecondRegisterFragmentView newInstance(String param1, String param2) {
        SecondRegisterFragmentView fragment = new SecondRegisterFragmentView();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getParentFragmentManager().setFragmentResultListener("dataUser", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                data = result.getStringArray("data");
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second_register_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);//getActivity().getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        cbMaths = (CheckBox) view.findViewById(R.id.cbMaths);
        cbCommunication = (CheckBox) view.findViewById(R.id.cbCommunication);
        cbEnglish = (CheckBox) view.findViewById(R.id.cbEnglish);
        tvAddress = (TextView) view.findViewById(R.id.tvAddress);

        admin = new UsersSQLiteOpenHelper(getContext(),"tutoria", null, 1);

        int permissionCheck = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION);
        if(permissionCheck == PackageManager.PERMISSION_DENIED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)){

            }
            else{
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION},13);
            }
        }


        presenter = new SecondRegisterPresenter(this);

        local = (Button) view.findViewById(R.id.local);
        local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
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
                int permissionCheck = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION);
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
                Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
                presenter.searchMap(mMap, lat, lon, geocoder);
            }
        });

        btnRegister = (Button) view.findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.register(db, admin, data, cbMaths.isChecked(), cbCommunication.isChecked(), cbEnglish.isChecked(), lat, lon, tvAddress.getText().toString());
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Call ActivityCompat#requestPermissions
            // here to request the missing permissions, and then override
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission.

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_FINE_LOCATION);

        } else {
            //do the actions that require location
            mMap.setMyLocationEnabled(true);
        }
    }


    @Override
    public void showAddress(String address) {
        tvAddress.setText(address);
    }

    @Override
    public void showMessage(int code) {
        if(code == 4){
            Toast.makeText(getContext(), "Successful registration.", Toast.LENGTH_SHORT).show();
            getActivity().finish();
        }
    }

}