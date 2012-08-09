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
<script language="javascript">
	function limpar(){
		with(document.cadastroCliente){
		nome.value = '';
		cpf.value = '';
		endereco.value = '';
		telefone.value = '';
		celular.value = '';	
		}
	}
</script>
<% 
/*
	String nome = (request.getParameter("nome"));
    String cpf = (request.getParameter("cpf"));
	String endereco = (request.getParameter("endereco"));
    String telefone = (request.getParameter("telefone"));
    String celular = (request.getParameter("celular"));
*/
%>
<div id="pagina">

 <div id="visao">
<p> Seja Bem vindo!</p>
 </div>
 
 <div id="cabecalho">
 <h1 >Sistema de Fidelização </h1>


      
 </div>
        <div  id="logon">
        
       </div>
 
 <div id="menu">
 <h3><strong>Menu Fideliza</strong></h3>
 
         <ul>
        <li><a href="index.jsp">Menu Inicial</a></li>
        <li><a href="sobreOSistema.html"> Sobre o sistema</a></li>
        <li><a href="empresasParticipantes.jsp">Empresa participantes </a></li>
        <li><a href="cadastroCliente.jsp">Cadastro </a></li>
        <li><a href="usarPontos.jsp">Usar pontos </a></li>
        <li><a href="relatorioPontos.jsp">Relatório de pontos </a></li>
        
        
      
       </ul>
 </div>
 
 <div id="conteudo">
            <h2 >Cadastro de cliente</h2>


       
    <form  name="cadastroCliente" action="cadastroClienteOP.jsp" method="get">
      <table width="400" border="1" align="center">
      <tr>
        <td width="76" bgcolor="#FFFFFF">Nome</td>
        <td width="306" bgcolor="#FFFFFF"><input name="nome" type="text" id="nome" size="30"/></td>
      </tr>
      <tr>
        <td bgcolor="#FFFFFF">CPF</td>
        <td bgcolor="#FFFFFF"><input name="cpf" type="text" id="cpf" size="30"/></td>
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
        <td bgcolor="#FFFFFF">Celular</td>
        <td bgcolor="#FFFFFF"><input name="celular" type="text" id="celular" size="30"/></td>
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
 <address>Todos os direitos reservados Sistema fideliza</address>
 </div>
</div>
</body>
</html>