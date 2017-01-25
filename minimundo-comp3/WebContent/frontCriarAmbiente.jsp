<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "entity.*" %>
    <%@ page import = "DAO.*" %>
    <%@ page import = "java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Crie um ambiente</title>
</head>
<body style="background-color: #FFFB83">

<%Contrato contrato = (Contrato) session.getAttribute("contrato"); %>

Crie um ambiente:<br>
<form action= CtrCriarAmbiente method=get>

  <input type="hidden" name="idContrato" value="<%=contrato.getId()%>" />
  
  Número de paredes:<br>
  <input type="text" name="numParedes" value="">
  <br><br>
  Número de Portas:<br>
  <input type="text" name="numPortas" value="">
  <br><br>
  Metros Quadrados: <br>
  <input type="text" name="metragem" value="">
  <br><br>
  Associar mobilias: <br>
  <% 
  		MobiliaDAO mobDAO = new MobiliaDAO();
  		LinkedList <Mobilia> mobilias = mobDAO.getListaMobilias(); 
  		
  		for(Mobilia m : mobilias)
  		{
  			if(m.getIdItemVenda() != 0){%>
  			
  			<input type="checkbox" name="mobJaAssociadas" value="<%=m.getId()%>" checked disabled />
  			<%} 
  			
  			else
  			{%>
  				<input type="checkbox" name="mobAssociar" value="<%=m.getId()%>"/> <%=m.getDescricao() %>
			 <br>
  				Quantidade: <input type="text" id="quantidade" name="<%=m.getId()%>" value="" />
  				<br><br>
  		 <% }%>
  		<% }%>
  

  
  <input type="submit" value="Criar">
</form> 

</body>
</html>