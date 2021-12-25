package com.idnp.tutoria_proyecto_final_idnp.presenters;

import android.content.SharedPreferences;

import com.google.firebase.firestore.FirebaseFirestore;
import com.idnp.tutoria_proyecto_final_idnp.UsersSQLiteOpenHelper;
import com.idnp.tutoria_proyecto_final_idnp.interfaces.Login;
import com.idnp.tutoria_proyecto_final_idnp.interactors.LoginModel;

public class LoginPresenter implements Login.Presenter{

    private Login.View view;
    private Login.Model model;

    public LoginPresenter(Login.View view){
        this.view = view;
        model = new LoginModel(this);
    }


    @Override
    public void login(FirebaseFirestore db, UsersSQLiteOpenHelper admin, SharedPreferences session, String username, String password, boolean remember) {
        model.login(db, admin, session, username, password, remember);
    }

    @Override
    public void showMessage(int code) {
        view.showMessage(code);
    }
}
