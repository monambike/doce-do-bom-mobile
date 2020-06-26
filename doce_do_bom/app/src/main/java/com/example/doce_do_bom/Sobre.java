package com.example.doce_do_bom;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class Sobre extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //Sincronizando com XML
    ImageView imgMenu;
    TextView txtTitulo;

    //Contexto
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobredeslizante);
        setTitle("");
    }

    @Override
    protected void onStart() {

        //Menu Deslizante
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        imgMenu = findViewById(R.id.bar_imgMenu);

        //Definindo Fontes e Textos
        txtTitulo = findViewById(R.id.bar_txtTitulo);
        txtTitulo.setTypeface(Fontes.fontLovely);
        txtTitulo.setText("Sobre");

        //Contexto
        context = this;

        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    //Menu de Navegação
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.menuHome) {
            Intent i = new Intent(this, Inicio.class);
            startActivity(i);
        } else if (id == R.id.menuPerfil) {
            Intent i = new Intent(this, Usuario.class);
            startActivity(i);
        } else if (id == R.id.menuLogoff) {
            Dados.userNome = null;
            Dados.userEmail = null;
            Dados.userSenha = null;

            Intent i = new Intent(this, Login.class);
            startActivity(i);
        }else if(id == R.id.menuSobre){
            Intent i = new Intent(context, Sobre.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
