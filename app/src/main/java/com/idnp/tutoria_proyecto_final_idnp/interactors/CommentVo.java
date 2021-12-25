package com.idnp.tutoria_proyecto_final_idnp.interactors;

public class CommentVo {
    String user;
    String comment;

    public CommentVo(){}

    public CommentVo(String user, String comment){
        this.user = user;
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


}
