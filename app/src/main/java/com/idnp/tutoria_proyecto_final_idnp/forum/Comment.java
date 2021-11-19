package com.idnp.tutoria_proyecto_final_idnp.forum;

public class Comment {
    String user;
    String comment;

    public Comment(){}

    public Comment(String user, String comment){
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
