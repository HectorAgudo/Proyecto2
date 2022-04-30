package com.example.dydproyect.vistas.jugador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dydproyect.R;
import com.example.dydproyect.entidades.Personaje;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class JugadorNombre extends AppCompatActivity {

    Button btnActualizar, btnVida, btnAtributos, btnHabilidades, btnSalvaciones;
    EditText editNombre, editPuntosExp;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nombre);



        editNombre = findViewById(R.id.editTextNombrePj);
        editPuntosExp = findViewById(R.id.editTextPExp);


        btnVida = findViewById(R.id.buttonVidaNom);
        btnAtributos = findViewById(R.id.buttonAtribnom);
        btnHabilidades = findViewById(R.id.buttonHabiNom);
        btnSalvaciones = findViewById(R.id.buttonSalvaNom);
        btnActualizar = findViewById(R.id.buttonActuNombre);

        inicializarFirebase();
        //deCero();
        listarPj();

        btnVida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irVida();
            }
        });
        btnAtributos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irAtributos();
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
                Personaje personaje = new Personaje(editNombre.getText().toString(),editPuntosExp.getText().toString());
                databaseReference.child("Personaje").setValue(personaje);
            }
        });



    }


    private void listarPj() {
        databaseReference.child("Personaje").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String nombre = snapshot.child("nombre").getValue().toString();
                    editNombre.setText(nombre);
                    String exp = snapshot.child("exp").getValue().toString();
                    editPuntosExp.setText(exp);

                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void irAtributos(){
        Intent intent = new Intent(this, JugadorAtributos.class);
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
