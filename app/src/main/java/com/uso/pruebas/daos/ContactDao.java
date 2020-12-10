package com.uso.pruebas.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.uso.pruebas.models.Contact;

import java.util.List;

@Dao
public interface ContactDao {
    @Query("SELECT * FROM contacts")
    List<Contact> getAll();

    @Query("SELECT * FROM contacts WHERE contact_id IN (:contactIds)")
    List<Contact> loadAllByIds(int[] contactIds);

    @Query("SELECT * FROM contacts WHERE contact_value LIKE :value OR " +
            "contact_name LIKE :name")
    List<Contact> findByName(String value, String name);

    @Insert
    void insertAll(Contact... contacts);

    @Update
    void update(Contact contact);

    @Delete
    void delete(Contact contact);


}
