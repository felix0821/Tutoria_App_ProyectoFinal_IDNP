package com.idnp.tutoria_proyecto_final_idnp.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.idnp.tutoria_proyecto_final_idnp.R;
import com.idnp.tutoria_proyecto_final_idnp.forum.ForumView;
import com.idnp.tutoria_proyecto_final_idnp.login.LoginView;
import com.idnp.tutoria_proyecto_final_idnp.signup.SignUpView;

public class MainView extends AppCompatActivity implements Main.View {

    private TextView tvSesion;

    private Main.Presenter presenter;

    SharedPreferences session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvSesion = (TextView) findViewById(R.id.tvSesion);
        presenter = new MainPresenter(this);
        session = getSharedPreferences("session", Context.MODE_PRIVATE);
        presenter.chargePreferences(session);
    }

    public void forum(View view){
        Intent forum = new Intent(this, ForumView.class);
        startActivity(forum);
    }

    public void login(View view){
        Intent login = new Intent(this, LoginView.class);
        startActivity(login);
    }


    public void signUp(View view){
        Intent signUp = new Intent(this, SignUpView.class);
        startActivity(signUp);
    }

    public void signOut(View view){
        presenter.signOut(session);
    }

    @Override
    public void showSesion(String nombre) {
        tvSesion.setText(nombre);
    }

    @Override
    public void onRestart(){
        super.onRestart();
        presenter.chargePreferences(session);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        presenter.validateSession(session);
    }


}