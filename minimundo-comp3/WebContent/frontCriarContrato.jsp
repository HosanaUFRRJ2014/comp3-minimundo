<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="entity.*"%>
<%@ page import="DAO.*"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Crie um contrato</title>
</head>
<body style="background-color: #FFFB83">
	<form action= "" method=get>
		Valor de Comissão:<br> <input type="text" name="valorComissao" value="">
		<br> <br> Valor de Contrato: <br> <input type="text"
			name="valorContrato" value="será calculado pelo sistema" disabled> <br> <br> 
			Prazo: <br> <input type="text" name="prazo" value="será calculado pelo sistema" disabled> <br>
		<br>
		<br> Crie um ambiente: <br>
		<button type="submit" value="criarAmbiente" formaction= CtrAltContrato >Criar Ambiente </button>
		
		
		
		<!-- Setar a posicao para o centro -->
	</form>

</body>
</html>

<!-- sempre exibir um box com todos os ambientes criados até o momento. Embaixo, ter uma opção
: criar novo ambiente. Também dar a opçao de selecionar(um ou muitos) e exclui-los. 
Sempre exibir as outras informações de contrato, como os custos, tempo de entrega, etc. -->