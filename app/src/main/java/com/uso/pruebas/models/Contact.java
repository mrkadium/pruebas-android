package com.uso.pruebas.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contacts")
public class Contact {
    @PrimaryKey(autoGenerate = true)
    private int contact_id;
    private String contact_name;
    private String contact_value;
    private String contact_type;

    public Contact(String contact_name, String contact_value, String contact_type) {
        this.contact_name = contact_name;
        this.contact_value = contact_value;
        this.contact_type = contact_type;
    }

    public int getContact_id() {
        return contact_id;
    }

    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_value() {
        return contact_value;
    }

    public void setContact_value(String contact_value) {
        this.contact_value = contact_value;
    }

    public String getContact_type() {
        return contact_type;
    }

    public void setContact_type(String contact_type) {
        this.contact_type = contact_type;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contact_id=" + contact_id +
                ", contact_name='" + contact_name + '\'' +
                ", contact_value='" + contact_value + '\'' +
                ", contact_type='" + contact_type + '\'' +
                '}';
    }
}
