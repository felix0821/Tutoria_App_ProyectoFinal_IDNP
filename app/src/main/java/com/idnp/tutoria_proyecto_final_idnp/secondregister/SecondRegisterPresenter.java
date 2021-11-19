package com.idnp.tutoria_proyecto_final_idnp.secondregister;

import android.location.Geocoder;

import com.google.android.gms.maps.GoogleMap;
import com.idnp.tutoria_proyecto_final_idnp.UsersSQLiteOpenHelper;

public class SecondRegisterPresenter implements SecondRegister.Presenter {

    private SecondRegister.View view;
    private SecondRegister.Model model;

    public SecondRegisterPresenter(SecondRegister.View view){
        this.view = view;
        model = new SecondRegisterModel(this);
    }

    @Override
    public void searchMap(GoogleMap mMap, double lat, double lon, Geocoder geocoder) {
        model.searchMap(mMap, lat, lon, geocoder);
    }

    @Override
    public void showAddress(String address) {
        view.showAddress(address);
    }

    @Override
    public void register(UsersSQLiteOpenHelper admin, String[] data, boolean cb1, boolean cb2, boolean cb3, double lat, double lon, String address) {
        model.register(admin, data, cb1, cb2, cb3, lat, lon, address);
    }

    @Override
    public void showMessage(int code) {
        view.showMessage(code);
    }
}
