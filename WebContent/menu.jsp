<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg bg-dark navbar-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">FINTECH</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    	<li class="nav-item">
                            <a class="nav-link" aria-current="page" href="home.jsp">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="perfil.jsp">Meu Perfil</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="financas.jsp">Cadastro de Despesas</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="despesas?acao=listar">Lista de Despesas</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="cadastro-receitas.jsp">Cadastro de Receitas</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="receitas?acao=listar">Lista de Receitas</a>
                        </li>
                    </ul>
                    <c:if test="${empty user }">
	    				<span class="navbar-text text-danger" style="margin-right:10px" >
	       					${erro }
	  					</span>	
	    				<form class="form-inline my-2 my-lg-0" action="login" method="post">
	    	  					<input class="form-control mr-sm-2" type="text" name="email" placeholder="E-mail">
	      					<input class="form-control mr-sm-2" type="password" name="senha" placeholder="Senha">
	      					<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Entrar</button>
	    				</form>
    				</c:if>
   					<c:if test="${not empty user }">
    					<span class="navbar-text">
	    					${user }
	    					<a href="login" class="btn btn-outline-primary my-2 my-sm-0">Sair</a>
	  					</span>	
    				</c:if>
                </div>
            </div>
        </nav>
	</body>
</html>