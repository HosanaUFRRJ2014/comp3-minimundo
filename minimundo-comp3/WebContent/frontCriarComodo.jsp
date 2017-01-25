<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Crie um cômodo</title>
</head>
<body style="background-color: #FFFB83">
<form action= CtrCriarComodo method=get>
  Informe o tipo de cômodo: <br>
  <input type="radio" name="comAssociados" value="ComodoComposto" checked> Comodo Composto <br>
  <input type="radio" name="comAssociados" value="Cozinha"> Cozinha <br>
  <input type="radio" name="comAssociados" value="Sala"> Sala <br>
  <input type="radio" name="comAssociados" value="Quarto"> Quarto <br>
  
  <br><br>
  Descrição:<br>
  <input type="text" name="descricao" value="">
  <br>
  <input type="submit" value="Criar">
</form> 

</body>
</html>
<!-- Se selecionado comodo composto, redirecionar para outra página, perguntando quais outros cômodos
o compora        ? -->