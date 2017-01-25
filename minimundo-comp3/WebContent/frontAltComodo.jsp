<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import = "entity.*" %>
    <%@page import = "DAO.*" %>
    <%@page import = "java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Altere um comodo</title>


</head>
<body  style="background-color: #FFFB83">
<% String desc = request.getParameter("comodoAcao");
	ComodoDAO comodoDAO = new ComodoDAO();
   LinkedList <Comodo> comodos = comodoDAO.buscar(desc);
  
%>

<form action= CtrAltComodo method=get>
  Altere o comodo selecionado: <br>
  
  <input type="hidden" name="id" value="<%=comodos.get(0).getId() %>" > 
 <input type="hidden" name="id_comodo" value="<%=comodos.get(0).getIdPai() %>" >
  <%
  switch(comodos.get(0).getTipoComodo())
	{
	case 1: %>
		<input type="radio" name="comAssociados" value="ComodoComposto" > Comodo Composto <br>
		  <input type="radio" name="comAssociados" value="Cozinha" checked> Cozinha <br>
		  <input type="radio" name="comAssociados" value="Sala"> Sala <br>
		  <input type="radio" name="comAssociados" value="Quarto"> Quarto <br>
		<% break;
		
	case 2:%>
		<input type="radio" name="comAssociados" value="ComodoComposto" > Comodo Composto <br>
		  <input type="radio" name="comAssociados" value="Cozinha" > Cozinha <br>
		  <input type="radio" name="comAssociados" value="Sala" checked> Sala <br>
		  <input type="radio" name="comAssociados" value="Quarto"> Quarto <br>
		<%break;
		
	case 3: %><input type="radio" name="comAssociados" value="ComodoComposto" > Comodo Composto <br>
		  <input type="radio" name="comAssociados" value="Cozinha" > Cozinha <br>
		  <input type="radio" name="comAssociados" value="Sala" > Sala <br>
		  <input type="radio" name="comAssociados" value="Quarto" checked> Quarto <br>
	<%	break;
	
	case 4: %>
		<input type="radio" name="comAssociados" value="ComodoComposto" checked> Comodo Composto <br>
		  <input type="radio" name="comAssociados" value="Cozinha" > Cozinha <br>
		  <input type="radio" name="comAssociados" value="Sala" > Sala <br>
		  <input type="radio" name="comAssociados" value="Quarto" > Quarto <br>
	<%	break;	
		
	
}
  	
  	%>
  
 
  <br><br>
  Descrição:<br>
  <input type="text" name="descricao" value="<%=comodos.get(0).getDescricao()%>">
  
  <br>Associe Mobílias a esse cômodo: <br>
  <%
  TipoComMobDAO tcmDAO = new TipoComMobDAO();
  
  
  LinkedList<Long> idsMob = tcmDAO.buscarPorTipoComodo(String.valueOf(comodos.get(0).getTipoComodo()));
  MobiliaDAO mobiliaDAO = new MobiliaDAO();
  LinkedList <Mobilia> mobilias = new LinkedList <Mobilia>();  //pegando só as do tipo especifico do comodo
  
  for(long i : idsMob)
  {
	  
	  mobilias.addAll(mobiliaDAO.buscarPorId(String.valueOf(i)));
  }
  
  
  
  for (int i = 0; i < mobilias.size(); i++)
	{
%> 
	<input type="radio" name="mobiliaAssociadas" value="<%=mobilias.get(i).getId()%>">
		Descrição: <%=mobilias.get(i).getDescricao() %> <br>
		Custo Produção: <%=mobilias.get(i).getCustoProducao() %> <br>
		Tempo Entrega: <%=mobilias.get(i).getTempoEntrega() %>
		
	<br>

	<%
	}
%>
  

  
  <br>
  <input type="submit" value="Alterar">
</form> 

</body>
</html>