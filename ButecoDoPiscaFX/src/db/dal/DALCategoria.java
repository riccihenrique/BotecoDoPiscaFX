package db.dal;

import db.banco.Banco;
import db.entidades.Categoria;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DALCategoria {
    
    
    
    public boolean gravar(Categoria c)
    {
        String sql="insert into categoria (cat_nome) values ('#1')";
        sql=sql.replaceAll("#1", c.getCat_nome());
        
        return Banco.getCon().manipular(sql);
    }
    
     public boolean alterar(Categoria c)
    {
        String sql="update categoria set cat_nome='#1' where cat_id="+c.getCat_id();
        sql=sql.replaceAll("#1", c.getCat_nome());
        
        return Banco.getCon().manipular(sql);
    }
    
     public boolean apagar(Categoria c)
    {
        return Banco.getCon().manipular("delete from categoria where cat_id="+c.getCat_id());
    }
    
    public Categoria get(int cod)
    {
        Categoria c = null;
        ResultSet rs = Banco.getCon().consultar("select * from categoria where cat_id="+cod);
        try
        {
            if(rs.next())
            {
                c = new Categoria(rs.getInt("cat_id"),rs.getString("cat_nome"));
            }
        }
        catch(SQLException e){}
        return c;
    }
    
    public List<Categoria> get(String filtro)
    {
        List<Categoria> L = new ArrayList();
        String sql = "select * from categoria";
        if(!filtro.isEmpty())
            sql+=" where "+filtro;
        
        ResultSet rs = Banco.getCon().consultar(sql);
        try
        {
            while(rs.next())
                L.add(new Categoria(rs.getInt("cat_id"),rs.getString("cat_nome")));
        }
        catch(SQLException e){}
        return L;
    }     
}
