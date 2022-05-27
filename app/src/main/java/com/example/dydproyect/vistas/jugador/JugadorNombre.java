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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class JugadorNombre extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button btnActualizar, btnVida, btnAtributos, btnHabilidades, btnSalvaciones;
    EditText editNombre, editPuntosExp;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    TextView textNivel;
    Spinner spinerClase, spinerRazas, spinerAlineamiento;
    FirebaseAuth mAuth;
    FirebaseUser user = mAuth.getInstance().getCurrentUser();
    String uid = user.getUid();
    private ArrayList<String> listaClases = new ArrayList<>();
    private ArrayList<String> listaRazas = new ArrayList<>();
    private ArrayList<String> listaAlineamientos = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nombre);
        setTitle("Nombre y Clase");



        editNombre = findViewById(R.id.editTextNombrePj);
        editPuntosExp = findViewById(R.id.editTextPExp);
        textNivel = findViewById(R.id.textViewNivel);

        btnVida = findViewById(R.id.buttonVidaNom);
        btnAtributos = findViewById(R.id.buttonAtribnom);
        btnHabilidades = findViewById(R.id.buttonHabiNom);
        btnSalvaciones = findViewById(R.id.buttonSalvaNom);
        btnActualizar = findViewById(R.id.buttonActuNombre);
        spinerClase = (Spinner) findViewById(R.id.spinnerClase);
        spinerRazas = (Spinner) findViewById(R.id.spinnerRazas);
        spinerAlineamiento = (Spinner) findViewById(R.id.spinnerAlineamiento);


        agregarClases();
        agregarRazas();
        agregarAlineamiento();
        seleccionarClase();
        seleccionarRaza();
        seleccionarAlineamiento();
        inicializarFirebase();
        //deCero();
        listarPj();

        btnVida.setOnClickListener(view -> irVida());
        btnAtributos.setOnClickListener(view -> irAtributos());
        btnHabilidades.setOnClickListener(view -> irHabilidades());
        btnSalvaciones.setOnClickListener(view -> irSalvaciones());

        btnActualizar.setOnClickListener(view -> {

            int nivel = calcularNivel(Integer.parseInt(editPuntosExp.getText().toString()));
            String nombre = editNombre.getText().toString();
            String pExp = editPuntosExp.getText().toString();
            String claseElegida = (String) spinerClase.getSelectedItem();
            String razaElegida = (String) spinerRazas.getSelectedItem();
            String alinemientoElegido = (String) spinerAlineamiento.getSelectedItem();
            HashMap map = new HashMap();
            map.put("nombre", nombre);
            map.put("exp", pExp);
            map.put("nivel",nivel);
            map.put("clase", claseElegida);
            map.put("raza", razaElegida);
            map.put("alineamiento", alinemientoElegido);
            databaseReference.child('"'+String.valueOf(uid)+'"').child("Personaje").updateChildren(map);
        });

    }

    private void listarPj() {
        databaseReference.child('"'+String.valueOf(uid)+'"').child("Personaje").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String nombre = snapshot.child("nombre").getValue().toString();
                    editNombre.setText(nombre);
                    String exp = snapshot.child("exp").getValue().toString();
                    editPuntosExp.setText(exp);
                    String nivel = snapshot.child("nivel").getValue().toString();
                    textNivel.setText(nivel);
                    String clase = snapshot.child("clase").getValue().toString();
                    int posicionSpinner = listaClases.indexOf(clase);
                    spinerClase.setSelection(posicionSpinner);
                    String raza = snapshot.child("raza").getValue().toString();
                    spinerRazas.setSelection(listaRazas.indexOf(raza));
                    String alineamiento = snapshot.child("alineamiento").getValue().toString();
                    spinerAlineamiento.setSelection(listaAlineamientos.indexOf(alineamiento));

                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private int calcularNivel(int pExp){
        int nivel = 0;
        if (pExp >= 1 && pExp <=299){
            nivel = 1;
        }else if (pExp >=300 && pExp <= 899){
            nivel = 2;
        }else if (pExp >=900 && pExp <= 2699){
            nivel = 3;
        }else if (pExp >=2700 && pExp <= 6499){
            nivel = 4;
        }else if (pExp >=6500 && pExp <= 13999){
            nivel = 5;
        }else if (pExp >=14000 && pExp <= 22999){
            nivel = 6;
        }else if (pExp >=23000 && pExp <= 33999){
            nivel = 7;
        }else if (pExp >=34000 && pExp <= 47999){
            nivel = 8;
        }else if (pExp >=48000 && pExp <= 63999){
            nivel = 9;
        }else if (pExp >=64000 && pExp <= 84999){
            nivel = 10;
        }else if (pExp >=85000 && pExp <= 99999){
            nivel = 11;
        }else if (pExp >=100000 && pExp <= 119999){
            nivel = 12;
        }else if (pExp >=120000 && pExp <= 139999){
            nivel = 13;
        }else if (pExp >=140000 && pExp <= 164999){
            nivel = 14;
        }else if (pExp >=165000 && pExp <= 194999){
            nivel = 15;
        }else if (pExp >=195000 && pExp <= 224999){
            nivel = 16;
        }else if (pExp >=225000 && pExp <= 264999){
            nivel = 17;
        }else if (pExp >=265000 && pExp <= 304999){
            nivel = 18;
        }else if (pExp >=305000 && pExp <= 354999){
            nivel = 19;
        }else if (pExp >=35500){
            nivel = 20;
        }
        return nivel;
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

    private void agregarClases(){

        listaClases.add("Barbaro");
        listaClases.add("Bardo");
        listaClases.add("Brujo");
        listaClases.add("Clerigo");
        listaClases.add("Druida");
        listaClases.add("Explorador");
        listaClases.add("Guerrero");
        listaClases.add("Hechicero");
        listaClases.add("Mago");
        listaClases.add("Monje");
        listaClases.add("Paladin");
        listaClases.add("Picaro");
    }
    private void agregarRazas(){
        listaRazas.add("Enano");
        listaRazas.add("Elfo");
        listaRazas.add("Drow");
        listaRazas.add("Mediano");
        listaRazas.add("Humano");
        listaRazas.add("Draconido");
        listaRazas.add("Gnomo");
        listaRazas.add("Semielfo");
        listaRazas.add("Semiorco");
        listaRazas.add("Tiefling");

    }
    private void agregarAlineamiento(){
        listaAlineamientos.add("Legal Bueno");
        listaAlineamientos.add("Legal Neutral");
        listaAlineamientos.add("Legal Malvado");
        listaAlineamientos.add("Neutral Bueno");
        listaAlineamientos.add("Neutral");
        listaAlineamientos.add("Neutral Malvado");
        listaAlineamientos.add("Caotico Bueno");
        listaAlineamientos.add("Caotico Neutral");
        listaAlineamientos.add("Caotico Maligno");

    }

    private void seleccionarAlineamiento(){

        spinerAlineamiento.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaAlineamientos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerAlineamiento.setAdapter(adapter);

    }

    private void seleccionarRaza(){

        spinerRazas.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaRazas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerRazas.setAdapter(adapter);

    }

    private void seleccionarClase(){

        spinerClase.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaClases);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerClase.setAdapter(adapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
