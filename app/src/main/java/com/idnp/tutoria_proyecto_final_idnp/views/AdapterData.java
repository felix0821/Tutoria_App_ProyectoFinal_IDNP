package com.idnp.tutoria_proyecto_final_idnp.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.idnp.tutoria_proyecto_final_idnp.R;
import com.idnp.tutoria_proyecto_final_idnp.interactors.CommentVo;

import java.util.ArrayList;

public class AdapterData extends RecyclerView.Adapter<AdapterData.ViewHolderData> {

    ArrayList<CommentVo> listData;

    public AdapterData(ArrayList<CommentVo> listData) {
        this.listData = listData;
    }

    @NonNull
    @Override
    public ViewHolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comment,null,false);
        return new ViewHolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderData holder, int position) {
        holder.assignData(listData.get(position).getUser(),listData.get(position).getComment());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolderData extends RecyclerView.ViewHolder {
        TextView comment,username;

        public ViewHolderData(@NonNull View itemView) {
            super(itemView);
            comment = (TextView) itemView.findViewById(R.id.idComment);
            username = (TextView) itemView.findViewById(R.id.idUsername);
        }

        public void assignData(String username, String comment) {
            this.comment.setText(comment);
            this.username.setText(username);
        }
    }
}
