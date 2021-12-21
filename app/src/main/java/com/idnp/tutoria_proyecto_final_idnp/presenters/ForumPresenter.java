package com.idnp.tutoria_proyecto_final_idnp.presenters;


import android.content.SharedPreferences;

import com.idnp.tutoria_proyecto_final_idnp.interactors.ForumModel;
import com.idnp.tutoria_proyecto_final_idnp.interfaces.Forum;

public class ForumPresenter implements Forum.Presenter{
    private Forum.View view;
    private Forum.Model model;

    public ForumPresenter(Forum.View view){
        this.view = view;
        model = new ForumModel(this);
    }
    @Override
    public void showSesion(String nombre) {
        if(view != null){
            view.showForum(nombre);
        }
    }

    @Override
    public void chargePreferences(SharedPreferences session) {
        model.chargePreferences(session);
    }

}
