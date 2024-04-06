package com.example.doce_do_bom;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    //Sincronização com XML
    EditText ediEmail, ediSenha;
    Button btnLogin;
    TextView txtAlterar;

    //Contexto
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void onStart() {
        //Sincronizando
        txtAlterar = findViewById(R.id.log_txtAlterar);

        ediEmail = findViewById(R.id.log_ediEmail);
        ediSenha = findViewById(R.id.log_ediSenha);

        btnLogin = findViewById(R.id.log_btnLogin);

        //Contexto
        context = this;

        super.onStart();
    }

    @Override
    protected void onResume() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if(ediEmail.getText().toString().length() != 0 || ediSenha.getText().toString().length() != 0){
                    Dados.userEmail = ediEmail.getText().toString();
                    Dados.userSenha = ediSenha.getText().toString();

                    Tarefas t = new Tarefas("logar", Dados.ip);
                    t.execute();
                    try{
                        Thread.sleep(700);
                    }catch (Exception e){

                    }

                    String inf[]=Dados.stringBuffer.toString().split(":");
                    if (Dados.stringBuffer.length() > 0) {
                        Dados.userId=inf[0];
                        Dados.userNome=inf[1];
                        Dados.userSenha=inf[2];
                        Dados.userEmail=inf[3];
                        //Dados.userFoto=inf[4].getBytes();

                        Intent i = new Intent(context, Inicio.class);
                        startActivity(i);

                        Log.d("msg", Dados.userNome);
                    }else{
                        new Mensagens("Erro", "Email e/ou Senha incorretos!", "Ok", "0", context);
                    }

                    Toast.makeText(context, "Bem vindo! "+ Dados.userNome, Toast.LENGTH_LONG);
                }else{
                    new Mensagens("Erro", "Email e/ou Senha inválido(s).", "Ok", "0", context);
                }*/
                Intent i = new Intent(context, Inicio.class);
                startActivity(i);
            }
        });

        txtAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Cadastro.class);
                startActivity(i);
            }
        });

        super.onResume();
    }
}