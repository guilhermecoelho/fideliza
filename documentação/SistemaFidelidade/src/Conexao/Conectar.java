package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conectar {

  public static Connection abrirConexao() throws SQLException {
    try {
        Class.forName("com.mysql.jdbc.Driver");
	System.out.println("Conectando ao banco");
	return (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sistemafidelidade", "root", "root");
    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    }
  }
}
