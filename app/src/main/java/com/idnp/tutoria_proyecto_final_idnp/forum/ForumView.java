package com.idnp.tutoria_proyecto_final_idnp.forum;

import androidx.appcompat.app.AppCompatActivity;

import com.idnp.tutoria_proyecto_final_idnp.ForumSQLiteOpenHelper;
import com.idnp.tutoria_proyecto_final_idnp.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ForumView extends AppCompatActivity implements Forum.View {
    private TextView tvSesion;
    private ListView ltSession;
    private EditText etComment;
    private Button sendButton;
    private Forum.Presenter presenter;
    SharedPreferences session;
    private ArrayList<Comment> comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
        tvSesion = (TextView) findViewById(R.id.usernameForum);
        ltSession = (ListView) findViewById(R.id.listComments);
        etComment =  (EditText) findViewById(R.id.addComment);
        sendButton = (Button) findViewById(R.id.sendButton);
        presenter = new ForumPresenter(this);
        session = getSharedPreferences("session", Context.MODE_PRIVATE);
        presenter.chargePreferences(session);
        final ForumSQLiteOpenHelper bd = new ForumSQLiteOpenHelper(this);
        comments = bd.selectComments();
        ArrayList<String> comms = new ArrayList<>();
        for(int i=0;i<comments.size();i++){
            comms.add(commentFormat(comments.get(i)));
        }
        listViewArray(comms);
        //ltSession.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, comms));
        //ltSession.setSelection(ltSession.getAdapter().getCount()-1);
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
                Comment cm = new Comment(user, comment);
                bd.insertComment(cm);
                comms.add(commentFormat(cm));
                listViewArray(comms);
                etComment.setText("");
            }
        });
    }

    private void listViewArray(ArrayList<String> comms){
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, comms){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                // Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                // Initialize a TextView for ListView each Item
                TextView tv = (TextView) view.findViewById(android.R.id.text1);

                // Set the text color of TextView (ListView Item)
                tv.setTextColor(Color.rgb(11,10,10));

                // Generate ListView Item using TextView
                return view;
            }
        };
        ltSession.setAdapter(arrayAdapter);
        ltSession.setSelection(ltSession.getAdapter().getCount()-1);
    }

    private String commentFormat(Comment comment){
        return comment.getUser()+": "+comment.getComment();
    }

    /*public void comment(View view){
        comments.add(comment.getText().toString());
        ltSession.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, comments));

    }*/

    @Override
    public void showForum(String nombre) {
        tvSesion.setText(nombre);
    }

}