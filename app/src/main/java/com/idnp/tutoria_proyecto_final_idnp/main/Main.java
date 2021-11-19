package com.idnp.tutoria_proyecto_final_idnp.main;

import android.content.SharedPreferences;

public interface Main {

    interface View{
        void showSesion(String nombre);
    }

    interface Presenter{
        void showSesion(String nombre);
        void signOut(SharedPreferences session);
        void chargePreferences(SharedPreferences session);
        void validateSession(SharedPreferences session);
    }

    interface Model{
        void logout(SharedPreferences session);
        void chargePreferences(SharedPreferences session);
        void validateSession(SharedPreferences session);
    }
}
