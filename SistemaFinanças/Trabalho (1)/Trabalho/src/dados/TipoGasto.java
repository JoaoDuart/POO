package dados;

public class TipoGasto {
    private int id;
    private String nome;
    private Gasto gasto;

    public TipoGasto(int id, String nome, Gasto gasto) {
        this.id = id;
        this.nome = nome;
        this.gasto = gasto;
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

    public Gasto getGasto() {
        return gasto;
    }

    public void setGasto(Gasto gasto) {
        this.gasto = gasto;
    }
}
