package DAO;

import TO.UsuarioTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private Connection con;

    public UsuarioDAO(Connection con){
        setCon(con);
    }

    public Connection getCon(){
        return con;
    }

    private void setCon(Connection con) {
        this.con = con;
    }

public String InserirUsuario(UsuarioTO tblusuario) throws SQLException{

    String listaExistente;

    listaExistente = RetornaExistente(tblusuario.usuario);

    if (listaExistente == "S"){
        UsuarioExistente(tblusuario.usuario);
            return ("CPF já foi cadastrado anteriormente. 'Para clientes inativos, cadastro reativado'");
    }else{

    String sql = "insert into tblusuario(CPF,Nome,Endereco,Telefone,Celular,SnAtivo)"
                 + "values(?,?,?,?,?,1)";

        PreparedStatement ps = getCon().prepareStatement(sql);

        ps.setString(1,tblusuario.getUsuario());
        ps.setString(2,tblusuario.getNome());
        ps.setString(3,tblusuario.getEndereco());
        ps.setString(4,tblusuario.getTelefone());
        ps.setString(5,tblusuario.getCelular());
    
        if (ps.executeUpdate() > 0){
            return "Inserido com sucesso";
        } else {
            return "Erro ao inserir";
        }
    }
}

public String ExcluirUsuario(UsuarioTO tblusuario) throws SQLException{

    String sql = "update tblusuario set Nome = ?,CPF = ?,Endereco = ?,Telefone = ?,Celular = ?,SnAtivo = 0";

    try{
        PreparedStatement ps = getCon().prepareStatement(sql);

        ps.setString(1,tblusuario.getNome());
        ps.setString(2,tblusuario.getUsuario());
        ps.setString(3,tblusuario.getEndereco());
        ps.setString(4,tblusuario.getTelefone());
        ps.setString(5,tblusuario.getCelular());

        if (ps.executeUpdate() > 0){
            return "Excluído com sucesso";
        }else{
            return "Erro ao excluir";
        }
    }catch (SQLException e){
        return e.getMessage();
    }
}

public String AlterarUsuario(UsuarioTO tblusuario) throws SQLException{

    String sql = "update tblusuario set Nome = ?,CPF = ?,Endereco = ?,Telefone = ?,Celular = ? where SnAtivo <> 0 ";

    try{
        PreparedStatement ps = getCon().prepareStatement(sql);

        ps.setString(1,tblusuario.getNome());
        ps.setString(2,tblusuario.getUsuario());
        ps.setString(3,tblusuario.getEndereco());
        ps.setString(4,tblusuario.getTelefone());
        ps.setString(5,tblusuario.getCelular());

        if (ps.executeUpdate() > 0){
            return "Alterado com sucesso";
        } else {
            return "Erro ao alterar";
        }
    }catch (SQLException e){
        return e.getMessage();
    }
}

public List<UsuarioTO> PesquisarUsuario(String cpf) throws SQLException{

    String sql = "Select nome,cpf,endereco,telefone,celular from tblusuario";
           sql = sql + " where SnAtivo <> 0 and cpf ="+cpf+"";

    List<UsuarioTO> listaUsuario = new ArrayList<UsuarioTO>();

    try{
        PreparedStatement ps = getCon().prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);

        if (rs != null){
            while(rs.next()){
                UsuarioTO us = new UsuarioTO();
                us.setNome(rs.getString(1));
                us.setUsuario(rs.getString(2));
                us.setEndereco(rs.getString(3));
                us.setTelefone(rs.getString(4));
                us.setCelular(rs.getString(5));

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

public String UsuarioExistente(String user) throws SQLException{

    String sql = "update tblusuario set SnAtivo = 1 where SnAtivo = 0 and cpf ="+user+"";

    try{
        PreparedStatement ps = getCon().prepareStatement(sql);

        if (ps.executeUpdate() > 0){
            return "Reativado";
        }else{
            return "Erro ao reativar";
        }
    }catch (SQLException e){
        return e.getMessage();
    }
}

public List<UsuarioTO> PesquisarUsuarioExisten(String cpf) throws SQLException{

    String sql = "Select cpf from tblusuario";
           sql = sql + " where cpf ="+cpf+"";

    List<UsuarioTO> lista = new ArrayList<UsuarioTO>();

    try{
        PreparedStatement ps = getCon().prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);

    if (rs != null){
        while(rs.next()){
            UsuarioTO t = new UsuarioTO();
            t.setUsuario(rs.getString(1));
            lista.add(t);
        }
        return lista;
      }else{
        return null;
      }
    }catch (SQLException e){
        return null;
    }
}

public String RetornaExistente(String usercpf) throws SQLException{

    String teste = null;

    List<UsuarioTO> lp = PesquisarUsuarioExisten(usercpf);
       for(UsuarioTO p : lp){
          teste = p.getUsuario();
       }

   if (teste != null){
       return "S";
   }else{
       return "N";
   }


}

}



