package com.idnp.tutoria_proyecto_final_idnp.forum;


import android.content.SharedPreferences;

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
