<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "entity.*" %>
    <%@ page import = "DAO.*" %>
    <%@ page import = "java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Exibindo mobilias cadastradas</title>
</head>
<body style="background-color: #FFFB83">

<!-- Também virá de um controlador... melhor -->
<br> Exibindo mobilias cadastrados no sistema: <br>
<form action= ""  method=get>
		<%LinkedList <Mobilia> mobilias = new LinkedList<Mobilia>();
  		MobiliaDAO mobiliaDAO = new MobiliaDAO();
  	
  		mobilias = mobiliaDAO.getListaMobilias();
  
  
  		for (int i = 0; i < mobilias.size(); i++)
  		{
      %> 
		<input type="radio" name="mobiliaAcao" value="<%=mobilias.get(i).getId()%>">
			Descrição: <%=mobilias.get(i).getDescricao()%> <br>
			Custo Produção: <%=mobilias.get(i).getCustoProducao() %> <br>
			Tempo de Entrega: <%=mobilias.get(i).getTempoEntrega() %>
			
		<br>

		<%
      	}
      %>
		<button type="submit" name=botaoMob value="Alterar" formaction= CtrListMobilia>Alterar </button>
		<button type="submit"  name=botaoMob value="Excluir" formaction= CtrListMobilia>Excluir </button>

</body>
</html>