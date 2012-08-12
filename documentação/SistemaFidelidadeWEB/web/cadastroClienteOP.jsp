
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@page import="Conexao.*,java.sql.*" %>
<%@page import="DAO.*,java.sql.*" %>
<%@page import="TO.*,java.sql.*" %>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Cadastro de cliente</title>
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
    <td width="772"><table width="392" border="0" align="center">
      <tr>
        <td bgcolor="#c2ccff"><strong>Cadastro de cliente</strong></td>
      </tr>
    </table>
<table width="393" border="0" align="center">
  <tr>
  <td align="center">
  <%
        String nome = ((request.getParameter("nome") != null) ? (request.getParameter("nome")): "");
        String cpf = ((request.getParameter("cpf") != null) ? (request.getParameter("cpf")):"");
	String endereco = ((request.getParameter("endereco")!= null) ? (request.getParameter("endereco")):"");
        String telefone = ((request.getParameter("telefone")!= null) ? (request.getParameter("telefone")):"");
        String celular = ((request.getParameter("celular")!= null) ? (request.getParameter("celular")):"");
        String crud = ((request.getParameter("crud") != null) ? (request.getParameter("crud")) : "");


	Connection con = Conectar.abrirConexao();
	UsuarioTO to = new UsuarioTO();
        UsuarioDAO dao = new UsuarioDAO(con);


 	to.setNome(nome);
	to.setUsuario(cpf);
	to.setEndereco(endereco);
	to.setTelefone(telefone);
	to.setCelular(celular);


	if (crud.equals("Incluir")){
            out.print(dao.InserirUsuario(to));
        %><a href="cadastroCliente.jsp"></br>Voltar</a><%
	}
        if (crud.equals("Excluir")){
            out.print(dao.ExcluirUsuario(to));
        %><a href="cadastroCliente.jsp"></br>Voltar</a><%
        }
        if (crud.equals("Alterar")){
            out.print(dao.AlterarUsuario(to));
        %><a href="cadastroCliente.jsp"></br>Voltar</a><%
        }
        if (crud.equals("Consultar")){
        %>
          <table width="384" border="0" align="center">
            <tr bgcolor="#f2f2f2">
                <td>
                    CPF
                </td>
                <td>
                    Nome
                </td>
            </tr>
        <%
            List<UsuarioTO> us = dao.PesquisarUsuario(to.usuario);

            int x = 1;
                String corLinha = "#FFFFFF";
                for (UsuarioTO u : us) {
                    if (x % 2 == 0) {
                        corLinha = "#c2ccff";
                    } else {
                        corLinha = "#FFFFFF";
                    }
        %>
            <tr bgcolor="<%=corLinha%>">
            <td><a href="cadastroCliente.jsp?nome=<%=u.getNome()%>&cpf=<%=u.getUsuario()%>&endereco=<%=u.getEndereco()%>
                   &telefone=<%=u.getTelefone()%>&celular=<%=u.getCelular()%>"><%=u.getUsuario()%></a></td>
                   <td><%=u.getNome()%></td>
            </tr>
        </table>

        <%
            x++;
            }
        }%>
  </td>
  </tr>
  </table>
    </td>
</table>
<table width="981" border="0" align="center">
  <tr>
    <td height="21" align="center" bgcolor="#c2ccff"><p><strong>Todos os direitos reservados</strong></p></td>
  </tr>
</table>

</body>
</html>