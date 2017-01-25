<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "entity.*" %>
    <%@ page import = "DAO.*" %>
    <%@ page import = "java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Selecione um contrato para alterar</title>
</head>
<body style="background-color: #FFFB83">
<!-- Também virá de um controlador... melhor -->
<br> Exibindo contratos cadastrados no sistema: <br>
<form action= ""  method=get>
		<%LinkedList <Contrato> contratos = new LinkedList<Contrato>();
  		ContratoDAO contratoDAO = new ContratoDAO();
  	
  		contratos = contratoDAO.getListaContratos();
  
  
  		for (int i = 0; i < contratos.size(); i++)
  		{
      %> 
		<input type="radio" name="contratoAcao" value="<%=contratos.get(i).getId()%>">
		Valor Comissão:	<%=contratos.get(i).getValorComissao() %> <br>
		Valor Contrato:	<%=contratos.get(i).calcularValorContrato() %> <br>
		Prazo:	<%=contratos.get(i).calcularPrazo() %>
			
		<br>

		<%
      	}
      %>
		<button type="submit" value="Alterar" formaction= CtrListContrato>Alterar </button>


</body>
</html>