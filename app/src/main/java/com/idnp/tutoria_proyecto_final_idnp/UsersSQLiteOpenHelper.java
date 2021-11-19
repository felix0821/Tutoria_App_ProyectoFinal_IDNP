package com.idnp.tutoria_proyecto_final_idnp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UsersSQLiteOpenHelper extends SQLiteOpenHelper {

    public UsersSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table users(id int primary key, username text, email text, name text, paternalSurname text, maternalSurname text," +
                "password text, teachingArea1 int, teachingArea2 int, teachingArea3 int, latitud double, longitud double, address text, schedule text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
