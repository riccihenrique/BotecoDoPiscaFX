
package db.dal;

import db.banco.Banco;
import db.entidades.TipoPagto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DALTipoPagto {
    public boolean gravar(TipoPagto tp)
    {
        String sql="insert into tipopgto (tpg_nome) values ('#1')";
        sql=sql.replaceAll("#1", tp.getTpg_nome());
        
        return Banco.getCon().manipular(sql);
    }
    
     public boolean alterar(TipoPagto tp)
    {
        String sql="update tipopgto set tpg_nome='#1' where tpg_id="+tp.getTpg_id();
        sql=sql.replaceAll("#1", tp.getTpg_nome());
        
        return Banco.getCon().manipular(sql);
    }
    
     public boolean apagar(TipoPagto tp)
    {
        return Banco.getCon().manipular("delete from tipopgto where tpg_id="+tp.getTpg_id());
    }
    
    public TipoPagto get(int cod)
    {
        TipoPagto tp = null;
        ResultSet rs = Banco.getCon().consultar("select * from tipopgto where tpg_id="+cod);
        try
        {
            if(rs.next())
            {
                tp = new TipoPagto(rs.getInt("tpg_id"),rs.getString("tpg_nome"));
            }
        }
        catch(SQLException e){}
        return tp;
    }
    
    public List<TipoPagto> get(String filtro)
    {
        List<TipoPagto> L = new ArrayList();
        String sql = "select * from tipopgto";
        if(!filtro.isEmpty())
            sql+=" where "+filtro;
        
        ResultSet rs = Banco.getCon().consultar(sql);
        try
        {
            while(rs.next())
                L.add(new TipoPagto(rs.getInt("tpg_id"),rs.getString("tpg_nome")));
        }
        catch(SQLException e){}
        return L;
    }     
}