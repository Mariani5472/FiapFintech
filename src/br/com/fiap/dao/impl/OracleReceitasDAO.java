package br.com.fiap.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.fiap.bean.Receitas;
import br.com.fiap.dao.ReceitasDAO;
import br.com.fiap.exception.DBException;
import br.com.fiap.singleton.ConnectionManager;

public class OracleReceitasDAO implements ReceitasDAO {

    private Connection conexao;

    @Override
    public void cadastrar(Receitas receita) throws DBException {
        PreparedStatement stmt = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "INSERT INTO RECEITAS (CODIGO, DESCRICAO, VALOR, DATA) VALUES (SQ_RECEITAS.NEXTVAL, ?, ?, ?)";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, receita.getDescricao());
            stmt.setDouble(2, receita.getValor());
            java.sql.Date data = new java.sql.Date(receita.getData().getTime());
            stmt.setDate(3, data);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao cadastrar.");
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }




    @Override
    public void atualizar(Receitas receita) throws DBException {
        PreparedStatement stmt = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "UPDATE RECEITAS SET DESCRICAO = ?, VALOR = ?, DATA = ? WHERE CODIGO = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, receita.getDescricao());
            stmt.setDouble(2, receita.getValor());
            java.sql.Date data = new java.sql.Date(receita.getData().getTime());
            stmt.setDate(3, data);
            stmt.setInt(4, receita.getCodigo());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao atualizar.");
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void remover(int codigo) throws DBException {
        PreparedStatement stmt = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "DELETE FROM RECEITAS WHERE CODIGO = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao remover.");
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public Receitas buscar(int id) {
        Receitas despesa = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conexao = ConnectionManager.getInstance().getConnection();
            stmt = conexao.prepareStatement("SELECT * FROM RECEITAS WHERE CODIGO = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()){
                int codigo = rs.getInt("CODIGO");
                String descricao = rs.getString("DESCRICAO");
                double valor = rs.getDouble("VALOR");
                java.sql.Date data = rs.getDate("DATA");
                despesa = new Receitas(codigo, descricao, valor, data);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return despesa;
    }


    @Override
    public List<Receitas> listar() {
        List<Receitas> lista = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conexao = ConnectionManager.getInstance().getConnection();
            stmt = conexao.prepareStatement("SELECT * FROM RECEITAS");
            rs = stmt.executeQuery();

            while (rs.next()) {
                int codigo = rs.getInt("CODIGO");
                String descricao = rs.getString("DESCRICAO");
                double valor = rs.getDouble("VALOR");
                java.sql.Date data = rs.getDate("DATA");
                Receitas receita = new Receitas(codigo, descricao, valor, data);
                lista.add(receita);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }
}

