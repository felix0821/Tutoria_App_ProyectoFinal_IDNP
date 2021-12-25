package com.idnp.tutoria_proyecto_final_idnp.views;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.idnp.tutoria_proyecto_final_idnp.ForumSQLiteOpenHelper;
import com.idnp.tutoria_proyecto_final_idnp.R;
import com.idnp.tutoria_proyecto_final_idnp.interactors.CommentVo;
import com.idnp.tutoria_proyecto_final_idnp.interfaces.Forum;
import com.idnp.tutoria_proyecto_final_idnp.presenters.ForumPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ForumFragmentView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ForumFragmentView extends Fragment implements Forum.View{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView tvSesion;
    private EditText etComment;
    private Button sendButton;
    private Forum.Presenter presenter;
    SharedPreferences session;
    private ArrayList<CommentVo> comments;
    private List<DocumentSnapshot> foro;
    private RecyclerView recycler;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public ForumFragmentView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ForumFragmentView.
     */
    // TODO: Rename and change types and number of parameters
    public static ForumFragmentView newInstance(String param1, String param2) {
        ForumFragmentView fragment = new ForumFragmentView();
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
        return inflater.inflate(R.layout.fragment_forum_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        tvSesion = (TextView) view.findViewById(R.id.usernameForum);
        etComment =  (EditText) view.findViewById(R.id.addComment);
        sendButton = (Button) view.findViewById(R.id.sendButton);
        presenter = new ForumPresenter(this);
        session = getContext().getSharedPreferences("session", Context.MODE_PRIVATE);
        presenter.chargePreferences(session);

        /*db.collection("forums").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        foro = queryDocumentSnapshots.getDocuments();
                        ArrayList<String> comms = new ArrayList<>();
                        for(int i = 0; i<foro.size(); i++)
                        {
                            comms.add(foro.get(i).)
                        }


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
                });*/


        final ForumSQLiteOpenHelper bd = new ForumSQLiteOpenHelper(getContext());
        recycler.setLayoutManager(
                new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        comments = bd.selectComments();
        AdapterData adapter = new AdapterData(comments);
        recycler.setAdapter(adapter);

        // Agregar listener al botón
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Quitamos los errores
                etComment.setError(null);
                String comment = etComment.getText().toString();
                // Vemos si está vacío...
                if ("".equals(comment)) {
                    // Primer error
                    etComment.setError("Introduce al menos una letra");
                    // Le damos focus
                    etComment.requestFocus();
                    // Y terminamos la ejecución
                    return;
                }
                String user = tvSesion.getText().toString();
                CommentVo cm = new CommentVo(user, comment);
                bd.insertComment(cm);
                comments.add(cm);
                AdapterData adapter = new AdapterData(comments);
                recycler.setAdapter(adapter);
                etComment.setText("");
            }
        });
    }


    private String commentFormat(CommentVo comment){
        return comment.getUser()+": "+comment.getComment();
    }

    @Override
    public void showForum(String nombre) {
        tvSesion.setText(nombre);
    }
}