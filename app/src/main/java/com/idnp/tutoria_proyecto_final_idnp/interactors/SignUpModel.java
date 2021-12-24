package com.idnp.tutoria_proyecto_final_idnp.interactors;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.firebase.firestore.FirebaseFirestore;
import com.idnp.tutoria_proyecto_final_idnp.UsersSQLiteOpenHelper;
import com.idnp.tutoria_proyecto_final_idnp.interfaces.SignUp;

import java.util.HashMap;

public class SignUpModel implements SignUp.Model {

    private SignUp.Presenter presenter;

    public SignUpModel(SignUp.Presenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void register(FirebaseFirestore db, UsersSQLiteOpenHelper admin, String username, String email, String name, String paternalSurname, String maternalSurname, String password, String repeatPassword, boolean student, boolean teacher) {
        if(student == true){
            SQLiteDatabase database = admin.getWritableDatabase();

            if(!username.isEmpty() && !email.isEmpty() && !name.isEmpty() && !paternalSurname.isEmpty() && !maternalSurname.isEmpty()
            && !password.isEmpty() && !repeatPassword.isEmpty()){
                if(password.equals(repeatPassword)){
                    /*Cursor c = database.rawQuery("select * from users",null);
                    int id = c.getCount()+1;
                    ContentValues registry = new ContentValues();
                    registry.put("id", id);
                    registry.put("username", username);
                    registry.put("email", email);
                    registry.put("name", name);
                    registry.put("paternalSurname", paternalSurname);
                    registry.put("maternalSurname", maternalSurname);
                    registry.put("password", password);

                    database.insert("users", null, registry);*/

                    HashMap<String,Object> usuarios = new HashMap<String,Object>();
                    usuarios.put("email", email);
                    usuarios.put("name", name);
                    usuarios.put("paternalSurname", paternalSurname);
                    usuarios.put("maternalSurname", maternalSurname);
                    usuarios.put("password", password);
                    usuarios.put("tutor",false);
                    db.collection("users").document(username).set(usuarios);
                    presenter.showMessage(4);
                    database.close();
                }
                else{
                    presenter.showMessage(1);
                }

            }
            else{
                presenter.showMessage(2);
            }
        }
        else if(teacher == true){
            if(!username.isEmpty() && !email.isEmpty() && !name.isEmpty() && !paternalSurname.isEmpty() && !maternalSurname.isEmpty()
                    && !password.isEmpty() && !repeatPassword.isEmpty()) {
                if (password.equals(repeatPassword)) {
                    presenter.continueRegister(new String[]{username, email, name, paternalSurname, maternalSurname, password});
                }
                else{
                    presenter.showMessage(1);
                }
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
