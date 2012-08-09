<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="EstiloFideliza.css" type="text/css" rel="stylesheet" />
<title>Fideliza/cadastroEmpresa</title>
</head>
<body>
<div id="pagina">
<div id="visao"><p>Você está em:"Cadastro de Empresas"!</p></div>
<div id="cabecalho2"><h1>Sistema de Fidelização</h1></div>
<div id="menu">
  <h3><strong>Menu Fideliza</strong></h3>
  
  <ul>
  <li><a href="cadastroEmpresa.jsp">Cadastro</a></li>
  <li><a href="regraNegocio.jsp">Regra de negócio</a></li>
  <li><a href="relatorioEmpresa.jsp">Relatório de empresa</a></li>
  </ul>
       
</div>

<div id="conteudo">

<h2 align="center">Cadastro de empresa</h2>
      
    
    
    <form  name="cadastroCliente" action="cadastroEmpresaOP.jsp" method="get">
      <table width="400" border="1" align="center">
      <tr>
        <td width="76" bgcolor="#FFFFFF">Nome</td>
        <td width="306" bgcolor="#FFFFFF"><input name="nome" type="text" id="nome" size="30"/></td>
      </tr>
      <tr>
        <td bgcolor="#FFFFFF">CNPJ</td>
        <td bgcolor="#FFFFFF"><input name="cnpj" type="text" id="cnpj" size="30"/></td>
      </tr>
      <tr>
        <td bgcolor="#FFFFFF">Endereço</td>
        <td bgcolor="#FFFFFF"><input name="endereco" type="text" id="endereco" size="30"/></td>
      </tr>
      <tr>
        <td bgcolor="#FFFFFF">Telefone</td>
        <td bgcolor="#FFFFFF"><input name="telefone" type="text" id="telefone" size="30"/></td>
      </tr>
      <tr>
        <td bgcolor="#FFFFFF">Possui site</td>
        <td bgcolor="#FFFFFF">
        Sim:<input name="sim" type="radio" id="sim" size="30"/>
        Não:<input name="nao" type="radio" id="nao" size="30"/>
        </td>
      </tr>
      
    </table>
      <table width="393" border="0" align="center">
        <tr>
          <td align="center">
          <input name="crud" type="submit" id="incluir" value="Incluir" size="30"/>
          <input name="crud" type="submit" id="alterar" value="Alterar" size="30"/>
          <input name="crud" type="submit" id="excluir" value="Excluir" size="30"/>
          <input name="crud" type="button" id="limpar" value="Limpar" size="30" onclick="limpar()"/></td>
        </tr>
    </table>
   </form> 
    
     



</div>

<div id="rodape">
  <p><strong>Todos os direitos reservados</strong></p>
</div>
</div>
</body>
</html>