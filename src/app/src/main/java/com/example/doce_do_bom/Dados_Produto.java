package com.example.doce_do_bom;

import java.util.ArrayList;

public class Dados_Produto {
    //Variáveis
    String sNome, sDescricao, sPreco;
    Boolean bFavoritado, bDisponivel;
    Integer intImagem;

    static ArrayList<String> arrayproductID = new ArrayList<>();
    static ArrayList<String> arrayproductNome = new ArrayList<>();
    static ArrayList<String> arrayproductDescricao = new ArrayList<>();
    static ArrayList<String> arrayproductPreco = new ArrayList<>();
    static ArrayList<String> arrayproductDisponivel = new ArrayList<>();


    public Dados_Produto(String sNome, String sDescricao, String sPreco, Boolean bFavoritado, Boolean bDisponivel, Integer intImagem){
        this.sNome = sNome;
        this.sDescricao = sDescricao;
        this.sPreco = sPreco;
        this.bFavoritado = bFavoritado;
        this.bDisponivel = bDisponivel;
        this.intImagem = intImagem;
    }

    public String getsNome() {
        return sNome;
    }
    public void setsNome(String sNome) {
        this.sNome = sNome;
    }

    public String getsDescricao() {
        return sDescricao;
    }
    public void setsDescricao(String sDescricao) {
        this.sDescricao = sDescricao;
    }

    public String getsPreco() {
        return sPreco;
    }
    public void setsPreco(String sPreco) {
        this.sPreco = sPreco;
    }

    public Boolean getbFavoritado() {
        return bFavoritado;
    }
    public void setbFavoritado(Boolean bFavoritado) {
        this.bFavoritado = bFavoritado;
    }

    public Boolean getbDisponivel() {
        return bDisponivel;
    }
    public void setbDisponivel(Boolean bDisponivel) {
        this.bDisponivel = bDisponivel;
    }

    public Integer getintImagem() {
        return intImagem;
    }
    public void setintImagem(Integer intImagem) {
        this.intImagem = intImagem;
    }
}
