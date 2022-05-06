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

public class JugadorHabilidades extends AppCompatActivity {

    Button btnVida, btnAtributos, btnNombre, btnSalvaciones;
    TextView textAcr, textAtle, textCArca, textEnga, textHisto, textInter, textIntimi,textInves, textJueM, textMedicina, textNatu, textPerc, textPers, textPersu, textRel, textSigi, textSuper, textTAm;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habilidades);

        inicializarFirebase();

        btnVida = findViewById(R.id.buttonVidaHabi);
        btnAtributos = findViewById(R.id.buttonAtriHabi);
        btnNombre = findViewById(R.id.buttonNomHabi);
        btnSalvaciones = findViewById(R.id.buttonSalvaHabi);

        textAcr = findViewById(R.id.textViewAcrobacias);
        textAtle = findViewById(R.id.textViewAtletismo);
        textCArca = findViewById(R.id.textViewCArcano);
        textEnga = findViewById(R.id.textViewEngaño);
        textHisto = findViewById(R.id.textViewHistoria);
        textInter = findViewById(R.id.textViewIntrepetacion);
        textIntimi = findViewById(R.id.textViewIntimidacion);
        textInves = findViewById(R.id.textViewInvestigacion);
        textJueM = findViewById(R.id.textViewJManos);
        textMedicina = findViewById(R.id.textViewMedicina);
        textNatu = findViewById(R.id.textViewNaturaleza);
        textPerc = findViewById(R.id.textViewPercepcion);
        textPers = findViewById(R.id.textViewPerspicacia);
        textPersu = findViewById(R.id.textViewPersuasion);
        textRel = findViewById(R.id.textViewReligion);
        textSigi = findViewById(R.id.textViewSigilo);
        textSuper = findViewById(R.id.textViewSupervivencia);
        textTAm = findViewById(R.id.textViewTAnimales);

        listarHabilidades();

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
        btnNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irNombre();
            }
        });
        btnSalvaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irSalvaciones();
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
    private void irNombre(){
        Intent intent = new Intent(this, JugadorNombre.class);
        startActivity(intent);
    }

    void listarHabilidades(){
        databaseReference.child("Personaje").child("Atributos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    textAcr.setText("+"+ JugadorAtributos.calcularModificador(Integer.parseInt(snapshot.child("destreza").getValue().toString())));
                    textAtle.setText("+"+ JugadorAtributos.calcularModificador(Integer.parseInt(snapshot.child("fuerza").getValue().toString())));
                    textCArca.setText("+"+ JugadorAtributos.calcularModificador(Integer.parseInt(snapshot.child("inteligencia").getValue().toString())));
                    textEnga.setText("+"+ JugadorAtributos.calcularModificador(Integer.parseInt(snapshot.child("carisma").getValue().toString())));
                    textHisto.setText("+"+ JugadorAtributos.calcularModificador(Integer.parseInt(snapshot.child("inteligencia").getValue().toString())));
                    textInter.setText("+"+ JugadorAtributos.calcularModificador(Integer.parseInt(snapshot.child("carisma").getValue().toString())));
                    textIntimi.setText("+"+ JugadorAtributos.calcularModificador(Integer.parseInt(snapshot.child("carisma").getValue().toString())));
                    textInves.setText("+"+ JugadorAtributos.calcularModificador(Integer.parseInt(snapshot.child("inteligencia").getValue().toString())));
                    textJueM.setText("+"+ JugadorAtributos.calcularModificador(Integer.parseInt(snapshot.child("destreza").getValue().toString())));
                    textMedicina.setText("+"+ JugadorAtributos.calcularModificador(Integer.parseInt(snapshot.child("sabiduria").getValue().toString())));
                    textNatu.setText("+"+ JugadorAtributos.calcularModificador(Integer.parseInt(snapshot.child("inteligencia").getValue().toString())));
                    textPerc.setText("+"+ JugadorAtributos.calcularModificador(Integer.parseInt(snapshot.child("sabiduria").getValue().toString())));
                    textPers.setText("+"+ JugadorAtributos.calcularModificador(Integer.parseInt(snapshot.child("sabiduria").getValue().toString())));
                    textPersu.setText("+"+ JugadorAtributos.calcularModificador(Integer.parseInt(snapshot.child("carisma").getValue().toString())));
                    textRel.setText("+"+ JugadorAtributos.calcularModificador(Integer.parseInt(snapshot.child("inteligencia").getValue().toString())));
                    textSigi.setText("+"+ JugadorAtributos.calcularModificador(Integer.parseInt(snapshot.child("destreza").getValue().toString())));
                    textSuper.setText("+"+ JugadorAtributos.calcularModificador(Integer.parseInt(snapshot.child("sabiduria").getValue().toString())));
                    textTAm.setText("+"+ JugadorAtributos.calcularModificador(Integer.parseInt(snapshot.child("sabiduria").getValue().toString())));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }




}
