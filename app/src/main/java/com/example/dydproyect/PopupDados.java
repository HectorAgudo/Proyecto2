package com.example.dydproyect;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class PopupDados extends Activity {

    TextView textTirada;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dice_popup);
        setTitle("Tirada");
        textTirada = findViewById(R.id.textViewTirada);
        int tirada = (int) (Math.random()*(20));

        String bon = getIntent().getStringExtra("modFue");

        textTirada.setText("Tu tirada a sido de " + tirada + " y tu bonificador es de " + bon + " sumas : " + (tirada+Integer.parseInt(bon)) );
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width * .8) ,(int)(height * .6));

    }
}
