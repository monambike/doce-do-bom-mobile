package com.example.doce_do_bom;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class Splashscreen1 extends AppCompatActivity {
    //Timer da SplashScreen
    private static int Splash_TimeOut = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen1);
    }
    @Override

    protected void onStart() {
        super.onStart();

        //Importando Fontes
        Fontes.fontLovely = Typeface.createFromAsset(getAssets(),"feeling_lovely.ttf");
        Fontes.fontCane = Typeface.createFromAsset(getAssets(),"candy_cane.ttf");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Splashscreen1.this,  Splashscreen2.class);
                startActivity(i);

                finish();
            }
        }, Splash_TimeOut);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
