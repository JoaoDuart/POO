package negocio;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import database.MongoDBManager;
import dados.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class Sistema {
    private MongoDBManager dbManager;
    private MongoCollection<Document> avioesCollection;
    private MongoCollection<Document> empregadosCollection;
    private MongoCollection<Document> testesCollection;
    private MongoCollection<Document> modelosCollection;
    private MongoCollection<Document> aeroportosCollection;

    public Sistema() {
        dbManager = new MongoDBManager();
        avioesCollection = dbManager.getCollection("avioes");
        empregadosCollection = dbManager.getCollection("empregados");
        testesCollection = dbManager.getCollection("testes");
        modelosCollection = dbManager.getCollection("modelos");
        aeroportosCollection = dbManager.getCollection("aeroportos");

        // Adiciona modelos predefinidos se ainda não existirem
        adicionarModelosPredefinidos();
    }

    public void close() {
        dbManager.close();
    }

    // Avioes
    public List<Aviao> getAvioes() {
        List<Aviao> avioes = new ArrayList<>();
        try (MongoCursor<Document> cursor = avioesCollection.find().iterator()) {
            while (cursor.hasNext()) {
                avioes.add(Aviao.fromDocument(cursor.next()));
            }
        }
        return avioes;
    }

    public void adicionarAviao(Aviao aviao) {
        avioesCollection.insertOne(aviao.toDocument());
    }

    public void removerAviao(String id) {
        avioesCollection.deleteOne(new Document("numeroRegistro", id));
    }

    // Empregados
    public List<Empregado> getEmpregados() {
        List<Empregado> empregados = new ArrayList<>();
        for (Document doc : empregadosCollection.find()) {
            String tipo = doc.getString("tipo");
            if (tipo.equals("Tecnico")) {
                empregados.add(Tecnico.fromDocument(doc));
            } else if (tipo.equals("Controlador")) {
                empregados.add(Controlador.fromDocument(doc));
            } else {
                empregados.add(Empregado.fromDocument(doc));
            }
        }
        return empregados;
    }

    public void adicionarEmpregado(Empregado empregado) {
        Document doc = empregado.toDocument();
        empregadosCollection.insertOne(doc);
    }

    public void removerEmpregado(String id) {
        empregadosCollection.deleteOne(new Document("numeroMatricula", id));
    }

    // Testes
    public List<Teste> getTestes() {
        List<Teste> testes = new ArrayList<>();
        for (Document doc : testesCollection.find()) {
            testes.add(Teste.fromDocument(doc));
        }
        return testes;
    }

    public void adicionarTeste(Teste teste) {
        Document doc = teste.toDocument();
        testesCollection.insertOne(doc);
    }

    public void adicionarInformacoesTeste(String id, String dataRealizacao, double horasGastas, List<String> tecnicos) {
        Document doc = new Document("dataRealizacao", dataRealizacao)
                .append("horasGastas", horasGastas)
                .append("tecnicos", tecnicos);
        testesCollection.updateOne(new Document("numeroTeste", id), new Document("$set", doc));
    }

    public void removerTeste(String id) {
        testesCollection.deleteOne(new Document("numeroTeste", id));
    }


    public List<Tecnico> getTecnicos() {
        List<Tecnico> tecnicos = new ArrayList<>();
        for (Document doc : empregadosCollection.find(new Document("tipo", "Tecnico"))) {
            tecnicos.add(Tecnico.fromDocument(doc));
        }
        return tecnicos;
    }

    // Modelos
    public List<Modelo> getModelos() {
        List<Modelo> modelos = new ArrayList<>();
        try (MongoCursor<Document> cursor = modelosCollection.find().iterator()) {
            while (cursor.hasNext()) {
                modelos.add(Modelo.fromDocument(cursor.next()));
            }
        }
        return modelos;
    }

    public void adicionarModelo(Modelo modelo) {
        modelosCollection.insertOne(modelo.toDocument());
    }

    public void removerModelo(String id) {
        modelosCollection.deleteOne(new Document("_id", id));
    }

    // Aeroportos
    public List<Aeroporto> getAeroportos() {
        List<Aeroporto> aeroportos = new ArrayList<>();
        try (MongoCursor<Document> cursor = aeroportosCollection.find().iterator()) {
            while (cursor.hasNext()) {
                aeroportos.add(Aeroporto.fromDocument(cursor.next()));
            }
        }
        return aeroportos;
    }

    public void adicionarAeroporto(Aeroporto aeroporto) {
        aeroportosCollection.insertOne(aeroporto.toDocument());
    }

    public void removerAeroporto(String id) {
        aeroportosCollection.deleteOne(new Document("_id", id));
    }

    private void adicionarModelosPredefinidos() {
        List<Modelo> modelosPredefinidos = new ArrayList<>();
        modelosPredefinidos.add(new Modelo("1", "DC-10",100,4000));
        modelosPredefinidos.add(new Modelo("2", "A320",150,5000));
        modelosPredefinidos.add(new Modelo("3", "Boing 767",200,10000));
        for (Modelo modelo : modelosPredefinidos) {
            if (modelosCollection.find(new Document("_id", modelo.getId())).first() == null) {
                adicionarModelo(modelo);
            }
        }
    }
}
