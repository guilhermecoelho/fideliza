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
        <title>Usar pontos</title>
    </head>
    <body>
<table width="982" height="110" border="0" align="center">
  <tr>
    <td height="104" align="center" bgcolor="#c2ccff"><table width="258" border="0" align="left">
      <tr>
        <td height="27" align="center"><p>Você está em: &quot;Usar Pontos&quot;</p></td>
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
 <form name="imprimir" action="impressaoPontos.jsp" method="get">
<table width="629" border="0" align="center">
  <tr>
  <td align="center">
      <%
        String cpf = ((request.getParameter("cpf") != null) ? (request.getParameter("cpf")):"");
        String crud = ((request.getParameter("crud") != null) ? (request.getParameter("crud")) : "");

	Connection con = Conectar.abrirConexao();
	UsarPontosTO to = new UsarPontosTO();
        UsarPontosDAO dao = new UsarPontosDAO(con);

	

        %>
            <table width="350" border="0" align="left">
            <tr>
            <td align="left">CPF</td>
            <td align="left">
            <input name="cpf" type="text" maxlength="5" size="10" value="<%=cpf%>">
            </td>
            <tr/>

            <tr>
            <td align="left">Pontos disponivel:</td>
            <td align="left">
            <%
            out.println(dao.MostrarPontos(cpf));
            %>
            </td>
            </tr>         
            <%
             List<UsarPontosTO> us = dao.ListarEmpresa(cpf);

            int x = 1;
                String corLinha = "#FFFFFF";
                for (UsarPontosTO u : us) {
                    if (x % 2 == 0) {
                        corLinha = "#c2ccff";
                    } else {
                        corLinha = "#FFFFFF";
                    }
            %>
            <tr>
            <td align="left">Empresas cadastradas:</td>
            <td align="left">
             <%=u.getEmpresa()%>
            </td>
            </tr>
        <%
            x++;
        }%>

        <tr>
        <td align="left">Qtde que vai usar:</td>
        <td align="left">
        <input name="pontos" type="text" maxlength="5" size="10">
        </td>
        <tr/>

        <tr>
        <td align="left"></td>
        <td align="left">
        <input name="crud" type="submit" value="Imprimir" size="30"/>
        </td>
        <tr/>
  </td>
</table>
</form>
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
