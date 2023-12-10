package br.com.fiap.bean;

import java.util.Date;

public class Investimentos {

    private int codigo;
    private int codigoUsuario;
    private String descricao;
    private double valor;
    private Date data;

    public Investimentos(int codigo, int codigoUsuario, String descricao, double valor, Date data) {
        super();
        this.codigo = codigo;
        this.codigoUsuario = codigoUsuario;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }

    public Investimentos() {
        super();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
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
