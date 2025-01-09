package dados;

public class Receita {
    private String nome;
    private String descricao;
    private double valor;
    private TipoReceita tiporeceita;

    public Receita(String nome, String descricao, double valor, TipoReceita tiporeceita) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.tiporeceita = tiporeceita;
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

    public TipoReceita getTiporeceita() {
        return tiporeceita;
    }

    public void setTiporeceita(TipoReceita tiporeceita) {
        this.tiporeceita = tiporeceita;
    }

    public String toString() {
        return "Nome: " + nome +
                ", Descrição: " + descricao +
                ", Valor: " + valor;
    }

}