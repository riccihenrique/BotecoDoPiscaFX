package db.entidades;


public class Produto {
    private int prod_id;
    private Categoria cat;
    private Unidade uni;
    private String prod_nome;
    private double prod_preco;
    private String prod_desc;

    public Produto() {
    }

    public Produto(Categoria cat, Unidade uni, String prod_nome, double prod_preco, String prod_desc) {
        this.cat = cat;
        this.uni = uni;
        this.prod_nome = prod_nome;
        this.prod_preco = prod_preco;
        this.prod_desc = prod_desc;
    }

    public Produto(int prod_id, Categoria cat, Unidade uni, String prod_nome, double prod_preco, String prod_desc) {
        this.prod_id = prod_id;
        this.cat = cat;
        this.uni = uni;
        this.prod_nome = prod_nome;
        this.prod_preco = prod_preco;
        this.prod_desc = prod_desc;
    }

    public int getProd_id() {
        return prod_id;
    }

    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
    }

    public Categoria getCat() {
        return cat;
    }

    public void setCat(Categoria cat) {
        this.cat = cat;
    }

    public Unidade getUni() {
        return uni;
    }

    public void setUni(Unidade uni) {
        this.uni = uni;
    }

    public String getProd_nome() {
        return prod_nome;
    }

    public void setProd_nome(String prod_nome) {
        this.prod_nome = prod_nome;
    }

    public double getProd_preco() {
        return prod_preco;
    }

    public void setProd_preco(double prod_preco) {
        this.prod_preco = prod_preco;
    }

    public String getProd_desc() {
        return prod_desc;
    }

    public void setProd_desc(String prod_desc) {
        this.prod_desc = prod_desc;
    }

    @Override
    public String toString() {
        return prod_nome;
    }
}
