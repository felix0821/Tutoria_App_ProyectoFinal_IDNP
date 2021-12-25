package com.idnp.tutoria_proyecto_final_idnp.interfaces;

import android.content.SharedPreferences;

import com.google.firebase.firestore.FirebaseFirestore;
import com.idnp.tutoria_proyecto_final_idnp.UsersSQLiteOpenHelper;

public interface Login {

    interface View{
        void showMessage(int code);
    }

    interface Presenter{
        void login(FirebaseFirestore db, UsersSQLiteOpenHelper admin, SharedPreferences session, String username, String password, boolean remember);
        void showMessage(int code);
    }

    interface Model{
        void login(FirebaseFirestore db, UsersSQLiteOpenHelper admin, SharedPreferences session, String username, String password, boolean remember);
    }
}
