package com.birzavit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText edtdni, edtcelular;
    Button btnaceptar, btnsalir;


    private MediaPlayer mp = null;

    void Controles(){
        edtdni = findViewById(R.id.edtDni);
        edtcelular = findViewById(R.id.edtCelular);
        btnsalir = findViewById(R.id.btnSalir);
        btnaceptar = findViewById(R.id.btnAceptar);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mp = MediaPlayer.create(this, R.raw.login);
        if (mp != null) {
            mp.start();
        }

        Controles();

        btnsalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnaceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validar();
            }
        });

    }

    private void validar() {
        String dni = edtdni.getText().toString();
        String cel = edtcelular.getText().toString();

        if (dni.equals("40404040") && cel.equals("987654321") || dni.equals("20202020")&& cel.equals("998877665")){
            Intent i = new Intent(Login.this, Menu.class);
            i.putExtra("DNI", dni);
            i.putExtra("CEL", cel);
            startActivity(i);
        } else {
            Toast.makeText(this, "ERROR, EL DNI Y/O CELULAR NO REGISTRADOS \n"+ dni, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mp != null) {
            mp.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mp != null) {
            mp.release();
            mp = null;
        }
    }



}