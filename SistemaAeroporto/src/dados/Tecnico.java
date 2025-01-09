package dados;

import org.bson.Document;

import java.util.List;

public class Tecnico extends Empregado {
    private String endereco;
    private String telefone;
    private double salario;
    private List<String> modelosPeritos;

    public Tecnico(String id, String numeroMatricula, String nome,String endereco, String telefone, double salario, List<String> modelosPeritos) {
        super(id, numeroMatricula, nome ,"Tecnico");
        this.endereco = endereco;
        this.telefone = telefone;
        this.salario = salario;
        this.modelosPeritos = modelosPeritos;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public double getSalario() {
        return salario;
    }

    public List<String> getModelosPeritos() {
        return modelosPeritos;
    }

    @Override
    public Document toDocument() {
        Document doc = super.toDocument();
        doc.append("endereco", endereco);
        doc.append("telefone", telefone);
        doc.append("salario", salario);
        doc.append("modelosPeritos", modelosPeritos);
        return doc;
    }

    public static Tecnico fromDocument(Document doc) {
            System.out.println("Documento recebido: " + doc.toJson());
            Empregado empregado = Empregado.fromDocument(doc);
            String endereco = doc.getString("endereco");
            String telefone = doc.getString("telefone");
            Double salario = doc.getDouble("salario");
            List<String> modelosPeritos = doc.getList("modelosPeritos", String.class);

            return new Tecnico(empregado.getId(), empregado.getNumeroMatricula(), empregado.getNome(), endereco, telefone, salario, modelosPeritos);
    }

    @Override
    public String toString() {
        return "Tecnico{" +
                "endereco='" + endereco + '\'' +
                ", telefone='" + telefone + '\'' +
                ", salario=" + salario +
                ", modelosPeritos=" + modelosPeritos +
                "} " + super.toString();
    }
}
