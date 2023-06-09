package com.example.doce_do_bom;

import android.content.Context;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Inicio extends AppCompatActivity implements AdapterView.OnItemSelectedListener,NavigationView.OnNavigationItemSelectedListener {
    //Sincronização com XML
    ImageButton imgFavorito, imgMenu, imgPerfil;
    ImageView imgProduto;
    Spinner Spinner;
    ListView lstProdutos;
    TextView txtTitulo, txtNome, txtDescricao, txtPreco;
    SearchView searchView;

    //Variáveis
    ArrayAdapter adapterProdutos;
    ArrayList<Dados_Produto> produtos;

    //Contexto
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciodeslizante);
    }

    @Override
    protected void onStart() {
        //adicionarProdutos();
        txtNome = findViewById(R.id.ini_txtNome);

        imgPerfil = findViewById(R.id.nav_imgPerfil);
        imgFavorito = findViewById(R.id.ini_imgFavorito);

        //SearchView
        searchView = findViewById(R.id.ini_schSearchBar);

        lstProdutos = findViewById(R.id.ini_lstProdutos);
        //Lista
        lstProdutos = findViewById(R.id.ini_lstProdutos);
        adapterProdutos = new inicioAdapter(this, adicionarProdutos());
        lstProdutos.setAdapter(adapterProdutos);
        adapterProdutos.notifyDataSetChanged();
        //Spinner
        Spinner = findViewById(R.id.ini_spiFiltro);
        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this,R.array.filtro,R.layout.support_simple_spinner_dropdown_item);
        adapterSpinner.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        Spinner.setAdapter(adapterSpinner);
        Spinner.setOnItemSelectedListener(this);

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
        txtTitulo.setText("Página Inicial");

        //Contexto
        context = this;

        //adicionarProdutos();

        super.onStart();
    }

    @Override
    protected void onResume() {
        lstProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Dados.productNome = inicioAdapter.produtos.get(position).sNome;
                Dados.productDescricao = inicioAdapter.produtos.get(position).sDescricao;
                Dados.productPreco = inicioAdapter.produtos.get(position).sPreco;
                Dados.productDisponibildiade = inicioAdapter.produtos.get(position).getbDisponivel();
                Dados.productImagem = inicioAdapter.produtos.get(position).getintImagem();

                Intent i = new Intent(context, Produto.class);
                startActivity(i);
            }
        });

        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.openDrawer(GravityCompat.START);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String arg0) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
				//Testar o filter em:
				//inicioAdapter.produtos.get(position).getsNome();
                adapterProdutos.getFilter().filter(query);

                return false;//Testar, return true
            }
        });

        super.onResume();
    }

    private ArrayList<Dados_Produto> adicionarProdutos(){
        ArrayList<Dados_Produto> produtos = new ArrayList<Dados_Produto>();
        Dados_Produto p;
        //Executando consulta de cliente
        Tarefas c = new Tarefas("consultar",Dados.ip);
        c.execute();

        try {
            Thread.sleep(700);
        }catch (Exception e){
            e.printStackTrace();
            Log.i("msg",e.getMessage().toString());
        }
        String inf[]=Dados.stringBuffer.toString().split(";");
        for (int x=0;x<inf.length;x++) {
            String[] a = inf[x].split(":");
            p = new Dados_Produto(a[2], a[5], a[4], false, false, R.drawable.imagem1);
            produtos.add(p);
        }

        return produtos;
    }

    //Spinner
    //Caso algo seja selecionado no Spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String itemSelecionado = parent.getItemAtPosition(position).toString();

        Toast.makeText(context, itemSelecionado, Toast.LENGTH_SHORT).show();
    }
    //Caso nada seja selecionado no Spinner
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
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
            Intent i = new Intent(context, Sobre.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
