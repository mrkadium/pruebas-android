package com.uso.pruebas.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.uso.pruebas.models.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String USERS = "users";
    public static final String COL_USER_ID = "id";
    public static final String COL_USER_NAME = "name";
    public static final String COL_USER_AGE = "age";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "user.db", null, 1);
    }

    //this is called the first time you try to access the db object
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable =
                "CREATE TABLE " + USERS + "(" +
                COL_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_USER_NAME + " TEXT," +
                COL_USER_AGE + " INTEGER" +
                ");";
        sqLiteDatabase.execSQL(createTable);
    }

    //this is called when an update to the database has happened and there's users that need to upgrade
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String createTable =
                "CREATE TABLE " + USERS + "(" +
                        COL_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COL_USER_NAME + " TEXT," +
                        COL_USER_AGE + " INTEGER" +
                        ");";
        sqLiteDatabase.execSQL(createTable);
    }

    public boolean addOne(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues(); //hash map, pairs of values, key-value
        //adding the values of the new row
        cv.put(COL_USER_NAME, user.getName());
        cv.put(COL_USER_AGE, user.getAge());

        long insert = db.insert(USERS, null, cv);

        db.close();

        return insert != -1;
    }

    public List<User> selectAll(){
        List<User> listUsers = new ArrayList<>();

        String sql = "SELECT * FROM "+USERS;
        SQLiteDatabase db = this.getReadableDatabase(); //open
        Cursor cursor = db.rawQuery(sql, null); //result set

        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int age = cursor.getInt(2);

                User user = new User(id, name, age);
                listUsers.add(user);
            }while(cursor.moveToNext());
        }

        //close connection to database
        cursor.close();
        db.close();
        return listUsers;
    }

    public boolean updateOne(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COL_USER_NAME, user.getName());
        cv.put(COL_USER_AGE, user.getAge());

        String whereClause = "id='"+user.getId()+"'";

        long update = db.update(USERS, cv, whereClause, null);
        db.close();
        return update != -1;
    }

    public boolean deleteOne(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM "+USERS+" WHERE id='"+user.getId()+"'";
        Cursor cursor = db.rawQuery(sql, null);

        return cursor.moveToFirst();
    }
}
