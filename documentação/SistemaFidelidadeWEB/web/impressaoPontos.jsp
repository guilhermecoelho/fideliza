
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
      <table width="629" border="0" align="center">
  <tr>
  <td align="center">
      <%
        String pontos = ((request.getParameter("pontos") != null) ? (request.getParameter("pontos")):"");
        String cpf = ((request.getParameter("cpf") != null) ? (request.getParameter("cpf")):"");
        String crud = ((request.getParameter("crud") != null) ? (request.getParameter("crud")) : "");

	Connection con = Conectar.abrirConexao();
	UsarPontosTO to = new UsarPontosTO();
        UsarPontosDAO dao = new UsarPontosDAO(con);

        if (crud.equals("Imprimir")){
            out.println("*****************************************************************************\n\n");
            out.println(dao.impressao(pontos,cpf));
            out.println("*****************************************************************************");
            out.println("Pontos: " +pontos);
        }
        %>
    </body>
</html>
