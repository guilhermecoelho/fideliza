package DAO;

import TO.UsarPontosTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsarPontosDAO {
    private Connection con;

    public UsarPontosDAO(Connection con){
        setCon(con);
    }

    public Connection getCon(){
        return con;
    }

    private void setCon(Connection con) {
        this.con = con;
    }

public String MostrarPontos(String cpf) throws SQLException{

    String sql = "select valor from tblpontos t inner join tblusuario u on(t.usuario = u.usuario_id)";
           sql = sql + "where u.cpf ="+cpf+"";

    String valor = null;

    try{
        PreparedStatement ps = getCon().prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);

        if (rs != null){
         while(rs.next()){
            UsarPontosTO us = new UsarPontosTO();
            us.setValor(rs.getString(1));

            valor = us.getValor();
         }

            return valor;
        }else{
            return "Erro nos Pontos";
        }
    }catch (SQLException e){
        return e.getMessage();
    }
}

public List<UsarPontosTO> ListarEmpresa(String cpf) throws SQLException{

    String sql = "SELECT t.nome FROM tblempresa t inner join tblusuario u on(t.empresa_id = u.empresa)";
           sql = sql + " where u.cpf ="+cpf+"";
    List<UsarPontosTO> listaUsuario = new ArrayList<UsarPontosTO>();

    try{
        PreparedStatement ps = getCon().prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);

        if (rs != null){
            while(rs.next()){
                UsarPontosTO us = new UsarPontosTO();
                us.setEmpresa(rs.getString(1));

                listaUsuario.add(us);
            }
                return listaUsuario;
        }else{
                return null;
            }
    }catch (SQLException e){
        return null;
    }
}

public String impressao(String pontos,String cpf) throws SQLException{

    int rpontos = Integer.parseInt(pontos);
    int val = Integer.parseInt(MostrarPontos(cpf));

    if (rpontos > val){
        return "Valor a ser utilizado maior que o disponivel";
    }else{
        return "Imprimi esse cupom e leve até o local de troca";
                
    }

}
}

