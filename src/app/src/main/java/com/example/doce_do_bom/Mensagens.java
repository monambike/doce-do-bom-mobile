package com.example.doce_do_bom;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

public class Mensagens {
    //Caso você queira sem botões, colocar opcao1 e opcao2 como "0"
    //Caso você queira um botão neutro, colocar apenas opcao2 como "0" ou apenas opcao1 como "0"
    //Caso você queira dois botões, preencher os dois campos
    //Finish volta uma página
    Mensagens(String titulo, String mensagem, String opcao1, String opcao2, Context context) {
        if (opcao1 == "0" && opcao2 == "0") {
            AlertDialog.Builder alerta = new AlertDialog.Builder(context);
            alerta.setTitle(titulo);
            alerta.setMessage(mensagem);
            alerta.show();
        }else if(opcao1 != "0" && opcao2 == "0"){
            AlertDialog.Builder alerta = new AlertDialog.Builder(context);
            alerta.setTitle(titulo);
            alerta.setMessage(mensagem);

            alerta.setNeutralButton(opcao1, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Dados.userOpcao = 0;

                    dialog.cancel();
                }
            });

            alerta.show();
        }else if(opcao1 != "0" && opcao2 != "0"){
            AlertDialog.Builder alerta = new AlertDialog.Builder(context);
            alerta.setTitle(titulo);
            alerta.setMessage(mensagem);

            alerta.setPositiveButton(opcao1, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    Log.d("teste", "op 1");
                }
            });
            alerta.setNegativeButton(opcao2, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    Log.d("teste", "op 2");
                }
            });

            alerta.show();
        }
    }
}