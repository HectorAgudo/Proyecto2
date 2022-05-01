package com.example.dydproyect.vistas.jugador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dydproyect.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class JugadorSalvaciones extends AppCompatActivity {

    Button btnVida, btnAtributos, btnHabilidades, btnNombre;
    TextView salFue, salDes,salCon, salInt, salSab, salCar;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salvaciones);

        inicializarFirebase();

        salFue = findViewById(R.id.textViewFuerSal);
        salDes = findViewById(R.id.textViewDesSal);
        salCon = findViewById(R.id.textViewConSal);
        salInt = findViewById(R.id.textViewIntSal);
        salSab = findViewById(R.id.textViewSabSal);
        salCar = findViewById(R.id.textViewCarSal);

        btnVida = findViewById(R.id.buttonVidaSal);
        btnAtributos = findViewById(R.id.buttonAtriSal);
        btnHabilidades = findViewById(R.id.buttonHabiSal);
        btnNombre = findViewById(R.id.buttonNomSal);
        listarSalvaciones();

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
        btnNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irNombre();
            }
        });
    }

    private void irAtributos(){
        Intent intent = new Intent(this, JugadorAtributos.class);
        startActivity(intent);
    }
    private void irVida(){
        Intent intent = new Intent(this, JugadorVida.class);
        startActivity(intent);
    }
    private void irNombre(){
        Intent intent = new Intent(this, JugadorNombre.class);
        startActivity(intent);
    }
    private void irHabilidades(){
        Intent intent = new Intent(this, JugadorHabilidades.class);
        startActivity(intent);
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void listarSalvaciones(){
        databaseReference.child("Personaje").child("Atributos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                salFue.setText(JugadorAtributos.calcularModificador(Integer.parseInt(snapshot.child("fuerza").getValue().toString())));
                salDes.setText(JugadorAtributos.calcularModificador(Integer.parseInt(snapshot.child("destreza").getValue().toString())));
                salCon.setText(JugadorAtributos.calcularModificador(Integer.parseInt(snapshot.child("constitucion").getValue().toString())));
                salInt.setText(JugadorAtributos.calcularModificador(Integer.parseInt(snapshot.child("inteligencia").getValue().toString())));
                salSab.setText(JugadorAtributos.calcularModificador(Integer.parseInt(snapshot.child("sabiduria").getValue().toString())));
                salCar.setText(JugadorAtributos.calcularModificador(Integer.parseInt(snapshot.child("carisma").getValue().toString())));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }




}
