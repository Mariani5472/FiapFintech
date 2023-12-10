<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Atualização de Receita</title>
    <%@ include file="header.jsp" %>
</head>
<body>
    <%@ include file="menu.jsp" %>
    <div class="container">
        <h1>Edição de Receita</h1>
        <form action="receitas" method="post">
            <input type="hidden" name="acao" value="editar">
            <input type="hidden" name="codigo" value="${receita.codigo}">

            <div class="form-group">
                <label for="id-descricao">Descrição</label>
                <input type="text" name="descricao" id="id-descricao" class="form-control" value="${receita.descricao}">
            </div>
            <div class="form-group">
                <label for="id-valor">Valor</label>
                <input type="text" name="valor" id="id-valor" class="form-control" value="${receita.valor}">
            </div>
            <div class="form-group">
                <label for="id-data">Data</label>
                <input type="text" name="data" id="id-data" class="form-control" value="${receita.data}">
            </div>
            <input type="submit" value="Salvar" class="btn btn-primary">
            <a href="receitas?acao=listar" class="btn btn-danger">Cancelar</a>
        </form>
    </div>
    <%@ include file="footer.jsp" %>
</body>
</html>
