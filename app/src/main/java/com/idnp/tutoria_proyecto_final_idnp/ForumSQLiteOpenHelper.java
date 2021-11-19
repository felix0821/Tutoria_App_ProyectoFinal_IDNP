package com.idnp.tutoria_proyecto_final_idnp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;

import com.idnp.tutoria_proyecto_final_idnp.forum.Comment;

import java.util.ArrayList;

public class ForumSQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="bdforo";
    private static final int SHEME_VERSION=1;
    private SQLiteDatabase db;

    public ForumSQLiteOpenHelper(@Nullable Context context){
        super(context, DB_NAME, null, SHEME_VERSION);
        db=this.getReadableDatabase();
    }

    interface Tables{
        String BD_COMMENT = "comment";
        String BD_USER = "user";
    }
    interface references{
        String EMAIL_USER=String.format("REFERENCES %s(%s)",Tables.BD_USER);
    }
    public void onOpen(SQLiteDatabase db){
        super.onOpen(db);
        if(db.isReadOnly()){
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN){
                db.setForeignKeyConstraintsEnabled(true);
            } else {
                db.execSQL("PRAGMA foreign_keys=ON");
            }
        }
    }
    public void insertComment(Comment cm){
        db.insert(Tables.BD_COMMENT, null, generateValuesComment(cm));
    }

    private ContentValues generateValuesComment(Comment comment){
        ContentValues values = new ContentValues();
        values.put(BDApp.ColumnComment.USER, comment.getUser());
        values.put(BDApp.ColumnComment.COMMENT, comment.getComment());
        return values;
    }

    public ArrayList<Comment> selectComments(){
        ArrayList<Comment> cmms = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s", Tables.BD_COMMENT);
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor == null){
            System.out.println("Esta Vacio");
        }
        if(cursor.moveToFirst()){
            do{
                Comment cm = new Comment();
                cm.setUser(cursor.getString(1));
                cm.setComment(cursor.getString(2));
                cmms.add(cm);
            }while (cursor.moveToNext());
        }
        return cmms;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table comment(id int primary key, username text, comment text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
