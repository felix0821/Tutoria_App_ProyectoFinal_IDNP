package com.idnp.tutoria_proyecto_final_idnp.signup;

import com.idnp.tutoria_proyecto_final_idnp.UsersSQLiteOpenHelper;

public class SignUpPresenter implements SignUp.Presenter {

    private SignUp.View view;
    private SignUp.Model model;

    public SignUpPresenter(SignUp.View view){
        this.view = view;
        model = new SignUpModel(this);
    }

    @Override
    public void register(UsersSQLiteOpenHelper admin, String username, String email, String name, String paternalSurname, String maternalSurname, String password, String repeatPassword, boolean student, boolean teacher) {
        model.register(admin, username, email, name, paternalSurname, maternalSurname, password, repeatPassword, student, teacher);
    }

    @Override
    public void showMessage(int code) {
        if(view != null){
            view.showMessage(code);
        }
    }

    @Override
    public void continueRegister(String[] data) {
        view.continueRegister(data);
    }
}
