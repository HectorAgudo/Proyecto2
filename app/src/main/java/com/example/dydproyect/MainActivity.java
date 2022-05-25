package com.example.dydproyect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import com.example.dydproyect.firebase.Logeo;
import com.example.dydproyect.vistas.jugador.JugadorNombre;
import com.google.android.gms.signin.internal.SignInClientImpl;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;

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


        btnJugador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irJugadorNombre();

            }
        });
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(MainActivity.this,Logeo.class));
            }
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