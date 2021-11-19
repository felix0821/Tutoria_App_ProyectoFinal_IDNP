package com.idnp.tutoria_proyecto_final_idnp.signup;

import com.idnp.tutoria_proyecto_final_idnp.UsersSQLiteOpenHelper;

public interface SignUp {

    interface View{
        void continueRegister(String [] data);
        void showMessage(int code);
    }

    interface Presenter{
        void register(UsersSQLiteOpenHelper admin, String username,
                      String email,
                      String name,
                      String paternalSurname,
                      String maternalSurname,
                      String password,
                      String repeatPassword,
                      boolean student,
                      boolean teacher);
        void continueRegister(String [] data);
        void showMessage(int code);
    }

    interface Model{
        void register(UsersSQLiteOpenHelper admin, String username,
                      String email,
                      String name,
                      String paternalSurname,
                      String maternalSurname,
                      String password,
                      String repeatPassword,
                      boolean student,
                      boolean teacher);
    }
}
