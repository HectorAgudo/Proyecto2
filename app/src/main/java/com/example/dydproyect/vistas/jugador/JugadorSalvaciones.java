package com.example.dydproyect.vistas.jugador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dydproyect.PopupDados;
import com.example.dydproyect.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class JugadorSalvaciones extends AppCompatActivity {

    Button btnVida, btnAtributos, btnHabilidades, btnNombre;
    TextView salFue, salDes,salCon, salInt, salSab, salCar, cliFue, cliDes, cliCon, cliInt, cliSab,cliCar;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;
    FirebaseUser user = mAuth.getInstance().getCurrentUser();
    String uid = user.getUid();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salvaciones);
        setTitle("Salvaciones");

        inicializarFirebase();
        cliFue = findViewById(R.id.textViewClicableFuerza);
        cliDes = findViewById(R.id.textViewClicableDestreza);
        cliCon = findViewById(R.id.textViewClicableConstitucion);
        cliInt = findViewById(R.id.textViewClicableInteligencia);
        cliSab = findViewById(R.id.textViewClicableSabiduria);
        cliCar = findViewById(R.id.textViewClicableCarisma);

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
        cliFue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(JugadorSalvaciones.this, PopupDados.class);
                intent.putExtra("mod", salFue.getText().toString());
                startActivity(intent);
            }
        });
        cliDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(JugadorSalvaciones.this, PopupDados.class);
                intent.putExtra("mod", salDes.getText().toString());
                startActivity(intent);
            }
        });
        cliCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(JugadorSalvaciones.this, PopupDados.class);
                intent.putExtra("mod", salCon.getText().toString());
                startActivity(intent);
            }
        });
        cliInt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(JugadorSalvaciones.this, PopupDados.class);
                intent.putExtra("mod", salInt.getText().toString());
                startActivity(intent);
            }
        });
        cliSab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(JugadorSalvaciones.this, PopupDados.class);
                intent.putExtra("mod", salSab.getText().toString());
                startActivity(intent);
            }
        });
        cliCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(JugadorSalvaciones.this, PopupDados.class);
                intent.putExtra("mod", salCar.getText().toString());
                startActivity(intent);
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
        databaseReference.child('"'+String.valueOf(uid)+'"').child("Personaje").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String claseElegida = snapshot.child("clase").getValue().toString();
                int bonCom = JugadorVida.calcularBonCom(Integer.parseInt(snapshot.child("nivel").getValue().toString()));
                int bonFuerza = JugadorAtributos.calcularModificador(Integer.parseInt(snapshot.child("Atributos").child("fuerza").getValue().toString()));
                int bonDestreza = JugadorAtributos.calcularModificador(Integer.parseInt(snapshot.child("Atributos").child("destreza").getValue().toString()));
                int bonConstitucion = JugadorAtributos.calcularModificador(Integer.parseInt(snapshot.child("Atributos").child("constitucion").getValue().toString()));
                int bonInteligencia = JugadorAtributos.calcularModificador(Integer.parseInt(snapshot.child("Atributos").child("inteligencia").getValue().toString()));
                int bonSabiduria = JugadorAtributos.calcularModificador(Integer.parseInt(snapshot.child("Atributos").child("sabiduria").getValue().toString()));
                int bonCarisma = JugadorAtributos.calcularModificador(Integer.parseInt(snapshot.child("Atributos").child("carisma").getValue().toString()));

                if(claseElegida.equals("Barbaro") || claseElegida.equals("Explorador")|| claseElegida.equals("Guerrero")|| claseElegida.equals("Monje")){
                    salFue.setText("+"+ (bonCom + bonFuerza));
                } else{
                    salFue.setText("+"+ bonFuerza);
                }
                if(claseElegida.equals("Bardo") || claseElegida.equals("Explorador")|| claseElegida.equals("Monje")|| claseElegida.equals("Picaro")){
                    salDes.setText("+"+ (bonCom + bonDestreza));
                } else{
                    salDes.setText("+"+ bonDestreza);
                }
                if(claseElegida.equals("Barbaro") || claseElegida.equals("Guerrero")|| claseElegida.equals("Hechicero")){
                    salCon.setText("+"+ (bonCom + bonConstitucion));
                } else{
                    salCon.setText("+"+ bonConstitucion);
                }
                if(claseElegida.equals("Druida") || claseElegida.equals("Mago")|| claseElegida.equals("Picaro")){
                    salInt.setText("+"+ (bonCom + bonInteligencia));
                } else{
                    salInt.setText("+"+ bonInteligencia);
                }
                if(claseElegida.equals("Brujo") || claseElegida.equals("Clerigo")|| claseElegida.equals("Druida")|| claseElegida.equals("Mago")|| claseElegida.equals("Paladin")){
                    salSab.setText("+"+ (bonCom + bonSabiduria));
                } else{
                    salSab.setText("+"+ bonSabiduria);
                }
                if(claseElegida.equals("Bardo") || claseElegida.equals("Clerigo")|| claseElegida.equals("Paladin")|| claseElegida.equals("Brujo")){
                    salCar.setText("+"+ (bonCom + bonCarisma));
                } else{
                    salCar.setText("+"+ bonCarisma);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }




}
