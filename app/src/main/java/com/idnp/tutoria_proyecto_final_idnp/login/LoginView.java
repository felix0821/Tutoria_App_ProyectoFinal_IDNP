package com.idnp.tutoria_proyecto_final_idnp.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.idnp.tutoria_proyecto_final_idnp.R;
import com.idnp.tutoria_proyecto_final_idnp.UsersSQLiteOpenHelper;

public class LoginView extends AppCompatActivity implements Login.View{

    private EditText etUsername;
    private EditText etPassword;
    private CheckBox cbRemember;

    private UsersSQLiteOpenHelper admin;

    private Login.Presenter presenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        cbRemember = (CheckBox) findViewById(R.id.cbRemember);

        admin = new UsersSQLiteOpenHelper(this,"tutoria", null, 1);



        presenter = new LoginPresenter(this);
    }

    public void login(View view){
        SharedPreferences session = getSharedPreferences("session", Context.MODE_PRIVATE);
        presenter.login(admin, session, etUsername.getText().toString(), etPassword.getText().toString(), cbRemember.isChecked());
    }

    @Override
    public void showMessage(int code) {
        if (code == 1) {
            Toast.makeText(this, "Successful login.", Toast.LENGTH_SHORT).show();
            finish();
        }
        else if(code == 2){
            Toast.makeText(this, "Incorrect username or password.", Toast.LENGTH_SHORT).show();
        }
        else if(code == 3){
            Toast.makeText(this, "You must fill in all the fields.", Toast.LENGTH_SHORT).show();
        }
        else if(code == 4){
            Toast.makeText(this, "Successful registration.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}