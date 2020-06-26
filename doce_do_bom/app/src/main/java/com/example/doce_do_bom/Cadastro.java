package com.example.doce_do_bom;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.io.ByteArrayOutputStream;

public class Cadastro extends AppCompatActivity {
    //Sincronização com XML
    EditText ediNome, ediEmail, ediSenha, ediConfirmarSenha, ediCPF;
    Button btnInscrever;
    TextView txtAlterar;
    ImageButton imgPerfil;

    //Variáveis
    Bitmap thumbnail;
    Integer galery = 1, permission = 2;
    //Máscaras
    SimpleMaskFormatter maskformatterCPF;
    MaskTextWatcher maskwatcherCPF;

    //Contexto
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
    }

    @Override
    protected void onStart() {
        ediNome = findViewById(R.id.cad_ediNome);
        ediEmail = findViewById(R.id.cad_ediEmail);
        ediSenha = findViewById(R.id.cad_ediSenha);
        ediCPF = findViewById(R.id.cad_ediCpf);
        ediConfirmarSenha = findViewById(R.id.cad_ediConfirmarSenha);

        txtAlterar = findViewById(R.id.cad_txtAlterar);

        btnInscrever = findViewById(R.id.cad_btnInscrever);

        imgPerfil = findViewById(R.id.cad_imgPerfil);

        //Máscaras
        maskformatterCPF = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        maskwatcherCPF = new MaskTextWatcher(ediCPF, maskformatterCPF);
        ediCPF.addTextChangedListener(maskwatcherCPF);

        //Contexto
        context = this;

        super.onStart();
    }

    @Override
    protected void onResume() {
        btnInscrever.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Salvando a imagem
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG,100,stream);
                Dados.userFoto = stream.toByteArray();

                //Recolhendo Dados
                Dados.userNome = ediNome.getText().toString();
                Dados.userSenha = ediSenha.getText().toString();
                Dados.userEmail = ediEmail.getText().toString();
                Dados.userCPF = ediCPF.getText().toString();

                //Se todos os campos estiverem preenchidos prosseguir
                if(ediNome.getText().toString().length() != 0 && ediEmail.getText().toString().length() != 0 && ediSenha.getText().toString().length() != 0 && ediConfirmarSenha.getText().toString().length() != 0 && ediCPF.getText().toString().length() != 0){

                    //Verificar se a senha está igual ao campo confirmar senha
                    if(ediSenha.getText().toString().equals(ediConfirmarSenha.getText().toString())){
                        //Executa o comando de mandar para o banco de Dados
                        Tarefas t = new Tarefas("cadastrar", Dados.ip);
                        t.execute();

                        //Limpa os campos e envia mensagem para o usuário
                        ediNome.setText("");
                        ediEmail.setText("");
                        ediSenha.setText("");
                        ediConfirmarSenha.setText("");
                        ediCPF.setText("");

                        //Recolhendo Dados
                        Dados.userNome = "";
                        Dados.userSenha = "";
                        Dados.userEmail = "";
                        Dados.userCPF = "";

                        Intent i = new Intent(context, Login.class);
                        startActivity(i);
                    }else{
                        new Mensagens("Senha", "Senha diferente do campo Confirmar Senha!", "Ok", "0", context);
                    }
                }else{
                    new Mensagens("Vazio", "Nenhum campo pode ser deixado vazio!", "Ok", "0", context);
                }
            }
        });

        txtAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Login.class);
                startActivity(i);
            }
        });

        imgPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, galery);
            }
        });

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)){

            }else {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},permission);
            }
        }

        super.onResume();
    }

    //Foto
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK&&requestCode== galery){
            Uri selectedImage=data.getData();
            String[]filePath= {MediaStore.Images.Media.DATA};
            Cursor c =getContentResolver().query(selectedImage,filePath,null,null,null);
            c.moveToFirst();
            int columnIndex=c.getColumnIndex(filePath[0]);
            String picturePath=c.getString(columnIndex);
            c.close();
            thumbnail=(BitmapFactory.decodeFile(picturePath));
            imgPerfil.setImageBitmap(thumbnail);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==permission){
            if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                //permissao concedida
            }else {
                //permissao negada, precisa ver o que deve ser desabilitado
            }
            return;
        }
    }
}