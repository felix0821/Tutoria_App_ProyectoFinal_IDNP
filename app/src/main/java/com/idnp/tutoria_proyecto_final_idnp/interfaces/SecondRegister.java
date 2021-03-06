package com.idnp.tutoria_proyecto_final_idnp.interfaces;

import android.location.Geocoder;

import com.google.android.gms.maps.GoogleMap;
import com.google.firebase.firestore.FirebaseFirestore;
import com.idnp.tutoria_proyecto_final_idnp.UsersSQLiteOpenHelper;

public interface SecondRegister {

    interface View{
        void showAddress(String address);
        void showMessage(int code);
    }

    interface Presenter{
        void searchMap(GoogleMap mMap, double lat, double lon, Geocoder geocoder);
        void showAddress(String address);
        void register(FirebaseFirestore db, UsersSQLiteOpenHelper admin, String[] data, boolean cb1, boolean cb2, boolean cb3, double lat, double lon, String address);
        void showMessage(int code);
    }

    interface Model{
        void searchMap(GoogleMap mMap, double lat, double lon, Geocoder geocoder);
        void register(FirebaseFirestore db, UsersSQLiteOpenHelper admin, String[] data, boolean cb1, boolean cb2, boolean cb3, double lat, double lon, String address);
    }
}
