package com.example.dydproyect;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class PopupDados extends Activity {

    TextView textTirada;
    ImageView imgDado;
    MediaPlayer mp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dice_popup);
        setTitle("Tirada");
        textTirada = findViewById(R.id.textViewTirada);
        imgDado = findViewById(R.id.imageViewDado);
        mp = MediaPlayer.create(this,R.raw.dadostirar);
        mp.start();
        @SuppressLint("ResourceType") Animator set = (Animator) AnimatorInflater.loadAnimator(PopupDados.this,R.anim.flip);
        set.setTarget(imgDado);
        set.start();
        int tirada = (int) (Math.random()*(20));
        eleccionDado(tirada);
        String bon = getIntent().getStringExtra("mod");
        textTirada.setText("Tu tirada a sido de " + tirada + " y tu bonificador es de " + bon + " sumas : " + (tirada+Integer.parseInt(bon)));
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width * .8) ,(int)(height * .6));

    }

    private void eleccionDado(int tirada){
        if (tirada == 1){
            imgDado.setImageResource(R.drawable.face1);
        } else if (tirada == 2){
            imgDado.setImageResource(R.drawable.face2);
        } else if (tirada == 3){
            imgDado.setImageResource(R.drawable.face3);
        }else if (tirada == 4){
            imgDado.setImageResource(R.drawable.face4);
        }else if (tirada == 5){
            imgDado.setImageResource(R.drawable.face5);
        }else if (tirada == 6){
            imgDado.setImageResource(R.drawable.face6);
        }else if (tirada == 7){
            imgDado.setImageResource(R.drawable.face7);
        }else if (tirada == 8){
            imgDado.setImageResource(R.drawable.face8);
        }else if (tirada == 9){
            imgDado.setImageResource(R.drawable.face9);
        }else if (tirada == 10){
            imgDado.setImageResource(R.drawable.face10);
        }else if (tirada == 11){
            imgDado.setImageResource(R.drawable.face11);
        }else if (tirada == 12){
            imgDado.setImageResource(R.drawable.face12);
        }else if (tirada == 13){
            imgDado.setImageResource(R.drawable.face13);
        }else if (tirada == 14){
            imgDado.setImageResource(R.drawable.face14);
        }else if (tirada == 15){
            imgDado.setImageResource(R.drawable.face15);
        }else if (tirada == 16){
            imgDado.setImageResource(R.drawable.face16);
        }else if (tirada == 17){
            imgDado.setImageResource(R.drawable.face17);
        }else if (tirada == 18){
            imgDado.setImageResource(R.drawable.face18);
        }else if (tirada == 19){
            imgDado.setImageResource(R.drawable.face19);
        }else if (tirada == 20){
            imgDado.setImageResource(R.drawable.face20);
        }

    }
}
