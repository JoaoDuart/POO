package dados;

import org.bson.Document;

import java.util.Arrays;
import java.util.List;

public class Teste {
    private String id;
    private String numeroANAC;
    private String numeroTeste;
    private Aviao aviao;
    private String dataRealizacao;
    private double horasGastas;
    private List<String> tecnicos;

    public Teste(String id, String numeroANAC, String numeroTeste, Aviao aviao) {
        this.id = id;
        this.numeroANAC = numeroANAC;
        this.numeroTeste = numeroTeste;
        this.aviao = aviao;
    }

    public String getId() {
        return id;
    }

    public String getNumeroANAC() {
        return numeroANAC;
    }

    public String getNumeroTeste() {
        return numeroTeste;
    }

    public Aviao getAviao() {
        return aviao;
    }

    public String getDataRealizacao() {
        return dataRealizacao;
    }

    public double getHorasGastas() {
        return horasGastas;
    }

    public List<String> getTecnicos() {
        return tecnicos;
    }

    public void setInformacoes(String dataRealizacao, double horasGastas, List<String> tecnicos) {
        this.dataRealizacao = dataRealizacao;
        this.horasGastas = horasGastas;
        this.tecnicos = tecnicos;
    }

    public Document toDocument() {
        Document doc = new Document("_id", id)
                .append("numeroANAC", numeroANAC)
                .append("numeroTeste", numeroTeste)
                .append("aviao", aviao.toDocument())
                .append("dataRealizacao", dataRealizacao)
                .append("horasGastas", horasGastas)
                .append("tecnicos", tecnicos);
        return doc;
    }

    public static Teste fromDocument(Document doc) {
        String id = doc.get("_id").toString();
        String numeroANAC = doc.getString("numeroANAC");
        String numeroTeste = doc.getString("numeroTeste");
        Document aviaoDoc = doc.get("aviao", Document.class);
        Aviao aviao = Aviao.fromDocument(aviaoDoc);
        String dataRealizacao = doc.get("dataRealizacao").toString();
        Double horasGastas = doc.getDouble("horasGastas");
        List<String> tecnicos = doc.getList("tecnicos", String.class);

        Teste teste = new Teste(id, numeroANAC, numeroTeste, aviao);
        if (dataRealizacao != null) {
            teste.setInformacoes(dataRealizacao, horasGastas != null ? horasGastas : 0.0, tecnicos != null ? tecnicos : Arrays.asList());
        }
        return teste;
    }
}
