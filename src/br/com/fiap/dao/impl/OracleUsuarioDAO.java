package br.com.fiap.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.fiap.bean.Usuario;
import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.singleton.ConnectionManager;

public class OracleUsuarioDAO implements UsuarioDAO{
	
	private Connection conexao;

	@Override
	public boolean validarUsuario(Usuario usuario) {
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    try {
	        conexao = ConnectionManager.getInstance().getConnection();
	        stmt = conexao.prepareStatement("SELECT * FROM USUARIOS WHERE DS_EMAIL = ? AND DS_SENHA = ?");
	        stmt.setString(1, usuario.getEmail());
	        stmt.setString(2, usuario.getSenha());
	        rs = stmt.executeQuery();

	        if (rs.next()){
	            return true;
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
	    return false;
	}
}