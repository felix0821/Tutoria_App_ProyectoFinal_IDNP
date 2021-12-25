package com.idnp.tutoria_proyecto_final_idnp.views;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.idnp.tutoria_proyecto_final_idnp.R;
import com.idnp.tutoria_proyecto_final_idnp.UsersSQLiteOpenHelper;
import com.idnp.tutoria_proyecto_final_idnp.interfaces.Login;
import com.idnp.tutoria_proyecto_final_idnp.presenters.LoginPresenter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragmentView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragmentView extends Fragment implements Login.View{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private EditText etUsername;
    private EditText etPassword;
    private CheckBox cbRemember;

    private Button btnLogin;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private UsersSQLiteOpenHelper admin;

    private Login.Presenter presenter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragmentView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragmentView.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragmentView newInstance(String param1, String param2) {
        LoginFragmentView fragment = new LoginFragmentView();
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
        return inflater.inflate(R.layout.fragment_login_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        etUsername = (EditText) view.findViewById(R.id.etUsername);
        etPassword = (EditText) view.findViewById(R.id.etPassword);
        cbRemember = (CheckBox) view.findViewById(R.id.cbRemember);

        //admin = new UsersSQLiteOpenHelper(this,"tutoria", null, 1);
        Log.d("Nombre", "Usuario2:creado");
        presenter = new LoginPresenter(this);
        btnLogin = (Button) view.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Nombre", "Usuario2:"+etUsername.getText().toString());
                SharedPreferences session = getContext().getSharedPreferences("session", Context.MODE_PRIVATE);
                presenter.login(db, admin, session, etUsername.getText().toString(), etPassword.getText().toString(), cbRemember.isChecked());
            }
        });
    }


    @Override
    public void showMessage(int code) {
        if (code == 1) {
            Toast.makeText(getContext(), "Successful login.", Toast.LENGTH_SHORT).show();
            getActivity().finish();
        }
        else if(code == 2){
            Toast.makeText(getContext(), "Incorrect username or password.", Toast.LENGTH_SHORT).show();
        }
        else if(code == 3){
            Toast.makeText(getContext(), "You must fill in all the fields.", Toast.LENGTH_SHORT).show();
        }
        else if(code == 4){
            Toast.makeText(getContext(), "Successful registration.", Toast.LENGTH_SHORT).show();
            getActivity().finish();
        }
    }
}