package br.com.fiap.dao;

import java.util.List;
import br.com.fiap.bean.Despesas;
import br.com.fiap.exception.DBException;

public interface DespesasDAO {
    void cadastrar(Despesas despesa) throws DBException;
    void atualizar(Despesas despesa) throws DBException;
    void remover(int codigo) throws DBException;
    Despesas buscar(int id);
    List<Despesas> listar();
}
