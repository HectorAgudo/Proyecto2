package com.example.dydproyect;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dydproyect.firebase.Logeo;
import com.example.dydproyect.vistas.jugador.JugadorNombre;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button btnJugador, btnLogOut;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        btnJugador= findViewById(R.id.buttonJugador);
        btnLogOut = findViewById(R.id.buttonLogOut);
        mAuth = FirebaseAuth.getInstance();


        btnJugador.setOnClickListener(view -> irJugadorNombre());
        btnLogOut.setOnClickListener(view -> {
            mAuth.signOut();
            startActivity(new Intent(MainActivity.this,Logeo.class));
        });
    }

    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user == null){
            startActivity(new Intent(MainActivity.this, Logeo.class));
        }
    }

    public void irJugadorNombre(){
        Intent intent = new Intent(this, JugadorNombre.class);
        startActivity(intent);

    }


}