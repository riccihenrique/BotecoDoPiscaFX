
package db.dal;

import db.banco.Banco;
import db.entidades.Unidade;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DALUnidade {
    
    public boolean gravar(Unidade u)
    {
        String sql="insert into unidade (uni_nome) values ('#1')";
        sql=sql.replaceAll("#1", u.getUni_nome());
        
        return Banco.getCon().manipular(sql);
    }
    
     public boolean alterar(Unidade u)
    {
        String sql="update unidade set uni_nome='#1' where uni_id="+u.getUni_id();
        sql=sql.replaceAll("#1", u.getUni_nome());
        
        return Banco.getCon().manipular(sql);
    }
    
     public boolean apagar(Unidade u)
    {
        return Banco.getCon().manipular("delete from unidade where uni_id="+u.getUni_id());
    }
    
    public Unidade get(int cod)
    {
        Unidade u = null;
        ResultSet rs = Banco.getCon().consultar("select * from unidade where uni_id="+cod);
        try
        {
            if(rs.next())
            {
                u = new Unidade(rs.getInt("uni_id"),rs.getString("uni_nome"));
            }
        }
        catch(SQLException e){}
        return u;
    }
    
    public List<Unidade> get(String filtro)
    {
        List<Unidade> L = new ArrayList();
        String sql = "select * from unidade";
        if(!filtro.isEmpty())
            sql+=" where "+filtro;
        
        ResultSet rs = Banco.getCon().consultar(sql);
        try
        {
            while(rs.next())
                L.add(new Unidade(rs.getInt("uni_id"),rs.getString("uni_nome")));
        }
        catch(SQLException e){}
        return L;
    }         
}