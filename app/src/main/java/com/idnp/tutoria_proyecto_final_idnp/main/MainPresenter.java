package com.idnp.tutoria_proyecto_final_idnp.main;

import android.content.SharedPreferences;

public class MainPresenter implements Main.Presenter{

    private Main.View view;
    private Main.Model model;

    public MainPresenter(Main.View view){
        this.view = view;
        model = new MainModel(this);
    }

    @Override
    public void showSesion(String nombre) {
        if(view != null){
            view.showSesion(nombre);
        }
    }

    @Override
    public void signOut(SharedPreferences session) {
        if(view != null){
            model.logout(session);
        }
    }

    @Override
    public void chargePreferences(SharedPreferences session) {
        model.chargePreferences(session);
    }

    @Override
    public void validateSession(SharedPreferences session) {
        model.validateSession(session);
    }
}
