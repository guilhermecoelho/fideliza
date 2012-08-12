package DAO;

import TO.UsuarioPontosTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioPontosDAO {
    private Connection con;

    public UsuarioPontosDAO(Connection con){
        setCon(con);
    }

    public Connection getCon(){
        return con;
    }

    private void setCon(Connection con) {
        this.con = con;
    }

public List<UsuarioPontosTO> PesquisarPontos(String cpf) throws SQLException{

    String sql = "SELECT x.Usuario,x.Status,x.NumeroCupom,x.Valor,x.DataInsercao,t.cpf from tblpontos x";
           sql = sql + " inner join tblusuario t on (usuario = t.usuario_ID)";
           sql = sql + " where t.cpf ="+cpf+"";

    List<UsuarioPontosTO> listaUsuario = new ArrayList<UsuarioPontosTO>();

    try{
        PreparedStatement ps = getCon().prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);

        if (rs != null){
            while(rs.next()){
                UsuarioPontosTO us = new UsuarioPontosTO();
                us.setUsuario(rs.getString(1));
                us.setStatus(rs.getInt(2));
                us.setNumeroCupom(rs.getInt(3));
                us.setValor(rs.getFloat(4));
                us.setDataCupom(rs.getString(5));
                us.setCpf(rs.getString(6));

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

}
