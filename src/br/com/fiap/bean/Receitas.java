package br.com.fiap.bean;

import java.util.Date;

public class Receitas {

    private int codigo;
    private String descricao;
    private double valor;
    private Date data;

    public Receitas(int codigo, String descricao, double valor, Date data) {
        super();
        this.codigo = codigo;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }



    public Receitas() {
        super();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}