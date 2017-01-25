<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Erro na exclusão</title>
</head>
<body  style="background-color: #FFFB83">
<%String url = (String) session.getAttribute("voltar"); %>

<p>Erro: o comodo não pode ser excluído por ter comodos associados e/ou por ter mobilias associadas a ele</p>

<button type="button" value="Voltar" formaction= url>Voltar </button>
</body>
</html>