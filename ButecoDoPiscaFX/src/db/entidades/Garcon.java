package db.entidades;

public class Garcon {
    private int gar_id;
    private String gar_nome, gar_cep, gar_cpf, gar_endereco, gar_cidade, gar_uf, gar_fone;

    public Garcon() {
    }

    public Garcon(String gar_nome, String gar_cep, String gar_cpf, String gar_endereco, String gar_cidade, String gar_uf, String gar_fone) {
        this.gar_nome = gar_nome;
        this.gar_cep = gar_cep;
        this.gar_cpf = gar_cpf;
        this.gar_endereco = gar_endereco;
        this.gar_cidade = gar_cidade;
        this.gar_uf = gar_uf;
        this.gar_fone = gar_fone;
    }

    public Garcon(int gar_id, String gar_nome, String gar_cep, String gar_cpf, String gar_endereco, String gar_cidade, String gar_uf, String gar_fone) {
        this.gar_id = gar_id;
        this.gar_nome = gar_nome;
        this.gar_cep = gar_cep;
        this.gar_cpf = gar_cpf;
        this.gar_endereco = gar_endereco;
        this.gar_cidade = gar_cidade;
        this.gar_uf = gar_uf;
        this.gar_fone = gar_fone;
    }

    public int getGar_id() {
        return gar_id;
    }

    public void setGar_id(int gar_id) {
        this.gar_id = gar_id;
    }

    public String getGar_nome() {
        return gar_nome;
    }

    public void setGar_nome(String gar_nome) {
        this.gar_nome = gar_nome;
    }

    public String getGar_cep() {
        return gar_cep;
    }

    public void setGar_cep(String gar_cep) {
        this.gar_cep = gar_cep;
    }

    public String getGar_cpf() {
        return gar_cpf;
    }

    public void setGar_cpf(String gar_cpf) {
        this.gar_cpf = gar_cpf;
    }

    public String getGar_endereco() {
        return gar_endereco;
    }

    public void setGar_endereco(String gar_endereco) {
        this.gar_endereco = gar_endereco;
    }

    public String getGar_cidade() {
        return gar_cidade;
    }

    public void setGar_cidade(String gar_cidade) {
        this.gar_cidade = gar_cidade;
    }

    public String getGar_uf() {
        return gar_uf;
    }

    public void setGar_uf(String gar_uf) {
        this.gar_uf = gar_uf;
    }

    public String getGar_fone() {
        return gar_fone;
    }

    public void setGar_fone(String gar_fone) {
        this.gar_fone = gar_fone;
    }

    @Override
    public String toString() {
        return gar_nome;
    } 
}


