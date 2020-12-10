package com.uso.pruebas.roomdb;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.uso.pruebas.daos.ContactDao;
import com.uso.pruebas.models.Contact;

@Database(entities = {Contact.class}, version = 1)
public abstract class ContactRDB extends RoomDatabase {

    public abstract ContactDao contactDao();

    private static volatile ContactRDB INSTANCE;

    static ContactRDB getInstance(Context context){

        if(INSTANCE == null){
            synchronized (ContactRDB.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            ContactRDB.class,
                            "Enterprise").build();
                }
            }
        }

        return INSTANCE;
    }

}
