<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@page import="Conexao.*,java.sql.*" %>
<%@page import="DAO.*,java.sql.*" %>
<%@page import="TO.*,java.sql.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Relatorio de cliente</title>
    </head>
    <body>
<table width="982" height="110" border="0" align="center">
  <tr>
    <td height="104" align="center" bgcolor="#c2ccff"><table width="258" border="0" align="left">
      <tr>
        <td height="27" align="center"><p>Você está em: &quot;Cadastro&quot;</p></td>
      </tr>
      <tr>
        <td><button>Sair</button></td>
      </tr>
    </table><h1>Sistema de Fidelização</h1></td>
  </tr>
</table>
<table width="982" height="245" border="0" align="center">
  <tr>
    <td width="200" bgcolor="#F5F5F5"> <h3><strong>Menu Fideliza</strong>
    </h3>
      <table width="200" height="110" border="0" align="center">
        <tr>
          <td bgcolor="#FFFFFF">Menu inicial</td>
        </tr>
        <tr>
          <td bgcolor="#FFFFFF">Sobre o sistema</td>
        </tr>
        <tr>
        <td bgcolor="#FFFFFF">Empresa participantes</td>
      </tr>
      <tr>
        <td bgcolor="#FFFFFF">
            <a href="cadastroCliente.jsp">Cadastro</a></td>
      </tr>
      <tr>
        <td bgcolor="#FFFFFF">
            <a href="usarPontos.jsp">Usar pontos</a></td>
      </tr>
      <tr>
        <td bgcolor="#FFFFFF">
            <a href="relatorioCliente.jsp">Relatório de pontos</a></td>
      </tr>
</table></td>
    <td width="772"><table width="633" border="0" align="center">
      <tr>
        <td bgcolor="#c2ccff"><strong>Cadastro de cliente</strong></td>
      </tr>
    </table>
<table width="629" border="0" align="center">
  <tr>
  <td align="center">
      <%
        String cpf = ((request.getParameter("cpf") != null) ? (request.getParameter("cpf")):"");
        String crud = ((request.getParameter("crud") != null) ? (request.getParameter("crud")) : "");

	Connection con = Conectar.abrirConexao();
	UsuarioPontosTO to = new UsuarioPontosTO();
        UsuarioPontosDAO dao = new UsuarioPontosDAO(con);

	to.setUsuario(cpf);

        if (crud.equals("Gerar")){
        %>
          <table width="616" border="0" align="center">
        <tr>
          <td align="center" bgcolor="#c2ccff">Usuário</td>
          <td align="center" bgcolor="#c2ccff">Pontos atuais</td>
          <td align="center" bgcolor="#c2ccff">Historico de ponto</td>
          <td align="center" bgcolor="#c2ccff">Valor gasto</td>
          <td align="center" bgcolor="#c2ccff">Iniciou em</td>
        </tr>

        <%
            List<UsuarioPontosTO> us = dao.PesquisarPontos(to.Usuario);

            int x = 1;
                String corLinha = "#FFFFFF";
                for (UsuarioPontosTO u : us) {
                    if (x % 2 == 0) {
                        corLinha = "#c2ccff";
                    } else {
                        corLinha = "#FFFFFF";
                    }
        %>
            <tr bgcolor="<%=corLinha%>">
            <td align="center"><%=u.getCpf()%></td>
            <td align="center"><%=u.getStatus()%></td>
            <td align="center"><%=u.getNumeroCupom()%></td>
            <td align="center"><%=u.getValor()%></td>
            <td align="center"><%=u.getDataCupom()%></td>
            </tr>
      
        <%
            x++;
            }
        }%>
  </td>
</table>
 </td>
  </tr>
  </table>
</td>

<table width="981" border="0" align="center">
  <tr>
    <td height="21" align="center" bgcolor="#c2ccff"><p><strong>Todos os direitos reservados</strong></p></td>
  </tr>
</table>

    </body>
</html>
