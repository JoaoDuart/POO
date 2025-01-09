package dados;

public class TipoReceita {
    private int id;
    private String nome;
    private Receita receita;

    public TipoReceita(int id, String nome, Receita receita) {
        this.id = id;
        this.nome = nome;
        this.receita = receita;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Receita getReceita() {
        return receita;
    }

    public void setReceita(Receita receita) {
        this.receita = receita;
    }
}