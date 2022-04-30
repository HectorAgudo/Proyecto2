package com.example.dydproyect.vistas.jugador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dydproyect.R;
import com.example.dydproyect.entidades.Personaje;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class JugadorAtributos  extends AppCompatActivity {
    Button btnActualizar, btnVida, btnNombre, btnHabilidades, btnSalvaciones;
    EditText editFuerza, editDestreza, editConstitucion, editInteligencia, editSabiduria, editCarisma;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    TextView modFuerza, modDestreza, modConstitucion, modInteligencia, modSabiduria, modCarisma;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atributos);

        inicializarFirebase();

        btnVida = findViewById(R.id.buttonVidaAtributos);
        btnNombre = findViewById(R.id.buttonNombreAtributos);
        btnHabilidades = findViewById(R.id.buttonHabiliAtributos);
        btnSalvaciones = findViewById(R.id.buttonSalvaAtributos);
        btnActualizar = findViewById(R.id.buttonActualizarAtributos);

        editFuerza = findViewById(R.id.editTextFueValor);
        editDestreza = findViewById(R.id.editTextDesValor);
        editConstitucion = findViewById(R.id.editTextConValor);
        editInteligencia = findViewById(R.id.editTextInteValor);
        editSabiduria = findViewById(R.id.editTextSabValor);
        editCarisma = findViewById(R.id.editTextCarValor);
        modFuerza = findViewById(R.id.textViewFueAtri);
        modDestreza = findViewById(R.id.textViewDesAtri);
        modConstitucion = findViewById(R.id.textViewConAtri);
        modInteligencia = findViewById(R.id.textViewIntAtri);
        modSabiduria = findViewById(R.id.textViewSabAtri);
        modCarisma = findViewById(R.id.textViewCarAtri);

        btnVida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irVida();
            }
        });
        btnNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irNombre();
            }
        });
        btnHabilidades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irHabilidades();
            }
        });
        btnSalvaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irSalvaciones();
            }
        });
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseReference.child("Personaje").updateChildren("fuerza",editFuerza.getText().toString());
            }
        });

    }
    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void irNombre(){
        Intent intent = new Intent(this, JugadorNombre.class);
        startActivity(intent);
    }
    private void irVida(){
        Intent intent = new Intent(this, JugadorVida.class);
        startActivity(intent);
    }
    private void irSalvaciones(){
        Intent intent = new Intent(this, JugadorSalvaciones.class);
        startActivity(intent);
    }
    private void irHabilidades(){
        Intent intent = new Intent(this, JugadorHabilidades.class);
        startActivity(intent);
    }





}
