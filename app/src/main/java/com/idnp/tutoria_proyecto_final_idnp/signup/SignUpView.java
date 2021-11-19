package com.idnp.tutoria_proyecto_final_idnp.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.idnp.tutoria_proyecto_final_idnp.R;
import com.idnp.tutoria_proyecto_final_idnp.UsersSQLiteOpenHelper;
import com.idnp.tutoria_proyecto_final_idnp.secondregister.SecondRegisterView;

public class SignUpView extends AppCompatActivity implements SignUp.View {

    private EditText etUsername, etEmail, etName, etPaternalSurname, etMaternalSurname;
    private EditText etPassword, etRepeatPassword;
    private RadioButton rbStudent, rbTeacher;

    private SignUp.Presenter presenter;

    private UsersSQLiteOpenHelper admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etName = (EditText) findViewById(R.id.etName);
        etPaternalSurname = (EditText) findViewById(R.id.etPaternalSurname);
        etMaternalSurname = (EditText) findViewById(R.id.etMaternalSurname);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etRepeatPassword = (EditText) findViewById(R.id.etRepeatPassword);
        rbStudent = (RadioButton) findViewById(R.id.rbStudent);
        rbTeacher = (RadioButton) findViewById(R.id.rbTeacher);

        presenter = new SignUpPresenter(this);

        admin = new UsersSQLiteOpenHelper(this,"tutoria", null, 1);
    }

    public void register(View view){
        presenter.register(admin, etUsername.getText().toString(),
                etEmail.getText().toString(),
                etName.getText().toString(),
                etPaternalSurname.getText().toString(),
                etMaternalSurname.getText().toString(),
                etPassword.getText().toString(),
                etRepeatPassword.getText().toString(),
                rbStudent.isChecked(),
                rbTeacher.isChecked());
    }

    @Override
    public void showMessage(int code) {
        if (code == 1) {
            Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
        }
        else if(code == 2){
            Toast.makeText(this, "You must fill in all the fields.", Toast.LENGTH_SHORT).show();
        }
        else if(code == 3){
            Toast.makeText(this, "You must select your role.", Toast.LENGTH_SHORT).show();
        }
        else if(code == 4){
            Toast.makeText(this, "Successful registration.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void continueRegister(String[] data) {
        Intent secondRegister = new Intent(this, SecondRegisterView.class);
        secondRegister.putExtra("data", data);
        startActivity(secondRegister);
        finish();
    }
}