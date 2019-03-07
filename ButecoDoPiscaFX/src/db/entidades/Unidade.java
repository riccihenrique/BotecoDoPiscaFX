package db.entidades;

public class Unidade 
{
    private int uni_id;
    private String uni_nome;

    public Unidade() {
    }

    public Unidade(String uni_nome) {
        this.uni_nome = uni_nome;
    }

    public Unidade(int uni_id, String uni_nome) {
        this.uni_id = uni_id;
        this.uni_nome = uni_nome;
    }

    public int getUni_id() {
        return uni_id;
    }

    public void setUni_id(int uni_id) {
        this.uni_id = uni_id;
    }
    
    

    public String getUni_nome() {
        return uni_nome;
    }

    public void setUni_nome(String uni_nome) {
        this.uni_nome = uni_nome;
    }

    @Override
    public String toString() {
        return uni_nome;
    }
}
