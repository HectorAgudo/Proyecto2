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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    FirebaseAuth mAuth;
    FirebaseUser user = mAuth.getInstance().getCurrentUser();
    String uid = user.getUid();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habilidades);
        setTitle("Habilidades");

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
        databaseReference.child('"'+String.valueOf(uid)+'"').child("Personaje").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String claseElegida = snapshot.child("clase").getValue().toString();
                    int bonCom = JugadorVida.calcularBonCom(Integer.parseInt(snapshot.child("nivel").getValue().toString()));
                    int bonFuerza = JugadorAtributos.calcularModificador(Integer.parseInt(snapshot.child("Atributos").child("fuerza").getValue().toString()));
                    int bonDestreza = JugadorAtributos.calcularModificador(Integer.parseInt(snapshot.child("Atributos").child("destreza").getValue().toString()));
                    int bonConstitucion = JugadorAtributos.calcularModificador(Integer.parseInt(snapshot.child("Atributos").child("constitucion").getValue().toString()));
                    int bonInteligencia = JugadorAtributos.calcularModificador(Integer.parseInt(snapshot.child("Atributos").child("inteligencia").getValue().toString()));
                    int bonSabiduria = JugadorAtributos.calcularModificador(Integer.parseInt(snapshot.child("Atributos").child("sabiduria").getValue().toString()));
                    int bonCarisma = JugadorAtributos.calcularModificador(Integer.parseInt(snapshot.child("Atributos").child("carisma").getValue().toString()));

                    if (claseElegida.equals("Picaro") || claseElegida.equals("Monje")){
                        textAcr.setText("+" +(bonDestreza+bonCom));
                    } else {
                        textAcr.setText("+" + bonDestreza);
                    }
                    if (claseElegida.equals("Guerrero") || claseElegida.equals("Monje") || claseElegida.equals("Paladin") || claseElegida.equals("Barbaro")){
                        textAtle.setText("+" +(bonFuerza+bonCom));
                    } else {
                        textAtle.setText("+" + bonFuerza);
                    }
                    if (claseElegida.equals("Mago") || claseElegida.equals("Brujo") || claseElegida.equals("Hechicero")){
                        textCArca.setText("+" +(bonInteligencia+bonCom));
                    } else {
                        textCArca.setText("+" + bonInteligencia);
                    }
                    if (claseElegida.equals("Bardo") || claseElegida.equals("Picaro")){
                        textEnga.setText("+" +(bonCarisma+bonCom));
                    } else {
                        textEnga.setText("+" + bonCarisma);
                    }
                    if (claseElegida.equals("Bardo") || claseElegida.equals("Mago") || claseElegida.equals("Hechicero")){
                        textHisto.setText("+" +(bonInteligencia+bonCom));
                    } else {
                        textHisto.setText("+" + bonInteligencia);
                    }
                    if (claseElegida.equals("Picaro") || claseElegida.equals("Bardo")){
                        textInter.setText("+" +(bonCarisma+bonCom));
                    } else {
                        textInter.setText("+" + bonCarisma);
                    }
                    if (claseElegida.equals("Guerrero") || claseElegida.equals("Monje") || claseElegida.equals("Barbaro")){
                        textIntimi.setText("+" +(bonCarisma+bonCom));
                    } else {
                        textIntimi.setText("+" + bonCarisma);
                    }
                    if (claseElegida.equals("Bardo") || claseElegida.equals("Mago") || claseElegida.equals("Brujo")){
                        textInves.setText("+" +(bonInteligencia+bonCom));
                    } else {
                        textInves.setText("+" + bonInteligencia);
                    }
                    if (claseElegida.equals("Picaro") || claseElegida.equals("Monje")){
                        textJueM.setText("+" +(bonDestreza+bonCom));
                    } else {
                        textJueM.setText("+" + bonDestreza);
                    }
                    if (claseElegida.equals("Clerigo") || claseElegida.equals("Paladin")){
                        textMedicina.setText("+" +(bonSabiduria+bonCom));
                    } else {
                        textMedicina.setText("+" + bonSabiduria);
                    }
                    if (claseElegida.equals("Explorador") || claseElegida.equals("Barbaro") || claseElegida.equals("Explorador")){
                        textNatu.setText("+" +(bonInteligencia+bonCom));
                    } else {
                        textNatu.setText("+" + bonInteligencia);
                    }
                    if (claseElegida.equals("Explorador") || claseElegida.equals("Monje")){
                        textPerc.setText("+" +(bonSabiduria+bonCom));
                    } else {
                        textPerc.setText("+" + bonSabiduria);
                    }
                    if (claseElegida.equals("Bardo") || claseElegida.equals("Picaro")){
                        textPers.setText("+" +(bonSabiduria+bonCom));
                    } else {
                        textPers.setText("+" + bonSabiduria);
                    }
                    if (claseElegida.equals("Bardo") || claseElegida.equals("Brujo")){
                        textPersu.setText("+" +(bonCarisma+bonCom));
                    } else {
                        textPersu.setText("+" + bonCarisma);
                    }
                    if (claseElegida.equals("Clerigo") || claseElegida.equals("Paladin")){
                        textRel.setText("+" +(bonInteligencia+bonCom));
                    } else {
                        textRel.setText("+" + bonInteligencia);
                    }
                    if (claseElegida.equals("Picaro") || claseElegida.equals("Explorador")){
                        textSigi.setText("+" +(bonDestreza+bonCom));
                    } else {
                        textSigi.setText("+" + bonDestreza);
                    }
                    if (claseElegida.equals("Barbaro") || claseElegida.equals("Explorador")|| claseElegida.equals("Druida")){
                        textSuper.setText("+" +(bonSabiduria+bonCom));
                    } else {
                        textSuper.setText("+" + bonSabiduria);
                    }
                    if (claseElegida.equals("Explorador") || claseElegida.equals("Druida")){
                        textTAm.setText("+" +(bonSabiduria+bonCom));
                    } else {
                        textTAm.setText("+" + bonSabiduria);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }




}
