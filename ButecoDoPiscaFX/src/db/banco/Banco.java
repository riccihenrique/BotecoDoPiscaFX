package db.banco;

public class Banco 
{
    static private Conexao con;
    static public Conexao getCon(){ return con;}

    private Banco() 
    {
        
    }
    
    static public boolean conectar()
    {
       con=new Conexao();
       return con.conectar("jdbc:postgresql://localhost/","boteco","postgres","postgres123");
    }   
}
