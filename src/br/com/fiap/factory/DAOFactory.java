package br.com.fiap.factory;

import br.com.fiap.dao.DespesasDAO;
import br.com.fiap.dao.ReceitasDAO;
import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.dao.impl.OracleDespesasDAO;
import br.com.fiap.dao.impl.OracleReceitasDAO;
import br.com.fiap.dao.impl.OracleUsuarioDAO;

public class DAOFactory {

    public static DespesasDAO getDespesasDAO() {
        return new OracleDespesasDAO();
    }

    public static ReceitasDAO getReceitasDAO() {
        return new OracleReceitasDAO();
    }

    public static UsuarioDAO getUsuarioDAO() {
        return new OracleUsuarioDAO();
    }
}
