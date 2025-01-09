package dados;

import org.bson.Document;

public class Empregado {
    private String id;
    private String numeroMatricula;
    private String nome;
    private String tipo;

    public Empregado(String id, String numeroMatricula, String nome, String tipo) {
        this.id = id;
        this.numeroMatricula = numeroMatricula;
        this.nome = nome;
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public String getNumeroMatricula() {
        return numeroMatricula;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public Document toDocument() {
        Document doc = new Document("_id", id)
                .append("numeroMatricula", numeroMatricula)
                .append("nome", nome)
                .append("tipo", tipo);
        return doc;
    }

    public static Empregado fromDocument(Document doc) {
        String id = doc.getString("_id");
        String numeroMatricula = doc.getString("numeroMatricula");
        String nome = doc.getString("nome");
        String tipo = doc.getString("tipo");
        return new Empregado(id, numeroMatricula, nome, tipo);
    }

    @Override
    public String toString() {
        return "Empregado{" +
                "id='" + id + '\'' +
                ", numeroMatricula='" + numeroMatricula + '\'' +
                ", nome='" + nome + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
