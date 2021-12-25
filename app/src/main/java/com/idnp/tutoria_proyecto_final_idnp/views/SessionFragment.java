package com.idnp.tutoria_proyecto_final_idnp.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.idnp.tutoria_proyecto_final_idnp.R;
import com.idnp.tutoria_proyecto_final_idnp.databinding.FragmentSessionBinding;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SessionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SessionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentSessionBinding binding;
    EditText año = null;
    Spinner mes = null;
    EditText dia = null;
    CheckBox duracion = null;
    Spinner hora = null;
    EditText minuto = null;
    Button agregar = null;
    EditText titulo = null;
    EditText descripcion = null;
    EditText localizacion = null;

    public SessionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SessionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SessionFragment newInstance(String param1, String param2) {
        SessionFragment fragment = new SessionFragment();
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
        return inflater.inflate(R.layout.fragment_session, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        año = (EditText) view.findViewById(R.id.etAño);
        mes = (Spinner) view.findViewById(R.id.spMes);
        dia = (EditText) view.findViewById(R.id.etDia);
        duracion = (CheckBox) view.findViewById(R.id.chkDuracion);
        hora = (Spinner) view.findViewById(R.id.spHora);
        minuto = (EditText) view.findViewById(R.id.etMinuto);
        titulo = (EditText) view.findViewById(R.id.etTitulo);
        descripcion = (EditText) view.findViewById(R.id.etDescripcion);
        localizacion = (EditText) view.findViewById(R.id.etLocation);
        agregar = (Button) view.findViewById(R.id.addSessionButton);

        ArrayAdapter<CharSequence> adapmes = ArrayAdapter.createFromResource(getContext(), R.array.meses,android.R.layout.simple_spinner_item);
        mes.setAdapter(adapmes); //Llenar Spinner de mes

        ArrayAdapter<CharSequence> adaphora = ArrayAdapter.createFromResource(getContext(), R.array.horas,android.R.layout.simple_spinner_item);
        hora.setAdapter(adaphora);//Llenar Spinner de horas

        agregar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                createSession();
            }
        });
    }

    public void createSession() {


        Calendar cal = Calendar.getInstance();


        boolean val = false; //Controlador del ciclo while
        Intent intent = null;
        while (val == false) {

            try {
                cal.set(Calendar.YEAR, Integer.parseInt(año.getText().toString()));                 //
                cal.set(Calendar.MONTH, (conversionData(mes.getSelectedItem().toString())));   // Set de AÑO MES y Dia
                cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dia.getText().toString()));       //

                cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt((hora.getSelectedItem().toString())));// Set de HORA y MINUTO
                cal.set(Calendar.MINUTE, Integer.parseInt(minuto.getText().toString()));            //

                intent = new Intent(Intent.ACTION_EDIT);
                intent.setType("vnd.android.cursor.item/event");

                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, cal.getTimeInMillis());
                intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, cal.getTimeInMillis() + 60 * 60 * 1000);

                intent.putExtra(CalendarContract.Events.ALL_DAY, duracion.isSelected());
                intent.putExtra(CalendarContract.Events.TITLE, titulo.getText().toString());
                intent.putExtra(CalendarContract.Events.DESCRIPTION, descripcion.getText().toString());
                intent.putExtra(CalendarContract.Events.EVENT_LOCATION, localizacion.getText().toString());

                startActivity(intent);
                val = true;
            } catch (Exception e) {
                año.setText("");
                dia.setText("");
                Toast.makeText(getActivity().getApplicationContext(), "Fecha Inválida", Toast.LENGTH_LONG).show();
            }
        }

    }

    private int conversionData(String data){
        switch (data) {
            case "enero":
                return 0;
            case "febrero":
                return 1;
            case "marzo":
                return 2;
            case "abril":
                return 3;
            case "mayo":
                return 4;
            case "junio":
                return 5;
            case "julio":
                return 6;
            case "agosto":
                return 7;
            case "septiembre":
                return 8;
            case "octubre":
                return 9;
            case "noviembre":
                return 10;
            case "Diciembre":
                return 11;

        }
    return -1;
    }
}