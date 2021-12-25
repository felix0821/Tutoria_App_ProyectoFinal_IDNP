package com.idnp.tutoria_proyecto_final_idnp.interactors;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.idnp.tutoria_proyecto_final_idnp.UsersSQLiteOpenHelper;
import com.idnp.tutoria_proyecto_final_idnp.interfaces.Login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginModel implements Login.Model {

    private Login.Presenter presenter;

    public LoginModel(Login.Presenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void login(FirebaseFirestore db, UsersSQLiteOpenHelper admin, SharedPreferences session, String username, String password, boolean remember) {
        Log.d("Nombre", "Usuario2:"+username);
        if(!username.isEmpty() && !password.isEmpty()){


            db.collection("users").whereEqualTo("username", username).whereEqualTo("password", password).get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            if(queryDocumentSnapshots.getDocuments().get(0).exists()){
                                SharedPreferences.Editor editor = session.edit();
                                editor.putString("username", queryDocumentSnapshots.getDocuments().get(0).getString("username"));
                                Log.d("Nombre", "Usuario:"+ queryDocumentSnapshots.getDocuments().get(0).getString("username"));
                                editor.putString("name", queryDocumentSnapshots.getDocuments().get(0).getString("name"));
                                editor.putString("paternalSurname", queryDocumentSnapshots.getDocuments().get(0).getString("paternalSurname"));
                                editor.putBoolean("remember", remember);
                                editor.commit();

                                presenter.showMessage(1);
                            }

                        }
                    });


            /*if(c.moveToFirst()){
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
            }*/
        }
        else{
            presenter.showMessage(3);
        }
    }
}
