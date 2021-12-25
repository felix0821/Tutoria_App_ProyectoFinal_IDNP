package com.idnp.tutoria_proyecto_final_idnp.views;

import android.content.Context;
import android.content.Intent;
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
import android.widget.TextView;

import com.idnp.tutoria_proyecto_final_idnp.R;
import com.idnp.tutoria_proyecto_final_idnp.presenters.MainPresenter;
import com.idnp.tutoria_proyecto_final_idnp.interfaces.Main;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragmentView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragmentView extends Fragment implements Main.View{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView tvSesion;
    private Button btnLogin, btnSignIn, btnSignOut;

    private Main.Presenter presenter;

    SharedPreferences session;

    public MainFragmentView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragmentView.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragmentView newInstance(String param1, String param2) {
        MainFragmentView fragment = new MainFragmentView();
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
        return inflater.inflate(R.layout.fragment_main_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvSesion = (TextView) view.findViewById(R.id.tvSesion);
        presenter = new MainPresenter(this);

        btnSignIn = (Button) view.findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUp = new Intent(getContext(), SesionActivity.class);
                startActivity(signUp);
            }
        });

        btnLogin = (Button) view.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent login = new Intent(getContext(), SesionActivity.class);
                startActivity(login);
            }
        });

        btnSignOut = (Button) view.findViewById(R.id.btnSignOut);
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.signOut(session);
            }
        });

        session = getContext().getSharedPreferences("session", Context.MODE_PRIVATE);
        presenter.chargePreferences(session);
    }

    @Override
    public void showSesion(String nombre) {
        tvSesion.setText(nombre);
    }
}