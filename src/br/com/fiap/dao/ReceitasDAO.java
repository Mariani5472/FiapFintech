package br.com.fiap.dao;

import java.util.List;
import br.com.fiap.bean.Receitas;
import br.com.fiap.exception.DBException;

public interface ReceitasDAO {
    void cadastrar(Receitas receita) throws DBException;
    void atualizar(Receitas receita) throws DBException;
    void remover(int codigo) throws DBException;
    Receitas buscar(int codigo);
    List<Receitas> listar();
}
