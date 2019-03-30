package db.entidades;


public class TipoPagto {
    private int tpg_id;
    private String tpg_nome;

    public TipoPagto() {
    }

    public TipoPagto(String tpg_nome) {
        this.tpg_nome = tpg_nome;
    }

    public TipoPagto(int tpg_id, String tpg_nome) {
        this.tpg_id = tpg_id;
        this.tpg_nome = tpg_nome;
    }

    public int getTpg_id() {
        return tpg_id;
    }

    public void setTpg_id(int tpg_id) {
        this.tpg_id = tpg_id;
    }

    public String getTpg_nome() {
        return tpg_nome;
    }

    public void setTpg_nome(String tpg_nome) {
        this.tpg_nome = tpg_nome;
    }

    @Override
    public String toString() {
        return tpg_nome;
    }
    
    
    
    
}
