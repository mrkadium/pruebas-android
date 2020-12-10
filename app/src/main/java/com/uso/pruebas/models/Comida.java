package com.uso.pruebas.models;

import com.google.gson.annotations.SerializedName;

public class Comida {
    @SerializedName("_id")
    private String _id;
    @SerializedName("menu")
    private String menu;
    @SerializedName("precio")
    private Double precio;
    @SerializedName("__v")
    private int __v;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Comida{" +
                "idcomida=" + _id +
                ", menu='" + menu + '\'' +
                ", precio=" + precio +
                '}';
    }
}
