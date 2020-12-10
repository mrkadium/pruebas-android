package com.uso.pruebas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.uso.pruebas.models.Comida;
import com.uso.pruebas.responses.ComidaResponse;
import com.uso.pruebas.responses.ComidasService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactosFragment extends Fragment {
    private ListView listviewContactos;
    private List<String> listContactos = new ArrayList<>();
    private ArrayList<Comida> listaComidas = new ArrayList<>();
    ArrayAdapter<Comida> adapter;
    private View view;
    private Retrofit retrofit;

    public ContactosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        retrofit = new Retrofit
                .Builder()
                .baseUrl("https://fathomless-bayou-92940.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    private void cargarComidas(){
        ComidasService service = retrofit.create(ComidasService.class);
        Call<List<Comida>> response = service.getComidas();

        response.enqueue(new Callback<List<Comida>>() {
            @Override
            public void onResponse(Call<List<Comida>> call, Response<List<Comida>> response) {
                if(response.isSuccessful()){
                    Log.d("GETCOMIDAS-SUCCESS", "SUCCESS");
                    List<Comida> res = response.body();
                    listaComidas.addAll(res);
                    adapter.addAll(listaComidas);
                    listviewContactos.setAdapter(adapter);
                }else{
                    Log.d("GETCOMIDAS-NOT-SUCCESS", response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Comida>> call, Throwable t) {
                Log.w("GETCOMIDAS-FAILURE", t.toString());
            }
        });
    }

    private void cargarLista(){
        listContactos.add("Marito");
        listContactos.add("Marito");
        listContactos.add("Marito");
        listContactos.add("Marito");
        listContactos.add("Marito");
        listContactos.add("Marito");
        listContactos.add("Marito");
        listContactos.add("Marito");
        listContactos.add("Marito");
        listContactos.add("Marito");
        listContactos.add("Marito");
        listContactos.add("Marito");
        listContactos.add("Marito");
        listContactos.add("Marito");
        listContactos.add("Marito");
        listContactos.add("Marito");
        listContactos.add("Marito");
        listContactos.add("Marito");
        listContactos.add("Marito");
        listContactos.add("Marito");
        listContactos.add("Marito");
        listContactos.add("Marito");
        listContactos.add("Marito");
        listContactos.add("Marito");
        listContactos.add("Marito");
        listContactos.add("Marito");
        listContactos.add("Marito");
        listContactos.add("Marito");
        listContactos.add("Marito");
        listContactos.add("Marito");
        listContactos.add("Marito");
        listContactos.add("Marito");
        listContactos.add("Marito");
        listContactos.add("Marito");
        listContactos.add("Marito");
        listContactos.add("Marito");
        listContactos.add("Marito");
        listContactos.add("Marito");
        listContactos.add("Marito");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_contactos, container, false);
        listviewContactos = view.findViewById(R.id.listviewContactos);
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1);


        cargarComidas();

        return  view;
    }
}