<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "entity.*" %>
    <%@ page import = "DAO.*" %>
    <%@ page import = "java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Altere o contrato selecionado</title>
</head>
<body style="background-color: #FFFB83">
<%Contrato contrato = (Contrato) session.getAttribute("contrato"); %>

<form action= CtrAltContrato method=get>
Valor de Comissão:<br> <input type="text" name="valorComissao" value="<%=contrato.getValorComissao() %>">
		<br>Serão recalculados conforme as mudanças feitas
		<br> <br> Valor de Contrato: <br> <input type="text" name="valorContrato" value="<%=contrato.getValorContrato()%>" disabled> <br> <br> 
			Prazo: <br> <input type="text" name="prazo" value="<%=contrato.getPrazo() %>" disabled> <br>
		<br>
		<br> Ambientes associados: <br>

		<%LinkedList <Ambiente> ambientes = new LinkedList<Ambiente>();
  		AmbienteDAO ambienteDAO = new AmbienteDAO();
  	
  		ambientes = ambienteDAO.buscarPorIdContrato(String.valueOf(contrato.getId()));
  
  		
  
  		for (int i = 0; i < ambientes.size(); i++)
  		{%>
  			
  		<input type="checkbox" name="ambJaAssociados" value="<%=ambientes.get(i).getId()%>" checked disabled>
 
			Número Paredes<%=ambientes.get(i).getNumParedes() %> <br>
			Número Portas<%=ambientes.get(i).getNumPortas() %> <br>
			Metragem <%=ambientes.get(i).getMetragem() %>
		<br>

		<%
      	}
      %>
		<input type="submit" value="Criar mais um ambiente">
		<button type="submit"  value="menu" formaction= "index.jsp">Menu Inicial</button>

</form>

</body>
</html>