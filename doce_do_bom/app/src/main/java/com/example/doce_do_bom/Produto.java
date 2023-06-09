package com.example.doce_do_bom;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Produto extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //Sincronizando com XML
    TextView txtTitulo, txtDescricaoDetalhada, txtPreco, txtDisponibilidade;
    ImageButton imgMenu, imgProduto, imgPerfil;


    //Contexto
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtodeslizante);
    }

    @Override
    protected void onStart() {
        txtPreco = findViewById(R.id.pro_txtPreco);
        txtDescricaoDetalhada = findViewById(R.id.pro_txtDescricao);
        txtDisponibilidade = findViewById(R.id.pro_txtDisponibilidade);

        imgProduto = findViewById(R.id.usu_imgProduto);
        imgPerfil = findViewById(R.id.nav_imgPerfil);

        //Menu Deslizante
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        imgMenu = findViewById(R.id.bar_imgMenu);

        //ScrollBar TextView
        txtDescricaoDetalhada.setMovementMethod(new ScrollingMovementMethod());

        //Definindo Fontes e Textos
        txtTitulo = findViewById(R.id.bar_txtTitulo);
        txtTitulo.setTypeface(Fontes.fontLovely);
        txtTitulo.setText(Dados.productNome);
        txtDescricaoDetalhada.setText(Dados.productDescricao);
        txtPreco.setText(Dados.productPreco);
        imgProduto.setBackgroundResource(Dados.productImagem);

        //Contexto
        context = this;

        super.onStart();
    }

    @Override
    protected void onResume() {
        //Animação
        if(Dados.productDisponibildiade == false){
            txtDisponibilidade.setText("Disponível");
            txtDisponibilidade.setBackgroundResource(R.drawable.animacao_disponivel);
            AnimationDrawable ani = (AnimationDrawable)txtDisponibilidade.getBackground();
            ani.start();

        }else if(Dados.productDisponibildiade == true){
            txtDisponibilidade.setText("Indisponível");
            txtDisponibilidade.setBackgroundResource(R.drawable.animacao_indisponivel);
            AnimationDrawable ani = (AnimationDrawable)txtDisponibilidade.getBackground();
            ani.start();
        }

        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.openDrawer(GravityCompat.START);
            }
        });

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
        } else if(id == R.id.menuPerfil) {
            Intent i = new Intent(this, Usuario.class);
            startActivity(i);
        }else if(id == R.id.menuLogoff){
            Dados.userNome = null;
            Dados.userEmail = null;
            Dados.userSenha = null;

            Intent i = new Intent(this, Login.class);
            startActivity(i);
        }else if(id == R.id.menuSobre){
            Intent i = new Intent(this, Sobre.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
