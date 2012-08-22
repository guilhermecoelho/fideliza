<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="EstiloFideliza.css" type="text/css" rel="stylesheet" />
<title>Sistema Fideliza/login</title>
</head>
<body>
<div id="pagina">

<div id="visao">

 <p> Você está em:"";</p>
 <button type="button" onclick="Index.html" >Voltar</button>
 
</div>

<div id="cabecalho"> 

<h1>Sistema de Fidelização</h1>
      
</div>




<div id="menu">
     <h3>  Menu Fideliza</h3>
        <ul>
        <li><a href="Index.html">Menu Inicial</a></li>
        <li><a> </a></li>
        <li><a> </a></li>
        
      
       </ul>
</div>

<div id="conteudo">
     <div id="formulario">
        <form  name="cadastroCliente" action="cadastroClienteOP.jsp" method="get">
       
           
         Login:
         <input name="login" type="text" id="login" size="30"/>
     
      <br>
         Senha:
         <input name="senha" type="text" id="senha" size="30"/>
      <br>
      
          <input name="crud" type="submit" id="Entrar" value="Entrar" size="30"/>
          <input name="crud" type="submit" id="Cancelar" value="Cancelar" size="30"/>
         
       </form> 
         </div>
</div>




<div id="rodape">
        <address>Todos os direitos reservados Sistema fideliza</address>
      </div>

</div>
</body>
</html>