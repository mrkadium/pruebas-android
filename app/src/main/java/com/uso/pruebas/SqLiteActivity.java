package com.uso.pruebas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.uso.pruebas.database.DatabaseHelper;
import com.uso.pruebas.models.User;

import java.util.ArrayList;
import java.util.List;

public class SqLiteActivity extends AppCompatActivity {
    EditText etName;
    EditText etAge;
    Button btnAdd;
    Button btnViewAll;
    Button btnUpdate;
    ListView lvUsers;
    List<User> list;
    ArrayAdapter<User> adapter;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sq_lite);

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        btnAdd = findViewById(R.id.btnAdd);
        btnViewAll = findViewById(R.id.btnViewAll);
        btnUpdate = findViewById(R.id.btnUpdate);
        lvUsers = findViewById(R.id.lvUsers);

        helper = new DatabaseHelper(getBaseContext());

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        lvUsers.setAdapter(adapter);

        lvUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                User user = (User) adapterView.getItemAtPosition(i);
                if(!helper.deleteOne(user)) Toast.makeText(getBaseContext(), "Deleted successfully", Toast.LENGTH_SHORT).show();
                else Toast.makeText(getBaseContext(), "Something went wrong...", Toast.LENGTH_SHORT).show();
                refreshList();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(etName.getText().toString(), Integer.parseInt(etAge.getText().toString()));
                if(helper.addOne(user)) {
                    refreshList();
                    Toast.makeText(getBaseContext(), "Added successfully", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(getBaseContext(), "Something went wrong...", Toast.LENGTH_SHORT).show();
            }
        });

        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshList();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(1, "Jennifer", 24);
                if(helper.updateOne(user)) {
                    refreshList();
                    Toast.makeText(getBaseContext(), "Updated successfully", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(getBaseContext(), "Something went wrong...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void refreshList(){
        list = helper.selectAll();
        adapter.clear();
        adapter.addAll(list);
    }
}