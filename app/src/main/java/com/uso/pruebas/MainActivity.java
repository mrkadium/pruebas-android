package com.uso.pruebas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

enum TipoAlmacenamiento{
    INTERNO,
    EXTERNO
}

public class MainActivity extends AppCompatActivity {
    private final int REQUEST_CODE_PERMISO_ESCRITURA_EXTERNA = 100;

    private TextView textviewPreferencias;

    private TipoAlmacenamiento tipoAlmacenamiento;
    private String ubicacionArchivos;

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textviewPreferencias = findViewById(R.id.textviewPreferencias);
        tipoAlmacenamiento = TipoAlmacenamiento.INTERNO;

        drawer = findViewById(R.id.drawerLayout);
        toggle = new ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadConfigFile();
    }

    public void openPerfil(View view){
        Intent intent = new Intent(this, PerfilActivity.class);
        startActivity(intent);
    }

    public void openPerfilDos(View view){
        Intent intent = new Intent(this, PerfilDosActivity.class);
        startActivity(intent);
    }

    public void openExpandableList(View view){
        Intent intent = new Intent(this, ExpandableListActivity.class);
        startActivity(intent);
    }

    public void openDateTimePicker(View view){
        Intent intent = new Intent(this, DateTimePickerActivity.class);
        startActivity(intent);
    }

    public void openSqlLite(View view){
        Intent intent = new Intent(this, SqLiteActivity.class);
        startActivity(intent);
    }

    public void openFiles(View view){
        Intent intent = new Intent(this, FilesActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item)) return true;
        return super.onOptionsItemSelected(item);
    }

    private boolean setLocation() {
        boolean isSet = false;
        if(tipoAlmacenamiento != null){
            if(tipoAlmacenamiento == TipoAlmacenamiento.INTERNO){
                ubicacionArchivos = "";
                isSet = true;
            }else{
                if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                    File ubicacion = Environment.getExternalStorageDirectory();
                    File carpeta = new File(ubicacion.getAbsolutePath() + "/app_pruebas");
                    if(!carpeta.isDirectory()){
                        if(carpeta.mkdir()) Toast.makeText(getBaseContext(), "", Toast.LENGTH_SHORT).show();
                    }
                    ubicacionArchivos = carpeta.getAbsolutePath();
                    isSet = true;
                }
            }
        }
        return isSet;
    }

    private void loadConfigFile() {
        try{
            if(setLocation()){
                FileInputStream file;
                if(tipoAlmacenamiento == TipoAlmacenamiento.INTERNO) file = openFileInput("configuration.txt");
                else{
                    File location = new File(ubicacionArchivos + "/configuration.txt");
                    file = new FileInputStream(location);
                }
                InputStreamReader reader = new InputStreamReader(file);
                char buffer[] = new char[100];
                int numChar = 0;
                String result = "";

                while((numChar = reader.read(buffer)) > 0){
                    String line = String.copyValueOf(buffer);
                    result += line;
                    buffer = new char[100];
                }

                if(!result.isEmpty()) textviewPreferencias.setText(result);
            }
        }catch (Exception ex){
            Log.d("", ex.getMessage());
        }
    }
}