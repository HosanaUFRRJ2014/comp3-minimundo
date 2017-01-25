<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Página Inicial</title>
</head>
<body style="background-color: #FFFB83">
<div style="position: fixed;
  
  top: 0px;
  left: 400px;
  width: 100px;
  height: 50px">
  
 <h2>Olá!</h2>

<h3>Opções: </h3>

<form action="">
<button type="submit"  value="CriarComodo" formaction= "frontCriarComodo.jsp">Criar um cômodo </button>
<br>
<br>
<button type="submit"  value="ListarComodo" formaction= "frontListarComodo.jsp">Listar cômodos</button>
<br>
<br>
<button type="submit"  value="CriarMobilia" formaction= "frontCriarMobilia.jsp">Criar uma mobília</button>
<br>
<br>
<button type="submit"  value="ListarMobilia" formaction= "frontListarMobilia.jsp">Listar mobílias</button>
<br>
<br>
<button type="submit"  value="CriarContrato" formaction= "frontCriarContrato.jsp">Criar um contrato</button>
<br>
<br>
<button type="submit"  value="ListarContratos" formaction= "frontListarContrato.jsp">Listar Contratos</button>
</form>
</div>
</body>
</html>