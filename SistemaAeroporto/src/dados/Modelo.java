package dados;

import org.bson.Document;

public class Modelo {
    private String id;
    private String nome;
    private int capacidade;
    private double alcance;

    public Modelo(String id, String nome, int capacidade, double alcance) {
        this.id = id;
        this.nome = nome;
        this.capacidade = capacidade;
        this.alcance = alcance;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public double getAlcance() {
        return alcance;
    }

    public Document toDocument() {
        return new Document("_id", id)
                .append("nome", nome)
                .append("capacidade", capacidade)
                .append("alcance", alcance);
    }

    public static Modelo fromDocument(Document doc) {
        return new Modelo(
                doc.getString("_id"),
                doc.getString("nome"),
                doc.getInteger("capacidade"),
                doc.getDouble("alcance")
        );
    }
}
