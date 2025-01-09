package dados;

public class Gasto {
    private String nome;
    private String descricao;
    private double valor;
    private TipoGasto tipogasto;

    public Gasto(String nome, String categoria, String data, String descricao, double valor, TipoGasto tipogasto) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.tipogasto = tipogasto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public TipoGasto getTipogasto() {
        return tipogasto;
    }

    public void setTipogasto(TipoGasto tipogasto) {
        this.tipogasto = tipogasto;
    }

    public String toString() {
        return "Nome: " + nome +
                ", Descrição: " + descricao +
                ", Valor: " + valor;
    }
}