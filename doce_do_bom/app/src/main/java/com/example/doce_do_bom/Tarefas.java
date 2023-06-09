package com.example.doce_do_bom;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Tarefas extends AsyncTask {
    //Variáveis
    URL url;
    InputStream inputStream;
    BufferedReader bufferedReader;
    HttpURLConnection httpURLConnection;
    StringBuffer stringBuffer;
    String s, caminhoURL;
    String operacao;

    //Construtor
    Tarefas(String operacao, String ip){
        this.operacao = operacao;

        switch (operacao){
            case "cadastrar":
                caminhoURL = "http://"+ip+"/cadastro.php?"+
                        "nome="+ Dados.userNome +"&"+
                        "senha="+ Dados.userSenha +"&"+
                        "email="+ Dados.userEmail +"&"+
                        "cpf="+ Dados.userCPF +"&"+
                        "foto="+ Dados.userFoto;
                break;
            case "consultar":
                caminhoURL = "http://"+ip+"/consulta_produto.php";
                break;
            case "excluir":
                caminhoURL = "http://"+ip+"/excluir_cli.php?" +
                        "cod_cliente="+ Dados.userId;
                break;
            case "editar":
                caminhoURL = "http://"+ip+"/editar_cli.php?"+
                        "cod_cliente="+ Dados.userId +"&"+
                        "nome="+ Dados.userNome +"&"+
                        "email="+ Dados.userEmail +"&"+
                        "senha="+ Dados.userSenha;
                break;
            case "logar":
                caminhoURL = "http://"+ip+"/login.php?"+
                        "email="+ Dados.userEmail +"&"+
                        "senha="+ Dados.userSenha;
                break;
            case "teste":
                caminhoURL = "http://"+ip+"/teste_cadastro.php";
                break;
        }
    }
    @Override
    protected Object doInBackground(Object[] objects) {
        switch (operacao){
            case "cadastrar":
                conectar(0);
                break;
            case "consultar":
                conectar(1);
                break;
            case "excluir":
                conectar(0);
                break;
            case "editar":
                conectar(0);
                break;
            case "logar":
                conectar(1);
                break;
            case "teste":
                conectar(1);
                break;
        }
        return null;
    }

    //Métodos (Operação)
    //Situacional 1 para Consultar e Logar
    //pois é necessário ler o texto da página
    void conectar(Integer situacional){
        if(situacional == 0) {
            try {
                url = new URL(caminhoURL);
                httpURLConnection = (HttpURLConnection) this.url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();
                inputStream = httpURLConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                stringBuffer = new StringBuffer();
            }catch (Exception e) {
                e.printStackTrace();
                Log.i("msg", "Erro: " + e.getMessage());
            }
        }else if(situacional == 1){
            try {
                url = new URL(caminhoURL);
                httpURLConnection = (HttpURLConnection) this.url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();
                inputStream = httpURLConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                stringBuffer = new StringBuffer();

                while ((s = bufferedReader.readLine()) != null) {
                    stringBuffer.append(s);
                }

                Dados.stringBuffer = stringBuffer;
                httpURLConnection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
                Log.i("msg", "Erro: " + e.getMessage());
            }
        }
    }
}