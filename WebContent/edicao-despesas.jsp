<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Atualização de Produto</title>
    <%@ include file="header.jsp" %>
</head>
<body>
    <%@ include file="menu.jsp" %>
    <div class="container">
        <h1>Edição de Produto</h1>
        <form action="despesas" method="post">
            <input type="hidden" name="acao" value="editar">
            <input type="hidden" name="codigo" value="${despesa.codigo}">

            <div class="form-group">
                <label for="id-descricao">Nome</label>
                <input type="text" name="descricao" id="id-descricao" class="form-control" value="${despesa.descricao}">
            </div>
            <div class="form-group">
                <label for="id-valor">Valor</label>
                <input type="text" name="valor" id="id-valor" class="form-control" value="${despesa.valor}">
            </div>
            <div class="form-group">
                <label for="id-data">Data</label>
                <input type="text" name="data" id="id-data" class="form-control" value="${despesa.data}">
            </div>
            <input type="submit" value="Salvar" class="btn btn-primary">
            <a href="despesas?acao=listar" class="btn btn-danger">Cancelar</a>
        </form>
    </div>
    <%@ include file="footer.jsp" %>
</body>
</html>
