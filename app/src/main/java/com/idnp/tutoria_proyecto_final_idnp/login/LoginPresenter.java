package com.idnp.tutoria_proyecto_final_idnp.login;

import android.content.SharedPreferences;

import com.idnp.tutoria_proyecto_final_idnp.UsersSQLiteOpenHelper;

public class LoginPresenter implements Login.Presenter{

    private Login.View view;
    private Login.Model model;

    public LoginPresenter(Login.View view){
        this.view = view;
        model = new LoginModel(this);
    }


    @Override
    public void login(UsersSQLiteOpenHelper admin, SharedPreferences session, String username, String password, boolean remember) {
        model.login(admin, session, username, password, remember);
    }

    @Override
    public void showMessage(int code) {
        view.showMessage(code);
    }
}
