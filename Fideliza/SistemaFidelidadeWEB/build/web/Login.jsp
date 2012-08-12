<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Conexao.*,java.sql.*" %>
<%@page import="DAO.*,java.sql.*" %>
<%@page import="TO.*,java.sql.*" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <script language="javascript">
            function limpar(){
                with(document.form1){
                    usuario.value = '';
                    senha.value = '';
                }
            }
        </script>
        
         <table width="982" height="110" border="0" align="center">
  <tr>
    <td height="104" align="center" bgcolor="#c2ccff"><table width="200" border="0" align="left">
      <tr>
        <td height="27" align="center"><p>BEM VINDO!</p></td>
      </tr>
      <tr>
        <td>Quero participar</td>
      </tr>
      <tr>
        <td>Já possuo cadastro</td>
      </tr>
    </table><h1>System Fideliza</h1></td>
  </tr>
</table>
<table width="982" height="245" border="0" align="center">
  <tr>
    <td width="200" bgcolor="#F5F5F5"> <h3><strong>Menu Fideliza</strong>
    </h3>
      <table width="200" height="110" border="1" align="center">
        <tr>
        <td bgcolor="#FFFFFF">Menu Inicial</td>
      </tr>
      <tr>
        <td bgcolor="#FFFFFF">Sobre o sistema de fidelização</td>
      </tr>
      <tr>
        <td bgcolor="#FFFFFF">Empresa participantes</td>
      </tr>
</table></td>
    <td width="772">
    <form name="form1" action="index.jsp" method="get">
    <table width="392" border="0" align="center">
      <tr>
        <td bgcolor="#c2ccff"><h3>LOGIN DO SISTEMA</h3></td>
      </tr>
    </table>
      <table width="390" border="0" align="center">
        <tr>
          <td width="102" height="45" valign="top">Cadastro</td>
          <td width="278" valign="top">Esqueci minha senha</td>
        </tr>
        <tr>
          <td align="right">Usuário</td>
          <td><input name="usuario" type="text" id="usuario" size="30" maxlength="11"/></td>
        </tr>
        <tr>
          <td height="42" align="right">Senha</td>
          <td><input name="senha" type="password" id="senha" size="30"/></td>
        </tr>
        <tr>
            <td align="right"><input type="submit" name="crud" value="Login" id="login"/></td>
            <td><input type="submit" name="crud" value="Limpar" onclick="limpar()"/></td>
        </tr>
    </table></td>
  </tr>
</table>
<table width="981" border="0" align="center">
  <tr>
    <td bgcolor="#c2ccff">&nbsp;</td>
  </tr>
</table>
        </form>
        <%
        Connection con = Conectar.abrirConexao();

        UsuarioTO obj = new UsuarioTO();
        UsuarioDAO obj1 = new UsuarioDAO(con);

       
        out.print(obj1.PesquisarUsuario());
        
        %>
    </body>
</html>
