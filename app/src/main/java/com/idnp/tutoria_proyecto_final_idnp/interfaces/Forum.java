package com.idnp.tutoria_proyecto_final_idnp.interfaces;

import android.content.SharedPreferences;

public interface Forum {
    interface View{
        void showForum(String nombre);
    }
    interface Presenter{
        void showSesion(String nombre);
        void chargePreferences(SharedPreferences session);
        //void back(SharedPreferences session);
    }

    interface Model{
        void chargePreferences(SharedPreferences session);
        void commentPreferences(SharedPreferences session);
    }
}
