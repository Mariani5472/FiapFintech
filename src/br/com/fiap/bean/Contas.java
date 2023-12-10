package br.com.fiap.bean;

public class Contas {
	
	private int codigo;
    private int codigoUsuario;
    private double saldo;
    
    public Contas(int codigoConta, int codigoUsuario, double saldo) {
    	super();
    	this.codigo = codigoConta;
    	this.codigoUsuario = codigoUsuario;
    	this.saldo = saldo;
    	
    }
    
    public Contas() {
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

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
