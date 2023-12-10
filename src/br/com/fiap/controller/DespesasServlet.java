package br.com.fiap.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import br.com.fiap.bean.Despesas;
import br.com.fiap.dao.DespesasDAO;
import br.com.fiap.exception.DBException;
import br.com.fiap.factory.DAOFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/despesas")
public class DespesasServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private DespesasDAO dao;

    @Override
    public void init() throws ServletException {
        super.init();
        dao = DAOFactory.getDespesasDAO();
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
        int idDespesa = Integer.parseInt(request.getParameter("codigo"));
        Despesas despesa = dao.buscar(idDespesa);
        request.setAttribute("despesa", despesa);
        request.getRequestDispatcher("edicao-despesas.jsp").forward(request, response);
    }

    private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Despesas> lista = dao.listar();
        request.setAttribute("despesas", lista); 
        request.getRequestDispatcher("financas2.jsp").forward(request, response);
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

            Despesas despesa = new Despesas(0, descricao, valor, data.getTime());
            dao.cadastrar(despesa);

            request.setAttribute("msg", "Despesa cadastrada!");
        } catch (DBException db) {
            db.printStackTrace();
            request.setAttribute("erro", "Erro ao cadastrar despesa");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Por favor, valide os dados");
        }
        request.getRequestDispatcher("financas.jsp").forward(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int codigo = Integer.parseInt(request.getParameter("codigo"));
            String descricao = request.getParameter("descricao");
            double valor = Double.parseDouble(request.getParameter("valor"));
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date data = format.parse(request.getParameter("data"));

            Despesas despesa = new Despesas(codigo, descricao, valor, data);
            dao.atualizar(despesa);

            request.setAttribute("msg", "Despesa atualizada!");
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
            request.setAttribute("msg", "Despesa removida!");
        } catch (DBException e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao atualizar");
        }
        listar(request, response);
    }
}
