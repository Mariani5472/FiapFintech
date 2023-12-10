package br.com.fiap.teste;

import java.util.Calendar;
import java.util.List;

import br.com.fiap.bean.Despesas;
import br.com.fiap.dao.DespesasDAO;
import br.com.fiap.exception.DBException;
import br.com.fiap.factory.DAOFactory;

public class DespesasDAOTeste {

    public static void main(String[] args) {
        DespesasDAO dao = DAOFactory.getDespesasDAO();

        // Cadastrar uma despesa
        Despesas despesa = new Despesas(0, "Calcinha", 520.00, Calendar.getInstance().getTime());
        try {
            dao.cadastrar(despesa);
            System.out.println("Despesa cadastrada.");
        } catch (DBException e) {
            e.printStackTrace();
        }

        // Buscar uma despesa pelo código e atualizar
        despesa = dao.buscar(1); // Supondo que o código 1 corresponda a uma despesa existente
        if (despesa != null) {
            despesa.setDescricao("Compra de calcinha");
            despesa.setValor(320.00);
            try {
                dao.atualizar(despesa);
                System.out.println("Despesa atualizada.");
            } catch (DBException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Despesa não encontrada.");
        }

        // Listar as despesas
        List<Despesas> lista = dao.listar();
        for (Despesas item : lista) {
            System.out.println(item.getDescricao() + " " + item.getValor() + " " + item.getData());
        }

        // Remover uma despesa
        try {
            dao.remover(14); // Supondo que o código 1 corresponda a uma despesa existente
            System.out.println("Despesa removida.");
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
