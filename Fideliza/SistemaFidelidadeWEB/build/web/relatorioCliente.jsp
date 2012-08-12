<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@page import="Conexao.*,java.sql.*" %>
<%@page import="DAO.*,java.sql.*" %>
<%@page import="TO.*,java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Relatorio de cliente</title>
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
        function Mascara(o,f){
                v_obj=o
                v_fun=f
                setTimeout("execmascara()",1)
        }
        function execmascara(){
                v_obj.value=v_fun(v_obj.value)
        }
        function Integer(v){
                return v.replace(/\D/g,"")
        }

</script>
<%
        String cpf = ((request.getParameter("cpf") != null) ? (request.getParameter("cpf")):"");
%>

<table width="982" height="110" border="0" align="center">
  <tr>
    <td height="104" align="center" bgcolor="#c2ccff"><table width="258" border="0" align="left">
      <tr>
        <td height="27" align="center"><p>Você está em: &quot;Relatório&quot;</p></td>
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
        <td bgcolor="#c2ccff"><strong>Relatório de cliente</strong></td>
      </tr>
    </table>
    <form name="relatorioCliente" action="relatorioClienteOP.jsp" method="get">
      <table width="392" border="0" align="center">
      
      <tr>
        <td bgcolor="#FFFFFF">CPF</td>
        <td bgcolor="#FFFFFF">
        <input name="cpf" type="text" maxlength="11" onkeydown="Mascara(this,Integer);" onkeypress="Mascara(this,Integer);" onkeyup="Mascara(this,Integer);" size="30" value="<%=cpf%>"/>
        </td>
      </tr>
    </table>
      <table width="393" border="0" align="center">
        <tr>
          <td align="center">
          <input name="crud" type="submit" value="Gerar" size="30"/>
          </td>
        </tr>
    </table>
   </form>
    </td>
</table>
<table width="981" border="0" align="center">
  <tr>
    <td height="21" align="center" bgcolor="#c2ccff"><p><strong>Todos os direitos reservados</strong></p></td>
  </tr>
</table>
</body>
</html>
