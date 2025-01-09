package dados;

import org.bson.Document;

public class Controlador extends Empregado {
    private String dataExame;

    public Controlador(String id, String numeroMatricula, String nome, String dataExame) {
        super(id, numeroMatricula, nome, "Controlador");
        this.dataExame = dataExame;
    }

    public String getDataExame() {
        return dataExame;
    }

    @Override
    public Document toDocument() {
        Document doc = super.toDocument();
        doc.append("dataExame", dataExame);
        return doc;
    }

    public static Controlador fromDocument(Document doc) {
        Empregado empregado = Empregado.fromDocument(doc);
        String dataExame = doc.getString("dataExame");
        return new Controlador(empregado.getId(), empregado.getNumeroMatricula(), empregado.getNome(), dataExame);
    }

    @Override
    public String toString() {
        return "Controlador{" +
                "dataExame=" + dataExame +
                "} " + super.toString();
    }
}
