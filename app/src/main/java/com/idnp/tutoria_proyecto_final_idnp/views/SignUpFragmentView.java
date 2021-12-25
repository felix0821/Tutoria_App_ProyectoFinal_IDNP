package com.idnp.tutoria_proyecto_final_idnp.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.idnp.tutoria_proyecto_final_idnp.R;
import com.idnp.tutoria_proyecto_final_idnp.UsersSQLiteOpenHelper;
import com.idnp.tutoria_proyecto_final_idnp.interactors.SecondRegisterModel;
import com.idnp.tutoria_proyecto_final_idnp.interfaces.SignUp;
import com.idnp.tutoria_proyecto_final_idnp.presenters.SignUpPresenter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpFragmentView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpFragmentView extends Fragment implements SignUp.View{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText etUsername, etEmail, etName, etPaternalSurname, etMaternalSurname;
    private EditText etPassword, etRepeatPassword;
    private RadioButton rbStudent, rbTeacher;
    private Button btnRegistro;

    private SignUp.Presenter presenter;

    private UsersSQLiteOpenHelper admin;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public SignUpFragmentView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUpFragmentView.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUpFragmentView newInstance(String param1, String param2) {
        SignUpFragmentView fragment = new SignUpFragmentView();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        etUsername = (EditText) view.findViewById(R.id.etUsername);
        etEmail = (EditText) view.findViewById(R.id.etEmail);
        etName = (EditText) view.findViewById(R.id.etName);
        etPaternalSurname = (EditText) view.findViewById(R.id.etPaternalSurname);
        etMaternalSurname = (EditText) view.findViewById(R.id.etMaternalSurname);
        etPassword = (EditText) view.findViewById(R.id.etPassword);
        etRepeatPassword = (EditText) view.findViewById(R.id.etRepeatPassword);
        rbStudent = (RadioButton) view.findViewById(R.id.rbStudent);
        rbTeacher = (RadioButton) view.findViewById(R.id.rbTeacher);

        presenter = new SignUpPresenter(this);

        admin = new UsersSQLiteOpenHelper(getContext(),"tutoria", null, 1);

        btnRegistro = (Button) view.findViewById(R.id.btnRegistro);
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.register(db, admin, etUsername.getText().toString(),
                        etEmail.getText().toString(),
                        etName.getText().toString(),
                        etPaternalSurname.getText().toString(),
                        etMaternalSurname.getText().toString(),
                        etPassword.getText().toString(),
                        etRepeatPassword.getText().toString(),
                        rbStudent.isChecked(),
                        rbTeacher.isChecked());
            }
        });
    }


    @Override
    public void showMessage(int code) {
        if (code == 1) {
            Toast.makeText(getContext(), "Passwords do not match.", Toast.LENGTH_SHORT).show();
        }
        else if(code == 2){
            Toast.makeText(getContext(), "You must fill in all the fields.", Toast.LENGTH_SHORT).show();
        }
        else if(code == 3){
            Toast.makeText(getContext(), "You must select your role.", Toast.LENGTH_SHORT).show();
        }
        else if(code == 4){
            Toast.makeText(getContext(), "Successful registration.", Toast.LENGTH_SHORT).show();
            getActivity().finish();
        }
    }

    @Override
    public void continueRegister(String[] data) {
        Bundle bundle = new Bundle();
        bundle.putStringArray("data", data);
        getParentFragmentManager().setFragmentResult("dataUser", bundle);
        Fragment fragment = new SecondRegisterFragmentView();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_login, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}