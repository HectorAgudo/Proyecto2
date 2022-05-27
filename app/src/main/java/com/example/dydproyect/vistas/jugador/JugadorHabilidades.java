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

public class JugadorHabilidades extends AppCompatActivity {

    Button btnVida, btnAtributos, btnNombre, btnSalvaciones;
    TextView textAcr, textAtle, textCArca, textEnga, textHisto, textInter, textIntimi,textInves, textJueM, textMedicina, textNatu, textPerc, textPers, textPersu, textRel, textSigi, textSuper, textTAm;
    TextView cliAcr, cliAtle, cliCArca, cliEnga, cliHisto, cliInter, cliIntimi, cliInves, cliJueM, cliMedic, cliNatu, cliPerc, cliPers, cliPersu, cliRel, cliSigil, cliSuper, cliTAm;
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

        paqueteTextos();
        paqueteClicables();


        listarHabilidades();

        btnVida.setOnClickListener(view -> irVida());
        btnAtributos.setOnClickListener(view -> irAtributos());
        btnNombre.setOnClickListener(view -> irNombre());
        btnSalvaciones.setOnClickListener(view -> irSalvaciones());

        cliAcr.setOnClickListener(view -> {
            Intent intent = new Intent(JugadorHabilidades.this, PopupDados.class);
            intent.putExtra("mod", textAcr.getText().toString());
            intent.putExtra("tipoTirada","Acrobacias");
            startActivity(intent);
        });
        cliAtle.setOnClickListener(view -> {
            Intent intent = new Intent(JugadorHabilidades.this, PopupDados.class);
            intent.putExtra("mod", textAtle.getText().toString());
            intent.putExtra("tipoTirada","Atletismo");
            startActivity(intent);
        });
        cliCArca.setOnClickListener(view -> {
            Intent intent = new Intent(JugadorHabilidades.this, PopupDados.class);
            intent.putExtra("mod", textCArca.getText().toString());
            intent.putExtra("tipoTirada","C.Arcano");
            startActivity(intent);
        });
        cliEnga.setOnClickListener(view -> {
            Intent intent = new Intent(JugadorHabilidades.this, PopupDados.class);
            intent.putExtra("mod", textEnga.getText().toString());
            intent.putExtra("tipoTirada","Engaño");
            startActivity(intent);
        });
        cliHisto.setOnClickListener(view -> {
            Intent intent = new Intent(JugadorHabilidades.this, PopupDados.class);
            intent.putExtra("mod", textHisto.getText().toString());
            intent.putExtra("tipoTirada","Historia");
            startActivity(intent);
        });
        cliInter.setOnClickListener(view -> {
            Intent intent = new Intent(JugadorHabilidades.this, PopupDados.class);
            intent.putExtra("mod", textInter.getText().toString());
            intent.putExtra("tipoTirada","Interpretacion");
            startActivity(intent);
        });
        cliInves.setOnClickListener(view -> {
            Intent intent = new Intent(JugadorHabilidades.this, PopupDados.class);
            intent.putExtra("mod", textInves.getText().toString());
            intent.putExtra("tipoTirada","Investigacion");
            startActivity(intent);
        });
        cliJueM.setOnClickListener(view -> {
            Intent intent = new Intent(JugadorHabilidades.this, PopupDados.class);
            intent.putExtra("mod", textJueM.getText().toString());
            intent.putExtra("tipoTirada","Juego de Manos");
            startActivity(intent);
        });
        cliMedic.setOnClickListener(view -> {
            Intent intent = new Intent(JugadorHabilidades.this, PopupDados.class);
            intent.putExtra("mod", textMedicina.getText().toString());
            intent.putExtra("tipoTirada","Medicina");
            startActivity(intent);
        });
        cliNatu.setOnClickListener(view -> {
            Intent intent = new Intent(JugadorHabilidades.this, PopupDados.class);
            intent.putExtra("mod", textNatu.getText().toString());
            intent.putExtra("tipoTirada","Naturaleza");
            startActivity(intent);
        });
        cliPerc.setOnClickListener(view -> {
            Intent intent = new Intent(JugadorHabilidades.this, PopupDados.class);
            intent.putExtra("mod", textPerc.getText().toString());
            intent.putExtra("tipoTirada","Percepcion");
            startActivity(intent);
        });
        cliPers.setOnClickListener(view -> {
            Intent intent = new Intent(JugadorHabilidades.this, PopupDados.class);
            intent.putExtra("mod", textPers.getText().toString());
            intent.putExtra("tipoTirada","Perspicacia");
            startActivity(intent);
        });
        cliPersu.setOnClickListener(view -> {
            Intent intent = new Intent(JugadorHabilidades.this, PopupDados.class);
            intent.putExtra("mod", textPersu.getText().toString());
            intent.putExtra("tipoTirada","Persuasion");
            startActivity(intent);
        });
        cliRel.setOnClickListener(view -> {
            Intent intent = new Intent(JugadorHabilidades.this, PopupDados.class);
            intent.putExtra("mod", textRel.getText().toString());
            intent.putExtra("tipoTirada","Religion");
            startActivity(intent);
        });
        cliSigil.setOnClickListener(view -> {
            Intent intent = new Intent(JugadorHabilidades.this, PopupDados.class);
            intent.putExtra("mod", textSigi.getText().toString());
            intent.putExtra("tipoTirada","Sigilo");
            startActivity(intent);
        });
        cliSuper.setOnClickListener(view -> {
            Intent intent = new Intent(JugadorHabilidades.this, PopupDados.class);
            intent.putExtra("mod", textSuper.getText().toString());
            intent.putExtra("tipoTirada","Supervivencia");
            startActivity(intent);
        });
        cliTAm.setOnClickListener(view -> {
            Intent intent = new Intent(JugadorHabilidades.this, PopupDados.class);
            intent.putExtra("mod", textTAm.getText().toString());
            intent.putExtra("tipoTirada","T.Animales");
            startActivity(intent);
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
                    if (claseElegida.equals("Explorador") || claseElegida.equals("Barbaro")){
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

    private void paqueteTextos(){
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

    }

    private void paqueteClicables(){

        cliAcr = findViewById(R.id.textViewClicAcro);
        cliAtle = findViewById(R.id.textViewClicAtle);
        cliCArca = findViewById(R.id.textViewClicCArc);
        cliEnga = findViewById(R.id.textViewClicEnga);
        cliHisto = findViewById(R.id.textViewClicHisto);
        cliInter = findViewById(R.id.textViewClicInter);
        cliIntimi = findViewById(R.id.textViewClicIntimi);
        cliInves = findViewById(R.id.textViewClicInves);
        cliJueM = findViewById(R.id.textViewClicJManos);
        cliMedic = findViewById(R.id.textViewClicMedi);
        cliNatu = findViewById(R.id.textViewClicNatu);
        cliPerc = findViewById(R.id.textViewClicPerc);
        cliPers = findViewById(R.id.textViewClicPers);
        cliPersu = findViewById(R.id.textViewClicPersu);
        cliRel = findViewById(R.id.textViewClicRel);
        cliSigil = findViewById(R.id.textViewClicSigil);
        cliSuper = findViewById(R.id.textViewClicSuper);
        cliTAm = findViewById(R.id.textViewClicTAnim);

    }



}
