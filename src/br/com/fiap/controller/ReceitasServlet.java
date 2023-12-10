package br.com.fiap.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import br.com.fiap.bean.Receitas;
import br.com.fiap.dao.ReceitasDAO;
import br.com.fiap.exception.DBException;
import br.com.fiap.factory.DAOFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/receitas")
public class ReceitasServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ReceitasDAO dao;

    @Override
    public void init() throws ServletException {
        super.init();
        dao = DAOFactory.getReceitasDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");

        switch (acao) {
            case "listar":
                listar(request, response);
                break;
            case "abrir-form-edicao":
                abrirFormEdicao(request, response);
                break;
        }
    }

    private void abrirFormEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idReceita = Integer.parseInt(request.getParameter("codigo"));
        Receitas receita = dao.buscar(idReceita);
        request.setAttribute("receita", receita);
        request.getRequestDispatcher("edicao-receitas.jsp").forward(request, response);
    }

    private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Receitas> lista = dao.listar();
        request.setAttribute("receitas", lista); 
        request.getRequestDispatcher("lista-receitas.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");

        switch (acao) {
            case "cadastrar":
                cadastrar(request, response);
                break;
            case "editar":
                editar(request, response);
                break;
            case "excluir":
                excluir(request, response);
                break;
        }
    }

    private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String descricao = request.getParameter("descricao");
            double valor = Double.parseDouble(request.getParameter("valor"));
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Calendar data = Calendar.getInstance();
            data.setTime(format.parse(request.getParameter("data")));

            Receitas receita = new Receitas(0, descricao, valor, data.getTime());
            dao.cadastrar(receita);

            request.setAttribute("msg", "Receita cadastrada!");
        } catch (DBException db) {
            db.printStackTrace();
            request.setAttribute("erro", "Erro ao cadastrar receita");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Por favor, valide os dados");
        }
        request.getRequestDispatcher("cadastro-receitas.jsp").forward(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int codigo = Integer.parseInt(request.getParameter("codigo"));
            String descricao = request.getParameter("descricao");
            double valor = Double.parseDouble(request.getParameter("valor"));
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date data = format.parse(request.getParameter("data"));

            Receitas receita = new Receitas(codigo, descricao, valor, data);
            dao.atualizar(receita);

            request.setAttribute("msg", "Receita atualizada!");
        } catch (DBException db) {
            db.printStackTrace();
            request.setAttribute("erro", "Erro ao atualizar");
        } catch (ParseException e) {
            e.printStackTrace();
            request.setAttribute("erro", "Data inválida");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Por favor, valide os dados");
        }
        listar(request, response);
    }



    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        try {
            dao.remover(codigo);
            request.setAttribute("msg", "Receita removida!");
        } catch (DBException e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao atualizar");
        }
        listar(request, response);
    }
}
