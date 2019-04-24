
package db.dal;

import db.banco.Banco;
import db.entidades.Produto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DALProduto {
    public boolean gravar(Produto p)
    {
        String sql="insert into produto (prod_nome,prod_preco,prod_descr,cat_id,uni_id) "
                + "values ('#1',#2,'#3',#4,#5)";
        sql=sql.replaceAll("#1", p.getProd_nome());
        sql=sql.replaceAll("#2", ""+p.getProd_preco());
        sql=sql.replaceAll("#3", p.getProd_desc());
        sql=sql.replaceAll("#4", ""+p.getCat().getCat_id());
        sql=sql.replaceAll("#5", ""+p.getUni().getUni_id());
        
        return Banco.getCon().manipular(sql);
    }
    
     public boolean alterar(Produto p)
    {
        String sql="update produto set prod_nome='#1',prod_preco=#2,prod_descr='#3',cat_id=#4,uni_id=#5  where prod_id="+p.getProd_id();
        sql=sql.replaceAll("#1", p.getProd_nome());
        sql=sql.replaceAll("#2", ""+p.getProd_preco());
        sql=sql.replaceAll("#3", p.getProd_desc());
        sql=sql.replaceAll("#4", ""+p.getCat().getCat_id());
        sql=sql.replaceAll("#5", ""+p.getUni().getUni_id());
        
        return Banco.getCon().manipular(sql);
    }
    
     public boolean apagar(Produto p)
    {
        return Banco.getCon().manipular("delete from produto where prod_id="+p.getProd_id());
    }
    
    public Produto get(int cod)
    {
        Produto p = null;
        DALCategoria c = new DALCategoria();
        DALUnidade u = new DALUnidade();
        ResultSet rs = Banco.getCon().consultar("select * from produto where prod_id="+cod);
        try
        {
            if(rs.next())
            {
                p = new Produto(rs.getInt("prod_id"),c.get(rs.getInt("cat_id")),u.get(rs.getInt("uni_id")),
                                    rs.getString("prod_nome"), rs.getDouble("prod_preco"), rs.getString("prod_descr"));
            }
        }
        catch(SQLException e){}
        return p;
    }
    
    public List<Produto> get(String filtro)
    {
        
        List<Produto> L = new ArrayList();
        
        DALCategoria c = new DALCategoria();
        DALUnidade u = new DALUnidade();
        String sql = "select * from produto";
        if(!filtro.isEmpty())
            sql+=" where "+filtro;
        
        ResultSet rs = Banco.getCon().consultar(sql);
        try
        {
            while(rs.next())
            {
                //int prod_id, Categoria cat, Unidade uni, String prod_nome, double prod_preco, String prod_desc
                L.add(new Produto(rs.getInt("prod_id"),c.get(rs.getInt("cat_id")),u.get(rs.getInt("uni_id")),
                                    rs.getString("prod_nome"), rs.getDouble("prod_preco"), rs.getString("prod_descr")));
            }
        }
        catch(SQLException e){ }
        return L;
           
    }     
}