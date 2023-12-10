package br.com.fiap.teste;

import java.util.Calendar;
import java.util.List;

import br.com.fiap.bean.Receitas;
import br.com.fiap.dao.ReceitasDAO;
import br.com.fiap.exception.DBException;
import br.com.fiap.factory.DAOFactory;

public class ReceitasDAOTeste {

    public static void main(String[] args) {
        ReceitasDAO dao = DAOFactory.getReceitasDAO();

        // Cadastrar uma despesa
        Receitas receita = new Receitas(0, "Salário", 5000.00, Calendar.getInstance().getTime());
        try {
            dao.cadastrar(receita);
            System.out.println("Receita cadastrada.");
        } catch (DBException e) {
            e.printStackTrace();
        }

        // Buscar uma despesa pelo código e atualizar
        receita = dao.buscar(0); // Supondo que o código 1 corresponda a uma despesa existente
        if (receita != null) {
            receita.setDescricao("Recebimento Salario");
            receita.setValor(320.00);
            try {
                dao.atualizar(receita);
                System.out.println("Receita atualizada.");
            } catch (DBException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Receita não encontrada.");
        }

        // Listar as despesas
        List<Receitas> lista = dao.listar();
        for (Receitas item : lista) {
            System.out.println(item.getDescricao() + " " + item.getValor() + " " + item.getData());
        }

        // Remover uma despesa
        try {
            dao.remover(14); // Supondo que o código 1 corresponda a uma despesa existente
            System.out.println("Receita removida.");
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
