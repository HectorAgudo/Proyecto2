package com.example.dydproyect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import com.example.dydproyect.vistas.jugador.JugadorNombre;

public class MainActivity extends AppCompatActivity {

    Button btnJugador, btnMaster;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnJugador= findViewById(R.id.buttonJugador);

        btnJugador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irJugadorNombre();

            }
        });





    }

    public void irJugadorNombre(){
        Intent intent = new Intent(this, JugadorNombre.class);
        startActivity(intent);

    }


}