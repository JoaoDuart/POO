package dados;

import org.bson.Document;

public class Aviao {
    private String id;
    private String modelo;
    private String numeroRegistro;

    public Aviao(String id, String modelo, String numeroRegistro) {
        this.id = id;
        this.modelo = modelo;
        this.numeroRegistro = numeroRegistro;
    }

    public String getId() {
        return id;
    }

    public String getModelo() {
        return modelo;
    }

    public String getNumeroRegistro() {
        return numeroRegistro;
    }

    public Document toDocument() {
        Document doc = new Document("id", id)
                .append("modelo", modelo)
                .append("numeroRegistro", numeroRegistro);
        return doc;
    }

    public static Aviao fromDocument(Document doc) {
        return new Aviao(doc.getString("id"), doc.getString("modelo"), doc.getString("numeroRegistro"));
    }
    public String toString() {
        return "NumeroRegistro: " + numeroRegistro + 
        " Modelo: " + modelo;
    }
}
