package com.example.doce_do_bom;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class Usuario extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //Sincronizando com XML
    ImageButton imgMenu, imgPerfil;
    TextView txtTitulo;
    EditText ediNome, ediEmail, ediSenha;
    Button btnEditar, btnExcluir;

    //Contexto
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuariodeslizante);
    }

    @Override
    protected void onStart() {
        ediNome = findViewById(R.id.usu_ediNome);
        ediEmail = findViewById(R.id.usu_ediEmail);
        ediSenha = findViewById(R.id.usu_ediSenha);

        btnEditar = findViewById(R.id.usu_btnEditar);
        btnExcluir = findViewById(R.id.usu_btnExcluir);

        imgPerfil = findViewById(R.id.nav_imgPerfil);

        //Menu deslizante
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        imgMenu = findViewById(R.id.bar_imgMenu);

        //Definindo Fontes e Textos
        txtTitulo = findViewById(R.id.bar_txtTitulo);
        txtTitulo.setTypeface(Fontes.fontLovely);
        txtTitulo.setText("Perfil");

        //Recebendo dados do banco
        ediNome.setText(Dados.userNome);
        ediEmail.setText(Dados.userEmail);
        ediSenha.setText(Dados.userSenha);

        //Contexto
        context = this;

        super.onStart();
    }

    @Override
    protected void onResume() {
        //Recebe dados do banco
        ediNome.setText(Dados.userNome);
        ediEmail.setText(Dados.userEmail);
        ediSenha.setText(Dados.userSenha);

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dados.userNome = ediNome.getText().toString();
                Dados.userEmail = ediEmail.getText().toString();
                Dados.userSenha = ediSenha.getText().toString();

                if(ediNome.getText().toString().length() != 0 && ediEmail.getText().toString().length() != 0 && ediSenha.getText().toString().length() != 0) {
                    Tarefas t = new Tarefas("editar", Dados.ip);
                    t.execute();

                    new Mensagens("Sucesso!", "Dados alterados com sucesso!", "Ok", "0", context);
                }else{
                    new Mensagens("Erro", "Não deixe nenhum dos campos vazio!", "Ok", "0", context);
                }
            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(context);
                alerta.setTitle("Tem certeza que deseja excluir sua conta?");
                alerta.setMessage("\n\n" +
                        "-Todos os seus dados serão perdidos;\n\n" +
                        "-Você perderá acesso ao app Doce do Bom " +
                        "enquanto não possuir uma conta.");

                alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Log.d("teste",Dados.userId);
                        Tarefas t = new Tarefas("excluir", Dados.ip);
                        t.execute();
                        Intent i = new Intent(context, Login.class);
                        startActivity(i);
                    }
                });
                alerta.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });

                alerta.show();
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
