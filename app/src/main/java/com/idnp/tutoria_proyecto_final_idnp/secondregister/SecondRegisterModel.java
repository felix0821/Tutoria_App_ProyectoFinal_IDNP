package com.idnp.tutoria_proyecto_final_idnp.secondregister;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.idnp.tutoria_proyecto_final_idnp.UsersSQLiteOpenHelper;

import java.io.IOException;
import java.util.List;

public class SecondRegisterModel implements SecondRegister.Model {

    private SecondRegister.Presenter presenter;

    public SecondRegisterModel(SecondRegister.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void searchMap(GoogleMap mMap, double lat, double lon, Geocoder geocoder) {
        LatLng actually = new LatLng(lat, lon);
        mMap.addMarker(new MarkerOptions().position(actually).title("Casa ("+lat+", "+lon+")"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(actually,18));

        if (lat != 0.0 && lon != 0.0) {
            try {
                List<Address> list = geocoder.getFromLocation(
                        lat, lon, 1);
                if (!list.isEmpty()) {
                    Address DirCalle = list.get(0);
                    presenter.showAddress(DirCalle.getAddressLine(0));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void register(UsersSQLiteOpenHelper admin, String[] data, boolean cb1, boolean cb2, boolean cb3, double lat, double lon, String address) {
        SQLiteDatabase database = admin.getWritableDatabase();
        Cursor c = database.rawQuery("select * from users",null);
        int id = c.getCount()+1;
        ContentValues registry = new ContentValues();
        registry.put("id", id);
        registry.put("username", data[0]);
        registry.put("email", data[1]);
        registry.put("name", data[2]);
        registry.put("paternalSurname", data[3]);
        registry.put("maternalSurname", data[4]);
        registry.put("password", data[5]);
        if(cb1){
            registry.put("teachingArea1",1);
        }
        else{
            registry.put("teachingArea1",0);
        }
        if(cb2){
            registry.put("teachingArea2",1);
        }
        else{
            registry.put("teachingArea2",0);
        }
        if(cb3){
            registry.put("teachingArea3",1);
        }
        else{
            registry.put("teachingArea3",0);
        }
        registry.put("latitud", lat);
        registry.put("longitud", lon);
        registry.put("address",address);
        database.insert("users", null, registry);
        presenter.showMessage(4);
        database.close();
    }
}
