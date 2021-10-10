package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

//Agregar animacion
        Animation animacion1 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_arriba);
        Animation animacion2 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_abajo);

        TextView DeTextView = findViewById(R.id.DeTextView);
       final TextView LogTextView = findViewById(R.id.LogTextView);
        final ImageView LogoImageView = findViewById(R.id.LogoImageView);

        DeTextView.setAnimation(animacion2);
        LogTextView.setAnimation(animacion2);
        LogoImageView.setAnimation(animacion1);

    new Handler().postDelayed(new Runnable() {

        public void run() {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            Pair[] pairs = new Pair[2];
            pairs[0] = new Pair<View, String>(LogoImageView, "LogoImagesTrans");
            pairs[1] = new Pair<View, String>(LogTextView, "textTrans");

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
                startActivity(intent, options.toBundle());
                finish();
            }else {
                startActivity(intent);
                finish();
            }
        }
        }, 4000 );
    }

    }
