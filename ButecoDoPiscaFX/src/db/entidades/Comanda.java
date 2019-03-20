package db.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Comanda 
{
    // Inner Class//
    public class Item
    {
        private Produto prod;
        private int it_quantidade;
        private double it_preco;

        public Item(Produto prod, int it_quantidade, double it_preco) {
            this.prod = prod;
            this.it_quantidade = it_quantidade;
            this.it_preco = it_preco;
        }

        public Item() {
        }
        
        

        public double getIt_preco() {
            return it_preco;
        }

        public void setIt_preco(double it_preco) {
            this.it_preco = it_preco;
        }

        
        
        public Produto getProd() {
            return prod;
        }

        public void setProd(Produto prod) {
            this.prod = prod;
        }

        public int getIt_quantidade() {
            return it_quantidade;
        }

        public void setIt_quantidade(int it_quantidade) {
            this.it_quantidade = it_quantidade;
        }
    }
    
    public class Pagamento
    {
        private double pag_valor;
        private int pag_id;
        private TipoPagto tipo;

        public Pagamento(double pag_valor, TipoPagto tipo) {
            this.pag_valor = pag_valor;
            this.tipo = tipo;
        }
        
        public Pagamento(int pag_id, double pag_valor, TipoPagto tipo) {
            this.pag_id = pag_id;
            this.pag_valor = pag_valor;
            this.tipo = tipo;
        }

        public Pagamento() {
        }

        public int getPag_id() {
            return pag_id;
        }

        public void setPag_id(int pag_id) {
            this.pag_id = pag_id;
        }
        
        public double getPag_valor() {
            return pag_valor;
        }

        public void setPag_valor(double pag_valor) {
            this.pag_valor = pag_valor;
        }

        public TipoPagto getTipo() {
            return tipo;
        }

        public void setTipo(TipoPagto tipo) {
            this.tipo = tipo;
        }
        
    }
    
    private int com_id;
    private Garcon garcon;
    private int com_numero;
    private String com_nome;
    private LocalDate com_data;
    private String com_desc;
    private double com_valor;
    private char com_status;
    private List <Item> itens = new ArrayList<Item>();
    private List <Pagamento> pagamentos = new ArrayList<Pagamento>();

    public Comanda() {
    }

    public Comanda(Garcon garcon, int com_numero, String com_nome, LocalDate com_data, String com_desc, double com_valor, char com_status) {
        this.garcon = garcon;
        this.com_numero = com_numero;
        this.com_nome = com_nome;
        this.com_data = com_data;
        this.com_desc = com_desc;
        this.com_valor = com_valor;
        this.com_status = com_status;
    }

    public Comanda(int com_id, Garcon garcon, int com_numero, String com_nome, LocalDate com_data, String com_desc, double com_valor, char com_status) {
        this.com_id = com_id;
        this.garcon = garcon;
        this.com_numero = com_numero;
        this.com_nome = com_nome;
        this.com_data = com_data;
        this.com_desc = com_desc;
        this.com_valor = com_valor;
        this.com_status = com_status;
    }

    public int getCom_id() {
        return com_id;
    }

    public void setCom_id(int com_id) {
        this.com_id = com_id;
    }

    public Garcon getGarcon() {
        return garcon;
    }

    public void setGarcon(Garcon garcon) {
        this.garcon = garcon;
    }

    public int getCom_numero() {
        return com_numero;
    }

    public void setCom_numero(int com_numero) {
        this.com_numero = com_numero;
    }

    public String getCom_nome() {
        return com_nome;
    }

    public void setCom_nome(String com_nome) {
        this.com_nome = com_nome;
    }

    public LocalDate getCom_data() {
        return com_data;
    }

    public void setCom_data(LocalDate com_data) {
        this.com_data = com_data;
    }

    public String getCom_desc() {
        return com_desc;
    }

    public void setCom_desc(String com_desc) {
        this.com_desc = com_desc;
    }

    public double getCom_valor() {
        return com_valor;
    }

    public void setCom_valor(double com_valor) {
        this.com_valor = com_valor;
    }

    public char getCom_status() {
        return com_status;
    }

    public List<Item> getItens() {
        return itens;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }
    

    public void setCom_status(char com_status) {
        this.com_status = com_status;
    }

    @Override
    public String toString() {
        return com_nome;
    }
    
    public void addProduto(Produto prod, int quantidade, double valor) {
        itens.add(new Item(prod, quantidade, valor));
    }

    public void addPagamento(double valor, TipoPagto tp) {
        pagamentos.add(new Pagamento(valor, tp));
    }
    
    public void addPagamento(int id, double valor, TipoPagto tp) {
        pagamentos.add(new Pagamento(id, valor, tp));
    }
}
