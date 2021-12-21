package com.idnp.tutoria_proyecto_final_idnp.interactors;

import android.content.SharedPreferences;

import com.idnp.tutoria_proyecto_final_idnp.interfaces.Forum;

public class ForumModel implements Forum.Model{
    private Forum.Presenter presenter;
    private String username;


    public ForumModel(Forum.Presenter presenter){
        this.presenter = presenter;
    }
    @Override
    public void chargePreferences(SharedPreferences session) {
        String name = session.getString("username", "");
        if(!name.equals("")){
            username = name;
        }
        else{
            username = "Anonimo";
        }
        presenter.showSesion(username);
    }

    @Override
    public void commentPreferences(SharedPreferences session) {

    }
}
