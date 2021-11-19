package com.idnp.tutoria_proyecto_final_idnp.login;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.idnp.tutoria_proyecto_final_idnp.UsersSQLiteOpenHelper;

public class LoginModel implements Login.Model {

    private Login.Presenter presenter;

    public LoginModel(Login.Presenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void login(UsersSQLiteOpenHelper admin, SharedPreferences session, String username, String password, boolean remember) {
        SQLiteDatabase database = admin.getWritableDatabase();

        if(!username.isEmpty() && !password.isEmpty()){
            Cursor c =database.rawQuery("select id, name, paternalSurname from users where username ='"+username+"' and password ='"+password+"'", null);

            if(c.moveToFirst()){
                SharedPreferences.Editor editor = session.edit();
                editor.putString("id", c.getString(0));
                editor.putString("username", username);
                editor.putString("name", c.getString(1));
                editor.putString("paternalSurname", c.getString(2));
                editor.putBoolean("remember", remember);
                editor.commit();

                presenter.showMessage(1);
                database.close();
            }
            else{
                presenter.showMessage(2);
            }
        }
        else{
            presenter.showMessage(3);
        }
    }
}
