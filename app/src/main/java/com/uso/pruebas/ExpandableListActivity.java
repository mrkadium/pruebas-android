package com.uso.pruebas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.uso.pruebas.adapters.ExpandableAdapter;
import com.uso.pruebas.models.User;

import java.util.ArrayList;
import java.util.List;

public class ExpandableListActivity extends AppCompatActivity {
    RecyclerView recycler;
    ExpandableAdapter adapter;
    List<User> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list);

        list.add(new User("Captain America", 23));
        list.add(new User("Iron Man", 23));
        list.add(new User("Dr. Strange", 23));
        list.add(new User("Spider-man", 23));
        list.add(new User("Hulk", 23));
        list.add(new User("Black Widow", 23));
        list.add(new User("Hawkeye", 23));
        list.add(new User("Thor", 23));

        recycler = findViewById(R.id.recyclerUser);
        adapter = new ExpandableAdapter(list);

        recycler.setAdapter(adapter);
        recycler.setHasFixedSize(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.accion_buscar);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}