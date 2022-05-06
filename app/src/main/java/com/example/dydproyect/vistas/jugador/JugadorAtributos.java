package com.example.dydproyect.vistas.jugador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        listarModificadores();

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
                int fuerza = Integer.parseInt(editFuerza.getText().toString().trim());
                int destreza = Integer.parseInt(editDestreza.getText().toString().trim());
                int constitucion = Integer.parseInt(editConstitucion.getText().toString().trim());
                int inteligencia = Integer.parseInt(editInteligencia.getText().toString().trim());
                int sabiduria = Integer.parseInt(editSabiduria.getText().toString().trim());
                int carisma = Integer.parseInt(editCarisma.getText().toString().trim());
                HashMap map = new HashMap();
                map.put("fuerza",fuerza);
                map.put("destreza",destreza);
                map.put("constitucion",constitucion);
                map.put("inteligencia",inteligencia);
                map.put("sabiduria",sabiduria);
                map.put("carisma",carisma);
                databaseReference.child("Personaje").child("Atributos").updateChildren(map);

            }
        });

    }



    private void listarModificadores(){
        databaseReference.child("Personaje").child("Atributos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    int modFue = calcularModificador(Integer.parseInt(snapshot.child("fuerza").getValue().toString()));
                    editFuerza.setText(snapshot.child("fuerza").getValue().toString());
                    modFuerza.setText("+" + modFue);
                    int modDes = calcularModificador(Integer.parseInt(snapshot.child("destreza").getValue().toString()));
                    editDestreza.setText(snapshot.child("destreza").getValue().toString());
                    modDestreza.setText("+" + modDes);
                    int modCon = calcularModificador(Integer.parseInt(snapshot.child("constitucion").getValue().toString()));
                    editConstitucion.setText(snapshot.child("constitucion").getValue().toString());
                    modConstitucion.setText("+" + modCon);
                    int modInt = calcularModificador(Integer.parseInt(snapshot.child("inteligencia").getValue().toString()));
                    editInteligencia.setText(snapshot.child("inteligencia").getValue().toString());
                    modInteligencia.setText("+" + modInt);
                    int modSab = calcularModificador(Integer.parseInt(snapshot.child("sabiduria").getValue().toString()));
                    editSabiduria.setText(snapshot.child("sabiduria").getValue().toString());
                    modSabiduria.setText("+" + modSab);
                    int modCar = calcularModificador(Integer.parseInt(snapshot.child("carisma").getValue().toString()));
                    editCarisma.setText(snapshot.child("carisma").getValue().toString());
                    modCarisma.setText("+" + modCar);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public static int calcularModificador(int valor){
        int modificador = 0;
        if(valor == 1){
            modificador = -5;
        }else if (valor == 2 || valor ==3 ){
            modificador = -4;
        }else if (valor == 4 || valor ==5 ){
            modificador = -3;
        }else if (valor == 6 || valor ==7 ){
            modificador = -2;
        }else if (valor == 8 || valor ==9 ){
            modificador = -1;
        }else if (valor == 10 || valor ==11 ){
            modificador = 0;
        }else if (valor == 12 || valor ==13 ){
            modificador = 1;
        }else if (valor == 14 || valor ==15 ){
            modificador = 2;
        }else if (valor == 16 || valor ==17 ){
            modificador = 3;
        }else if (valor == 18 || valor ==19 ){
            modificador = 4;
        }else if (valor == 20 || valor ==21){
            modificador = 5;
        }else if (valor == 22 || valor ==23){
            modificador = 6;
        }
        return modificador;
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
