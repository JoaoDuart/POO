package dados;

import org.bson.Document;

public class Aeroporto {
    private String id;
    private String nome;
    private String localizacao;

    public Aeroporto(String id, String nome, String localizacao) {
        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public Document toDocument() {
        return new Document("_id", id)
                .append("nome", nome)
                .append("localizacao", localizacao);
    }

    public static Aeroporto fromDocument(Document doc) {
        return new Aeroporto(
                doc.getString("_id"),
                doc.getString("nome"),
                doc.getString("localizacao")
        );
    }
}
