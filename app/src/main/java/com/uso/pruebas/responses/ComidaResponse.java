package com.uso.pruebas.responses;

import com.uso.pruebas.models.Comida;

import java.util.ArrayList;

public class ComidaResponse {
    private ArrayList<Comida> data;

    public ArrayList<Comida> getData() {
        return data;
    }

    public void setData(ArrayList<Comida> data) {
        this.data = data;
    }
}
