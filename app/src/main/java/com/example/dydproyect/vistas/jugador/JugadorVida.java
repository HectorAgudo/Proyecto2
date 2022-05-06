package com.example.dydproyect.vistas.jugador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
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

import java.util.HashMap;

public class JugadorVida extends AppCompatActivity {

    Button btnActualizar, btnNombre, btnAtributos, btnHabilidades, btnSalvaciones;
    EditText editVidaMax, editVidaActual, editCA, editVelocidad;
    TextView textIniciativa, textBonCom;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Switch switchInspiracion;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vida);

        inicializarFirebase();

        btnNombre = findViewById(R.id.buttonNombreVida);
        btnAtributos = findViewById(R.id.buttonAtriVida);
        btnHabilidades = findViewById(R.id.buttonHabilVida);
        btnSalvaciones = findViewById(R.id.buttonSalvaVida);
        btnActualizar = findViewById(R.id.buttonActuVida);

        editVidaMax = findViewById(R.id.editTextVidaMax);
        editVidaActual = findViewById(R.id.editTextVidaActual);
        editCA = findViewById(R.id.editTextCA);
        editVelocidad = findViewById(R.id.editTextVelocidad);

        textIniciativa = findViewById(R.id.textViewIniciativa);
        textBonCom = findViewById(R.id.textViewBonCom);

        switchInspiracion = findViewById(R.id.switchInspiracion);

        listarVida();

        btnNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irNombre();
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
                int vidaMax = Integer.parseInt((editVidaMax.getText().toString()));
                int vidaActual = Integer.parseInt((editVidaActual.getText().toString()));
                int claseArmadura = Integer.parseInt((editCA.getText().toString()));
                int velocidad = Integer.parseInt((editVelocidad.getText().toString()));
                //boolean inspiracion = switchInspiracion
                HashMap map = new HashMap();
                map.put("vidaMax", vidaMax);
                map.put("vidaaActu", vidaActual);
                map.put("CA", claseArmadura);
                map.put("velocidad", velocidad);
                databaseReference.child("Personaje").child("vida").updateChildren(map);
            }
        });

    }

    private void irAtributos(){
        Intent intent = new Intent(this, JugadorAtributos.class);
        startActivity(intent);
    }
    private void irNombre(){
        Intent intent = new Intent(this, JugadorNombre.class);
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

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public static int calcularBonCom(int nivel){
        int bonCom = 0;
        if (nivel >=1 && nivel <=4){
            bonCom = 2;
        }else if(nivel >=5 && nivel <=8){
            bonCom = 3;
        }else if(nivel >=9 && nivel <=12){
            bonCom = 4;
        }else if(nivel >=13 && nivel <=16){
            bonCom = 5;
        }else if(nivel >=17 && nivel <=20){
            bonCom = 6;
        }
        return bonCom;
    }

    private void listarVida(){
        databaseReference.child("Personaje").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    int bonCom = calcularBonCom(Integer.parseInt(snapshot.child("nivel").getValue().toString()));
                    textBonCom.setText("+"+bonCom);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference.child("Personaje").child("Atributos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    int iniciativaValor = JugadorAtributos.calcularModificador(Integer.parseInt(snapshot.child("destreza").getValue().toString()));
                    textIniciativa.setText(iniciativaValor+"'");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}
