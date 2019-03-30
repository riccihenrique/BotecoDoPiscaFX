package db.entidades;

public class Categoria {
    private int cat_id;
    private String cat_nome;

    public Categoria() {
    }

    public Categoria(String cat_nome) {
        this.cat_nome = cat_nome;
    }
    
    public Categoria(int cat_id, String cat_nome) {
        this.cat_id = cat_id;
        this.cat_nome = cat_nome;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public String getCat_nome() {
        return cat_nome;
    }

    public void setCat_nome(String cat_nome) {
        this.cat_nome = cat_nome;
    }

    @Override
    public String toString() {
        return cat_nome;
    }

    
    
    
}
