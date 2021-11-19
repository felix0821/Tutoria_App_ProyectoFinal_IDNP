package com.idnp.tutoria_proyecto_final_idnp.main;


import android.content.SharedPreferences;

public class MainModel implements Main.Model{

    private Main.Presenter presenter;
    private String message;


    public MainModel(Main.Presenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void logout(SharedPreferences session) {
        message = "Sin sesión activa.";
        SharedPreferences.Editor editor = session.edit();
        editor.remove("remember");
        editor.remove("paternalSurname");
        editor.remove("name");
        editor.remove("id");
        editor.remove("username");
        editor.commit();
        presenter.showSesion(message);
    }

    @Override
    public void chargePreferences(SharedPreferences session) {
        String name = session.getString("name", "");
        if(!name.equals("")){
            String paternalSurname = session.getString("paternalSurname", "");
            message = name+" "+paternalSurname;
        }
        else{
            message = "Sin sesión activa.";
        }
        presenter.showSesion(message);
    }

    @Override
    public void validateSession(SharedPreferences session) {
        Boolean remember = session.getBoolean("remember", false);
        if(!remember){
            SharedPreferences.Editor editor = session.edit();
            editor.remove("remember");
            editor.remove("paternalSurname");
            editor.remove("name");
            editor.remove("id");
            editor.remove("username");
            editor.commit();
        }
    }
}
