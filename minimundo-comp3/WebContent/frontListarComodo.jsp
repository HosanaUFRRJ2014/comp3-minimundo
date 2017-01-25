<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="entity.*"%>
<%@ page import="DAO.*"%>
<%@ page import="java.util.*"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Comodos cadastrados</title>
</head>
<body style="background-color: #FFFB83">
<!-- Também virá de um controlador... melhor -->
<br> Exibindo cômodos cadastrados no sistema: <br>
<form action= ""  method=get>
		<%LinkedList <Comodo> comodos = new LinkedList<Comodo>();
  		ComodoDAO comodoDAO = new ComodoDAO();
  	
  		comodos = comodoDAO.getListaComodos();
  
  
  		for (int i = 0; i < comodos.size(); i++)
  		{
      %> 
		<input type="radio" name="comodoAcao" value="<%=comodos.get(i).getDescricao()%>">
			Descrição: <%=comodos.get(i).getDescricao() %> <br>
			Tipo Cômodo: <%=comodos.get(i).getTipoComodo() %>
			
		<br>

		<%
      	}
      %>
		<button type="submit" value="Alterar" formaction= "frontAltComodo.jsp">Alterar </button>
		<button type="submit" value="Excluir" formaction= CtrRemComodo >Excluir </button>
		</form>                                          
		
</body>
</html>