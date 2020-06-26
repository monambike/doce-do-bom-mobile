package com.example.doce_do_bom;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Splashscreen2 extends AppCompatActivity {
    //Timer da SplashScreen
    private static int Splash_TimeOut = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen2);
    }

    @Override
    protected void onStart() {
        TextView txtNome = findViewById(R.id.spl_txtDoce);
        txtNome.setTypeface(Fontes.fontLovely);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Splashscreen2.this,  Login.class);
                startActivity(i);

                finish();
            }
        }, Splash_TimeOut);

        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
