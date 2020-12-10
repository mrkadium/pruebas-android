package com.uso.pruebas;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class FilesActivity extends AppCompatActivity {
    private final int REQUEST_CODE_PERMISO_ESCRITURA_EXTERNA = 100;

    private EditText edittextHashtags;
    private Button buttonSave;

    private TipoAlmacenamiento tipoAlmacenamiento;
    private String ubicacionArchivos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files);

        edittextHashtags = findViewById(R.id.edittextHashtags);
        buttonSave = findViewById(R.id.buttonSave);
        tipoAlmacenamiento = TipoAlmacenamiento.INTERNO;

        validatePermissions();

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(saveConfigFile()){
                    Toast.makeText(getBaseContext(), "Configuración guardada", Toast.LENGTH_SHORT).show();
                    finish();
                } else Toast.makeText(getBaseContext(), "Error al guardar configuración", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validatePermissions() {
        int permisoLectura = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if(permisoLectura != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE_PERMISO_ESCRITURA_EXTERNA);
        }else{
            Toast.makeText(this, "Permisos Obtorgados", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean saveConfigFile() {
        boolean isSaved = false;
        try{
            if(setLocation()){
                FileOutputStream file;
                if(tipoAlmacenamiento == TipoAlmacenamiento.INTERNO) {
                    file = openFileOutput("configuration.txt", MODE_APPEND);
                } else{
                    File location = new File(ubicacionArchivos + "/configuration.txt");
                    file = new FileOutputStream(location, true);
                }
                OutputStreamWriter writer = new OutputStreamWriter(file);
                writer.write(edittextHashtags.getText().toString());
                writer.flush();
                writer.close();
                isSaved = true;
            }
        }catch (Exception ex){
            Log.d("", ex.getMessage());
        }
        return isSaved;
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
}