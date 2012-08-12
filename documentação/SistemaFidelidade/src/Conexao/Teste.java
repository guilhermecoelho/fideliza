
package Conexao;
import DAO.*;
import TO.*;
import java.sql.*;
import java.util.List;


public class Teste {
    public static void main(String[] args) throws SQLException {

        Connection con = Conectar.abrirConexao();

        UsarPontosTO obj = new UsarPontosTO();
        UsarPontosDAO obj1 = new UsarPontosDAO(con);


      /* List<UsarPontosTO> lp = obj1.ListarEmpresa("37010510890");
       for(UsarPontosTO p : lp){
        System.out.println(p.getEmpresa());
       }*/

       

    }
}
