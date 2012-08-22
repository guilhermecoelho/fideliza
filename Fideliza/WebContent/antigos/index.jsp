<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="EstiloFideliza.css" type="text/css" rel="stylesheet" />

<title>Fideliza/index</title>
</head>
<body>
<div id="pagina">

 <div id="visao">
<p> Seja Bem vindo!</p>
 </div>
 
 <div id="cabecalho">
 <h1 >Sistema de Fidelização </h1>


      
 </div>
        <div  id="logon">
        <form  name="cadastroCliente" action="cadastroClienteOP.jsp" method="get">
       
          
         Login:
         <input name="login" type="text" id="login" size="20"/>
<br>
         Senha:
         <input name="senha" type="password" id="senha" size="20"/>
     <br>
     <input name="crud" type="submit" id="Entrar" value="Entrar" size="25"/>
         <a href="cadastroCliente.jsp">Se cadastrar agora?</a> 
      </form> 
       </div>
 
 <div id="menu">
 <h3><strong>Menu Fideliza</strong></h3>
 
 <ul>
 <li><a href ="sobreOSistema.html">Sobre o Sistema de fidelização </a></li>
  <li><a href="empresasParticipantes.jsp">Empresas participantes </a></li>
  
 </ul>
 </div>
 
 <div id="conteudo">
        <img src="fideliza.png" width="800" height="500" />
       <% /*
        <img src="Imagem/farm.jpg" width="176" height="176" />
        <img src="Imagem/pet.jpg" width="119" height="163" />
        <img src="Imagem/cabelo.jpg" width="225" height="225" />
        */
        %>
 </div>
 
 <div id="rodape">
 <address>Todos os direitos reservados Sistema fideliza</address>
 </div>
</div>
</body>
</html>