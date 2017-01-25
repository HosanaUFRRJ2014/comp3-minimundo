<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "entity.*" %>
    <%@ page import = "DAO.*" %>
    <%@ page import = "java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<title>Criar uma Mobília</title>
</head>
<body style="background-color: #FFFB83">
<!-- no controlador que redireciona para essa página, 
Varrer o banco, ver se existe cozinha, sala ou quarto 
criado e abilitar ou desabilitar esses checkboxes -->
<% 
	LinkedList<Comodo> comodos = new LinkedList<Comodo>();
	ComodoDAO cDAO = new ComodoDAO();	
	comodos = cDAO.getListaComodos();
	
	boolean existeSala = false, existeQuarto = false, existeCozinha = false;
	
	for(Comodo c : comodos)
	{
		if(c.getTipoComodo() == 1)
			existeCozinha = true;
		
		if(c.getTipoComodo() == 2)
			existeSala = true;
		
		if(c.getTipoComodo() == 3)
			existeQuarto = true;
		
		if(existeSala && existeQuarto && existeCozinha)
			break;
	}
	
%>


<form action= CtrCriarMobilia method=get>
  Descrição:<br>
  <input type="text" name="descricao" value="">
  <br> <br>
  Custo de produção: <br>
  <input type="text" name="custo" value="">
  <br> <br>
  Tempo de Entrega: <br>
  <input type="text" name="tempoEntrega" value=""> <br>
  <br><br>
  Cômodos a associar: <br> 
  
  <% if(existeCozinha) 
  {%>
  <input type="checkbox"  name="associados" value="Cozinha"> Cozinha <br>
  
 <%} 
    else
    {%>
    	<input type="checkbox" name="associados"  value="Cozinha" disabled> Cozinha <br>
   
   <%} %>
   
   <% if(existeSala) 
  {%>
  <input type="checkbox" name="associados"  value="Sala"> Sala <br>
  
 <%} 
    else
    {%>
    	<input type="checkbox" name="associados"  value="Sala" disabled> Sala <br>
   
   <%} %>
   
   <% if(existeQuarto) 
  {%>
 <input type="checkbox" name="associados"  value="Quarto"> Quarto <br>
  
 <%} 
    else
    {%>
    	<input type="checkbox" name="associados"  value="Quarto" disabled> Quarto <br>
   
   <%} %>
  
  <input type="submit" value="Criar"> <!-- Setar a posicao para o centro -->
</form> 

</body>
</html>

<!-- Ao se criar uma mobília, colocar os comodos aos quais ela é associada -->
<!-- Da forma acima, só dá para associar a um dos tipos de comodo. Entretanto, o minimundo especifica q
que deve ser um ou muitos quartos, uma ou muitas salas, etc. -->
<!-- Por exemplo: uma
Mobília de descrição “cadeira de leitura” está associada a Quartos (um ou muitos) e Salas (um
ou muitos) destinados a leitura ou estudo. Ao se criar uma Mobília todos os cômodos a que ela será associada devem existir no sistema.  -->
<!-- Possível solução: deixar do jeito que está, mas em alterarComodo, apresentar as possíveis mobílias para serem 
associadas. -->