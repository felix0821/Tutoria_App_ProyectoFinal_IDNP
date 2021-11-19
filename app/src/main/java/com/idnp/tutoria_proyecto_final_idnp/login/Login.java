package com.idnp.tutoria_proyecto_final_idnp.login;

import android.content.SharedPreferences;

import com.idnp.tutoria_proyecto_final_idnp.UsersSQLiteOpenHelper;

public interface Login {

    interface View{
        void showMessage(int code);
    }

    interface Presenter{
        void login(UsersSQLiteOpenHelper admin, SharedPreferences session, String username, String password, boolean remember);
        void showMessage(int code);
    }

    interface Model{
        void login(UsersSQLiteOpenHelper admin, SharedPreferences session, String username, String password, boolean remember);
    }
}
